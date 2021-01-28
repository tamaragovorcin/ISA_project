package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.ConsultingDTO;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Service.Implementations.ConsultingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consulting")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ConsultingController {
    @Autowired
    ConsultingService consultingService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<String> addConsulting(@RequestBody ConsultingDTO consultingDTO) {

        Consulting consulting = consultingService.save(consultingDTO);
        return consulting == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Consulting is successfully added!", HttpStatus.CREATED);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<String> update(@RequestBody Consulting consulting) {

        Consulting consulting1 = consultingService.update(consulting);
        return consulting1 == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Consulting is successfully updated!", HttpStatus.CREATED);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<Consulting> getAll() {

        List<Consulting> programs = consultingService.findAll();
        return programs == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(programs.get(0));
    }
}
