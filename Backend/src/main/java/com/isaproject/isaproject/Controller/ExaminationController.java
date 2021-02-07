package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Examinations.Examination;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Repository.ExaminationRepository;
import com.isaproject.isaproject.Service.Implementations.ConsultingService;
import com.isaproject.isaproject.Service.Implementations.ExaminationScheduleService;
import com.isaproject.isaproject.Service.Implementations.ExaminationService;
import com.isaproject.isaproject.Service.Implementations.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/examination")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExaminationController {

    @Autowired
    ExaminationService examinationService;

    @Autowired
    PatientService patientService;
    @Autowired
    ExaminationScheduleService examinationScheduleService;
    @Autowired
    ExaminationRepository examinationRepository;

    @Autowired
    JavaMailSenderImpl mailSender;


    @Autowired
    Environment environment;


    @PostMapping("/add")
    // @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<String> addConsulting(@RequestBody SendRequestExaminationDTO sendRequestExaminationDTO) {

        Patient patient = patientService.findById(sendRequestExaminationDTO.getPatientId());
        ExaminationSchedule examinationSchedule = examinationScheduleService.findById(sendRequestExaminationDTO.getExaminationTermId());
        ExaminationDTO examinationDTO = new ExaminationDTO();


       examinationDTO= new ExaminationDTO(patient, false, false, sendRequestExaminationDTO.getExaminationTermId(), "");
        Examination examination = examinationService.save(examinationDTO);
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(patient.getEmail());
        mail.setSubject("Successfuly reserved examination!");

        mail.setFrom(environment.getProperty("spring.mail.username"));
        mail.setText("You have successfully reserved an appointment on : "
                + examination.getExaminationSchedule().getDate() + " at " + examination.getExaminationSchedule().getStartTime() + ".\n" +
                " Your doctor is " + examination.getExaminationSchedule().getDermatologist().getName() + " " + examination.getExaminationSchedule().getDermatologist().getSurname()
        +"\n Pharmacy where the examination will be held is " + examination.getExaminationSchedule().getPharmacy().getPharmacyName() + ".");

        mailSender.send(mail);
        return examination == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Examination is successfully added!", HttpStatus.CREATED);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<String> update(@RequestBody ExaminationForBack examination) {

        Examination examination1 = examinationService.getById(examination.getExaminationId());

        examination1.setInformation(examination.getInformation());
        examination1.setShowedUp(true);
        this.examinationRepository.save(examination1);
        return examination1 == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Examination is successfully updated!", HttpStatus.CREATED);
    }

}
