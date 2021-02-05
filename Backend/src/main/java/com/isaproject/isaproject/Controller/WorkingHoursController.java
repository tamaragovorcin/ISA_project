package com.isaproject.isaproject.Controller;


import com.isaproject.isaproject.DTO.PharmacyAdminDTO;
import com.isaproject.isaproject.DTO.WorkingHoursDermatologistDTO;
import com.isaproject.isaproject.DTO.WorkingHoursPharmacistDTO;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.Schedule.WorkingHoursDermatologist;
import com.isaproject.isaproject.Model.Schedule.WorkingHoursPharmacist;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Service.Implementations.PharmacyAdminService;
import com.isaproject.isaproject.Service.Implementations.WorkingHoursDermatologistService;
import com.isaproject.isaproject.Service.Implementations.WorkingHoursPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workingHours")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WorkingHoursController {
    @Autowired
    WorkingHoursPharmacistService workingHoursPharmacistService;
    @Autowired
    WorkingHoursDermatologistService workingHoursDermatologistService;

    @PostMapping("/pharmacist")
    //@PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<String> addPharmacistWorkingHours(@RequestBody WorkingHoursPharmacistDTO userRequest) {
        System.out.println("------------------------------------------------------------------");
        System.out.println("----POGODIO CONTROLLER---");

        WorkingHoursPharmacist workingHoursPharmacist = workingHoursPharmacistService.save(userRequest);
        return new ResponseEntity<>("Working hours for pharmacist successfully added.", HttpStatus.CREATED);
    }

    @PostMapping("/dermatologist")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<String> addDermatologistWorkingHours(@RequestBody WorkingHoursDermatologistDTO userRequest) {
        System.out.println("------------------------------------------------------------------");
        System.out.println(userRequest);

        WorkingHoursDermatologist workingHoursDermatologist = workingHoursDermatologistService.save(userRequest);
        return new ResponseEntity<>("Working hours for pharmacist successfully added.", HttpStatus.CREATED);
    }

}
