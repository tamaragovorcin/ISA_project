package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.HolidaySchedulePharmacistDTO;
import com.isaproject.isaproject.DTO.RefuseHolidayScheduleDTO;
import com.isaproject.isaproject.Model.Schedule.HolidaySchedulePharmacist;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.PharmacyAdmin;
import com.isaproject.isaproject.Service.Implementations.HolidaySchedulePharmacistService;
import com.isaproject.isaproject.Service.Implementations.PharmacyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/holidayPharmacist")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HolidaySchedulePharmacistController {

    @Autowired
    HolidaySchedulePharmacistService pharmacistHolidayService;
    @Autowired
    PharmacyAdminService pharmacyAdminService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<String> addHolidayPharmacist(@RequestBody HolidaySchedulePharmacistDTO holidaySchedulePharmacistDTO) {
        System.out.println("---------------------");
    System.out.println(holidaySchedulePharmacistDTO.getPharmacist());
        HolidaySchedulePharmacist program = pharmacistHolidayService.save(holidaySchedulePharmacistDTO);
        return program == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Holiday is successfully added!", HttpStatus.CREATED);
    }

    @GetMapping("/approve/{id}")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<HolidaySchedulePharmacist> getActions(@PathVariable Integer id)
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        System.out.println(pharmacistHolidayService.findById(id).getPharmacist().getPharmacy().getId());
        System.out.println(pharmacyAdmin.getPharmacy().getId());
        if(pharmacistHolidayService.findById(id).getPharmacist().getPharmacy().getId() == pharmacyAdmin.getPharmacy().getId()) {
            HolidaySchedulePharmacist holidaySchedulePharmacist = pharmacistHolidayService.approve(id);
            pharmacistHolidayService.sendEmailApproved(id);
            return ResponseEntity.ok(holidaySchedulePharmacist);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/refuse")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<HolidaySchedulePharmacist> refuse(@RequestBody RefuseHolidayScheduleDTO dto)
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());

        if(pharmacistHolidayService.findById(dto.getRequestId()).getPharmacist().getPharmacy().getId() == pharmacyAdmin.getPharmacy().getId()) {
            HolidaySchedulePharmacist holidaySchedulePharmacist = pharmacistHolidayService.refuse(dto);
            pharmacistHolidayService.sendEmailRefused(dto);
            return ResponseEntity.ok(holidaySchedulePharmacist);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
