package com.isaproject.isaproject.Controller;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Service.Implementations.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/patient")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PatientController {
    @Autowired
    PatientService patientService;

    @PostMapping("/register")
    ResponseEntity<Patient> register(@RequestBody PersonUserDTO person)
    {
        Patient patient = patientService.save(person);
        return patient == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patient);
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<Patient> getById(@PathVariable Integer id)
    {
        Integer idd = 1;
        Patient patient = patientService.findById(idd);
        return patient == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patient);
    }

    @GetMapping("")
    ResponseEntity<List<Patient>> getAll()
    {
        List<Patient> patients = patientService.findAll();
        return patients == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patients);
    }
    @GetMapping("/email/{id}")
    ResponseEntity<String> getEmail(@PathVariable Integer id)
    {
        String email = patientService.getEmail(id);
        return email == null || email.equals("") ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(email);
    }

    @PostMapping("/update")
    ResponseEntity<Patient> update(@RequestBody PersonUserDTO person)
    {
        Patient per = patientService.findByEmail(person.getEmail());
        Integer id = per.getId();
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+id);
        patientService.delete(per);
        Patient patient = patientService.update(person, id);
        return patient == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patient);
    }

}
