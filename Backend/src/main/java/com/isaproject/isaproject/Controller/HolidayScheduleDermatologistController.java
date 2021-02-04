package com.isaproject.isaproject.Controller;


import com.isaproject.isaproject.DTO.HolidayScheduleDermatologistDTO;
import com.isaproject.isaproject.DTO.HolidaySchedulePharmacistDTO;
import com.isaproject.isaproject.DTO.HolidaySchedulePharmacistFrontDTO;
import com.isaproject.isaproject.DTO.RefuseHolidayScheduleDTO;
import com.isaproject.isaproject.Model.Schedule.HolidayScheduleDermatologist;
import com.isaproject.isaproject.Model.Schedule.HolidaySchedulePharmacist;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import com.isaproject.isaproject.Model.Users.PharmacyAdmin;
import com.isaproject.isaproject.Model.Users.SystemAdmin;
import com.isaproject.isaproject.Service.Implementations.HolidayScheduleDermatologistService;
import com.isaproject.isaproject.Service.Implementations.HolidaySchedulePharmacistService;
import com.isaproject.isaproject.Service.Implementations.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/holidayDermatologist")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HolidayScheduleDermatologistController {

    @Autowired
    HolidayScheduleDermatologistService dermatologistHolidayService;
    @Autowired
    SystemAdminService systemAdminService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<String> addHolidayPharmacist(@RequestBody HolidayScheduleDermatologistDTO holidaySchedulePharmacistDTO) {

        HolidayScheduleDermatologist program = dermatologistHolidayService.save(holidaySchedulePharmacistDTO);
        return program == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Holiday is successfully added!", HttpStatus.CREATED);
    }
    @GetMapping("/all")
    ResponseEntity<List<HolidayScheduleDermatologist>> getAll()
    {
        List<HolidayScheduleDermatologist> holidayScheduleDermatologists = dermatologistHolidayService.findAll();
        return holidayScheduleDermatologists == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(holidayScheduleDermatologists);
    }
    @GetMapping("/waiting")
    ResponseEntity<List<HolidaySchedulePharmacistFrontDTO>> getWaiting()
    {
        List<HolidaySchedulePharmacistFrontDTO> holidayScheduleDermatologists = new ArrayList<HolidaySchedulePharmacistFrontDTO>();
        for(HolidayScheduleDermatologist holiday :  dermatologistHolidayService.findAll()){
            if(holiday.getApproved().equals("WAIT_FOR_RESPONSE")) {
                HolidaySchedulePharmacistFrontDTO holidaySchedulePharmacistFrontDTO = new HolidaySchedulePharmacistFrontDTO();
                holidaySchedulePharmacistFrontDTO.setPharmacist(holiday.getDermatologist().getName() + " " + holiday.getDermatologist().getSurname());
                holidaySchedulePharmacistFrontDTO.setScheduleId(holiday.getId());
                holidaySchedulePharmacistFrontDTO.setApproved(holiday.getApproved());
                holidaySchedulePharmacistFrontDTO.setStartDate(holiday.getStartDate());
                holidaySchedulePharmacistFrontDTO.setEmail(holiday.getDermatologist().getEmail());
                holidaySchedulePharmacistFrontDTO.setEndDate(holiday.getEndDate());
                holidaySchedulePharmacistFrontDTO.setType(holiday.getType());
                holidayScheduleDermatologists.add(holidaySchedulePharmacistFrontDTO);
            }
        }
        return holidayScheduleDermatologists == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(holidayScheduleDermatologists);
    }

    @GetMapping("/approve/{id}")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    ResponseEntity<HolidayScheduleDermatologist> getActions(@PathVariable Integer id)
    {
            HolidayScheduleDermatologist holidayScheduleDermatologist = dermatologistHolidayService.approve(id);
            dermatologistHolidayService.sendEmailApproved(id);
            if(holidayScheduleDermatologist != null) {
                return ResponseEntity.ok(holidayScheduleDermatologist);
            }


        return   new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/refuse")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    ResponseEntity<HolidayScheduleDermatologist> refuse(@RequestBody RefuseHolidayScheduleDTO dto)
    {
        try{
            HolidayScheduleDermatologist holidayScheduleDermatologist = dermatologistHolidayService.refuse(dto);
            dermatologistHolidayService.sendEmailRefused(dto);
            return     ResponseEntity.ok(holidayScheduleDermatologist);

        }catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
