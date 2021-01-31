package com.isaproject.isaproject.Controller;


import com.isaproject.isaproject.DTO.HolidaySchedulePharmacistDTO;
import com.isaproject.isaproject.Model.Schedule.HolidaySchedulePharmacist;
import com.isaproject.isaproject.Service.Implementations.DermatologistService;
import com.isaproject.isaproject.Service.Implementations.HolidaySchedulePharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/holidayPharmacist")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HolidaySchedulePharmacistController {

    @Autowired
    HolidaySchedulePharmacistService pharmacistHolidayService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<String> addHolidayPharmacist(@RequestBody HolidaySchedulePharmacistDTO holidaySchedulePharmacistDTO) {

        HolidaySchedulePharmacist program = pharmacistHolidayService.save(holidaySchedulePharmacistDTO);
        return program == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Holiday is successfully added!", HttpStatus.CREATED);
    }

}
