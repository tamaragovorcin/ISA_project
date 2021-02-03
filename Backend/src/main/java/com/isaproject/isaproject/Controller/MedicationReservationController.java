package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.MedicationDTO;
import com.isaproject.isaproject.DTO.MedicationReservationDTO;
import com.isaproject.isaproject.DTO.MedicationReservationFrontDTO;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Repository.ConfirmationTokenRepository;
import com.isaproject.isaproject.Repository.MedicationReservationRepository;
import com.isaproject.isaproject.Service.Implementations.ActionsService;
import com.isaproject.isaproject.Service.Implementations.MedicationReservationService;
import com.isaproject.isaproject.Service.Implementations.PatientService;
import com.isaproject.isaproject.Service.Implementations.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
    JavaMailSenderImpl mailSender;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private Environment environment;

    @PostMapping("/add")
    //@PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<String> register(@RequestBody MedicationReservationDTO medicationReservationDTO)
    {

        Patient patient = patientService.findById(medicationReservationDTO.getPatient().getId());
        Boolean able = true;
        if(patient.getPenalties() > 3){
            able = false;
        }

        MedicationReservation medication = medicationReservationService.save(medicationReservationDTO);


       // LocalDate ldt = medicationReservationDTO.getDateOfTakeOver().toLocalDate();

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(medicationReservationDTO.getPatient().getEmail());
        mail.setSubject("Successfuly reserved medication!");
        mail.setFrom(environment.getProperty("spring.mail.username"));
        //mail.setFrom("pharmacyisa@gmail.com");
        mail.setText("You have successfully reserved a medication : "
                + medicationReservationDTO.getMedication().getName() +" until " + medicationReservationDTO.getDateOfTakeOver());

        mailSender.send(mail);

        return able == true ?
                new ResponseEntity<>("", HttpStatus.CREATED) :
                new ResponseEntity<>("You are not able to reserve a medication because you have 3 or more penalties.", HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    ResponseEntity<List<MedicationReservationFrontDTO>> getAll(@PathVariable Integer id)
    {
        List<MedicationReservation> medications = medicationReservationService.findAll();
        List<MedicationReservationFrontDTO> medicationReservationFrontDTOS = new ArrayList<MedicationReservationFrontDTO>();
        for( MedicationReservation med : medications){
                if(med.getPatient().getId() == id){
                    MedicationReservationFrontDTO medicationReservationFrontDTO = new MedicationReservationFrontDTO();
                    medicationReservationFrontDTO.setId(med.getId());
                    medicationReservationFrontDTO.setCode(med.getMedicine().getCode());
                    medicationReservationFrontDTO.setForm(med.getMedicine().getForm());
                    medicationReservationFrontDTO.setName(med.getMedicine().getName());
                    medicationReservationFrontDTO.setPharmacyName(med.getPharmacy().getPharmacyName());
                    medicationReservationFrontDTO.setDateOfTakeOver(med.getDateOfTakeOver());

                    medicationReservationFrontDTOS.add(medicationReservationFrontDTO);
                }

        }

        return medicationReservationFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medicationReservationFrontDTOS);
    }

    @GetMapping("/cancel/{id}")
        //@PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<String> cancel(@PathVariable Integer id)
    {
        MedicationReservation medicationReservation = medicationReservationService.findById(id);
        LocalDate date = LocalDate.now().plusDays(1);

        Boolean able = false;

        if(medicationReservation.getDateOfReservation().isBefore(date)) {
            able = true;
            medicationReservationService.delete(medicationReservation);
        }

        return able == true ?
                new ResponseEntity<>("You have successfully cancelled a reservation!", HttpStatus.CREATED) :
                new ResponseEntity<>("You are not able to cancel the reservation because it is in the next 24 hours!", HttpStatus.CREATED);


    }
}
