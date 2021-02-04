package com.isaproject.isaproject.Controller;


import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Schedule.WorkingHoursPharmacist;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import com.isaproject.isaproject.Service.Implementations.PharmacistService;
import com.isaproject.isaproject.Service.Implementations.PharmacyAdminService;
import com.isaproject.isaproject.Service.Implementations.WorkingHoursPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/workingHours")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WorkingHoursController {
    @Autowired
    WorkingHoursPharmacistService workingHoursPharmacistService;

    @Autowired
    PharmacistService pharmacistService;

    @PostMapping("/pharmacist")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<String> addPharmacistWorkingHours(@RequestBody WorkingHoursPharmacistDTO userRequest) {
        System.out.println("------------------------------------------------------------------");
        System.out.println("----POGODIO CONTROLLER---");

        WorkingHoursPharmacist workingHoursPharmacist = workingHoursPharmacistService.save(userRequest);
        return new ResponseEntity<>("Working hours for pharmacist successfully added.", HttpStatus.CREATED);
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('PHARMACIST')")
    ResponseEntity<WorkingHoursPharmacistDTO> getOurWorkingHours() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser) currentUser.getPrincipal();
        Pharmacist pharmacist = pharmacistService.findById(user.getId());

        WorkingHoursPharmacistDTO  hours = null;

        for (WorkingHoursPharmacist wh : workingHoursPharmacistService.findAll()) {
                if(wh.getPharmacist().getId() == pharmacist.getId())
                hours = new WorkingHoursPharmacistDTO(wh.getPharmacist(), wh.getMondaySchedule().getStartTime(), wh.getMondaySchedule().getEndTime(), wh.getTuesdaySchedule().getStartTime(), wh.getTuesdaySchedule().getEndTime(), wh.getWednesdaySchedule().getStartTime(), wh.getWednesdaySchedule().getEndTime(), wh.getThursdaySchedule().getStartTime(), wh.getThursdaySchedule().getEndTime(), wh.getFridaySchedule().getStartTime(), wh.getFridaySchedule().getEndTime(), wh.getSaturdaySchedule().getStartTime(), wh.getSaturdaySchedule().getEndTime(), wh.getSundaySchedule().getStartTime(), wh.getSundaySchedule().getEndTime());
        }


        return hours == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(hours);
    }

}
