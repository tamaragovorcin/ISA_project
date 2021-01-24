package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.MedicationDTO;
import com.isaproject.isaproject.DTO.MedicationReservationDTO;
import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Repository.ConfirmationTokenRepository;
import com.isaproject.isaproject.Service.Implementations.ActionsService;
import com.isaproject.isaproject.Service.Implementations.MedicationReservationService;
import com.isaproject.isaproject.Service.Implementations.PatientService;
import com.isaproject.isaproject.Service.Implementations.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicationReservation")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MedicationReservationController {

    @Autowired
    MedicationReservationService medicationReservationService;
    @Autowired
    PatientService patientService;


    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private Environment environment;

    @PostMapping("/add")
    //@PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<MedicationReservation> register(@RequestBody MedicationReservationDTO medicationReservationDTO)
    {
       
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+medicationReservationDTO.getPatient().getId());
        MedicationReservation medication = medicationReservationService.save(medicationReservationDTO);
        return medication == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medication);
    }

    @GetMapping("")
    ResponseEntity<List<MedicationReservation>> getAll()
    {
        List<MedicationReservation> medications = medicationReservationService.findAll();
        System.out.println(medications.size());
        return medications == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medications);
    }
}
