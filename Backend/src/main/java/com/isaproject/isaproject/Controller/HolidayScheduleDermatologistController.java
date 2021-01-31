package com.isaproject.isaproject.Controller;


import com.isaproject.isaproject.DTO.HolidayScheduleDermatologistDTO;
import com.isaproject.isaproject.DTO.HolidaySchedulePharmacistDTO;
import com.isaproject.isaproject.Model.Schedule.HolidayScheduleDermatologist;
import com.isaproject.isaproject.Model.Schedule.HolidaySchedulePharmacist;
import com.isaproject.isaproject.Service.Implementations.HolidayScheduleDermatologistService;
import com.isaproject.isaproject.Service.Implementations.HolidaySchedulePharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/holidayDermatologist")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HolidayScheduleDermatologistController {

    @Autowired
    HolidayScheduleDermatologistService dermatologistHolidayService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<String> addHolidayPharmacist(@RequestBody HolidayScheduleDermatologistDTO holidaySchedulePharmacistDTO) {

        HolidayScheduleDermatologist program = dermatologistHolidayService.save(holidaySchedulePharmacistDTO);
        return program == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Holiday is successfully added!", HttpStatus.CREATED);
    }
}
