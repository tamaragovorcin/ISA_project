package com.isaproject.isaproject.Controller;
import com.isaproject.isaproject.DTO.WorkingHoursDermatologistDTO;
import com.isaproject.isaproject.Model.Schedule.WorkingHoursDermatologist;
import com.isaproject.isaproject.Service.Implementations.WorkingHoursDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ScheduleController {

    @Autowired
    WorkingHoursDermatologistService workingHoursDermatologistService;

    @PostMapping("/add")
    //@PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<WorkingHoursDermatologist> addWorkingHours(@RequestBody WorkingHoursDermatologistDTO workingHoursDermatologistDTO) {

        WorkingHoursDermatologist workingHoursDermatologist = workingHoursDermatologistService.save(workingHoursDermatologistDTO);
        return workingHoursDermatologist == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(workingHoursDermatologist);
    }
}