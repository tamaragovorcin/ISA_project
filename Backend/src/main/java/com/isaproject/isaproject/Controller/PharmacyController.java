package com.isaproject.isaproject.Controller;


import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.Pharmacy.Actions;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import com.isaproject.isaproject.Service.Implementations.ActionsService;
import com.isaproject.isaproject.Service.Implementations.ExaminationScheduleService;
import com.isaproject.isaproject.Service.Implementations.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/pharmacy")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PharmacyController {
    @Autowired
    PharmacyService pharmacyService;
    @Autowired
    ActionsService  actionsService;
    @Autowired
    ExaminationScheduleService examinationScheduleService;
    @Autowired
    private Environment environment;
    @Autowired
    JavaMailSenderImpl mailSender;

    @PostMapping("/add")
    ResponseEntity<Pharmacy> add(@RequestBody PharmacyDTO ph)
    {
        Pharmacy pharmacy = pharmacyService.save(ph);
        return pharmacy == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacy);
    }
    @PostMapping("/addActions")
    ResponseEntity<Actions> shareActions(@RequestBody ActionsDTO action)
    {
        Actions actions = actionsService.save(action);
                if(actions != null) {
                    Pharmacy pharmacy = action.getPharmacy();
                    Set<Patient> subPatients = pharmacy.getSubscribedPatients();
                    SimpleMailMessage mail = new SimpleMailMessage();

                    System.out.println("Take advantage of a special opportunity!\n"
                            + " " + action.getDescription() + " till the " + action.getExpiryDate());
                    for (Patient patient : subPatients) {
                        mail.setTo(patient.getEmail());
                        mail.setSubject("Action in pharmacy " + pharmacy.getPharmacyName()+"!");
                        mail.setFrom(environment.getProperty("spring.mail.username"));
                        mail.setText("Take advantage of a special opportunity!\n"
                                + " " + action.getDescription() + " till the " + action.getExpiryDate());
                        mailSender.send(mail);
                    }
                        return ResponseEntity.ok(actions);
                }else{
                    return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

                }
    }

    @GetMapping("/actions/{id}")
    ResponseEntity<List<Actions>> getActions(@PathVariable Integer id)
    {
        List<Actions> actions = actionsService.findAll();
        return actions == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(actions);
    }

    @PostMapping("/register")
    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<String> addUser(@RequestBody PharmacyDTO pharmacyDTO) {

        Pharmacy existedPharmacy = pharmacyService.findByPharmacyName(pharmacyDTO.getPharmacyName());
        if (existedPharmacy != null) {
            throw new ResourceConflictException(existedPharmacy.getPharmacyName(), "Pharmacy name already exists");
        }
        Pharmacy pharmacy = pharmacyService.save(pharmacyDTO);
        return new ResponseEntity<>("Pharmacy is successfully registred!", HttpStatus.CREATED);
    }
    @GetMapping("/all")
    ResponseEntity<List<Pharmacy>> getAllPharmacies()
    {
        List<Pharmacy> pharmacies = pharmacyService.findAll();
        return pharmacies == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacies);
    }
    @GetMapping("/{id}")
    ResponseEntity<Pharmacy> getPharmacyById(@PathVariable Integer id) {
        Pharmacy pharmacy = pharmacyService.findById(id);
        return pharmacy == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacy);
    }

    @GetMapping("/allNames")
    ResponseEntity<List<String>> getAllPharmaciesNames()
    {
        List<Pharmacy> pharmacies = pharmacyService.findAll();
        List<String>pharmaciesNames = new ArrayList<>();
        for (Pharmacy pharmacy: pharmacies)
            pharmaciesNames.add(pharmacy.getPharmacyName());{
        }
        return pharmaciesNames == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmaciesNames);
    }

    @GetMapping("/dermatologists/{id}")
    public ResponseEntity<Set<Dermatologist>> getDermatologists(@PathVariable Integer id) {
        Set<Dermatologist> dermatologists = pharmacyService.findById(id).getDermatologists();
        return dermatologists == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(dermatologists);

    }
    @GetMapping("/pharmacists/{id}")
    public ResponseEntity<Set<Pharmacist>> getPharmacists(@PathVariable Integer id) {
        Set<Pharmacist> pharmacists = pharmacyService.findById(id).getPharmacists();
        return pharmacists == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacists);

    }

    @PostMapping("/addDermatologist")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<String> addUser(@RequestBody WorkingHoursDermatologistDTO dto) {

        if(pharmacyService.savePharmacy(dto)){
            return new ResponseEntity<>("Pharmacy is successfully registred!", HttpStatus.CREATED);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @PostMapping("/addExaminationSchedule")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<ExaminationSchedule> addSchedule(@RequestBody ExaminationScheduleDTO dto) {
        ExaminationSchedule examinationSchedule = examinationScheduleService.save(dto);
        return examinationSchedule == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(examinationSchedule);

    }

}
