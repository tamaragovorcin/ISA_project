package com.isaproject.isaproject.Controller;



import com.isaproject.isaproject.DTO.ConsultingDTO;
import com.isaproject.isaproject.DTO.Prescription2DTO;
import com.isaproject.isaproject.DTO.PrescriptionDTO;
import com.isaproject.isaproject.DTO.PrescriptionSendDTO;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Examinations.Examination;
import com.isaproject.isaproject.Model.Examinations.Prescription;
import com.isaproject.isaproject.Model.HelpModel.MedicationPrice;
import com.isaproject.isaproject.Model.HelpModel.PatientsMedicationAlergy;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Users.Patient;

import com.isaproject.isaproject.DTO.PrescriptionDTO;
import com.isaproject.isaproject.DTO.PrescriptionSendDTO;
import com.isaproject.isaproject.Model.Examinations.Consulting;

import com.isaproject.isaproject.Service.Implementations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/prescription")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PrescriptionController {

    @Autowired
    PrescriptionService prescriptionService;

    @Autowired
    PatientService patientService;

    @Autowired
    ConsultingService consultingService;

    @Autowired
    ExaminationService examinationService;

    @Autowired
    AlergiesService alergiesService;
    @Autowired
    JavaMailSenderImpl mailSender;


    @Autowired
    Environment environment;


    @Autowired
    MedicationPriceService medicationPriceService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<String> addPrescription(@RequestBody PrescriptionSendDTO sendDTO) {

        Consulting consulting = consultingService.findById(sendDTO.getConsultingId());
        PrescriptionDTO prescription = new PrescriptionDTO();

        prescription.setPatient(consulting.getPatient());
        prescription.setPharmacy(consulting.getPharmacist().getPharmacy());
        prescription.setDate(consulting.getDate());
        prescription.setDurationOfTherapy(sendDTO.getDurationOfTherapy());
        prescription.setTaken(false);

        Boolean able = false;
        List<PatientsMedicationAlergy> patientsMedicationAlergies = alergiesService.findAll();
        for (PatientsMedicationAlergy p: patientsMedicationAlergies) {
                if(p.getPatient().getId() == consulting.getPatient().getId() && p.getMedication().getId() == sendDTO.getMedication().getId())
                able = true;
             return new ResponseEntity<>("Patient is alergist!", HttpStatus.CREATED);

        }
        Boolean haveMedicine = false;
       List<MedicationPrice> medicationPrices = new ArrayList<>();
        for (MedicationPrice mp: medicationPrices) {
            if (mp.getPharmacy().getPharmacyName().equals(consulting.getPharmacist().getPharmacy()) && mp.getMedication().getId() == sendDTO.getMedication().getId() && mp.getQuantity() > 0) {
                haveMedicine = true;
                mp.setQuantity(mp.getQuantity() - 1);
            } else {
              /*  SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo(consulting.getPharmacist().getPharmacy());
                mail.setSubject("Successfuly reserved pharmacist consultation!");
                mail.setFrom(environment.getProperty("spring.mail.username"));
                //mail.setFrom("pharmacyisa@gmail.com");
                mail.setText("You have successfully reserved an appointment on : "
                        + consulting1.getDate() + " at " + consulting1.getStartTime() + ". Your doctor is " + consulting1.getPharmacist().getName() + " " + consulting1.getPharmacist().getSurname());

                mailSender.send(mail);*/
            }
        }
        Set<Medication> medications = new HashSet<Medication>();
        medications.add(sendDTO.getMedication());
        if(able == false)
        prescription.setMedications(medications);



        prescriptionService.save(prescription);
        return prescription == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Prescription is successfully added!", HttpStatus.CREATED);
    }

    @PostMapping("/addDerm")
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<String> addPrescription2(@RequestBody Prescription2DTO sendDTO) {

        Examination examination = examinationService.getById(sendDTO.getExaminationId());
        PrescriptionDTO prescription = new PrescriptionDTO();

        prescription.setPatient(examination.getPatient());
        prescription.setPharmacy(examination.getExaminationSchedule().getPharmacy());
        prescription.setDate(examination.getExaminationSchedule().getDate());
        prescription.setDurationOfTherapy(sendDTO.getDurationOfTherapy());
        prescription.setTaken(false);
        Boolean able = false;
        List<PatientsMedicationAlergy> patientsMedicationAlergies = alergiesService.findAll();
        for (PatientsMedicationAlergy p: patientsMedicationAlergies) {
            if(p.getPatient().getId() == examination.getPatient().getId() && p.getMedication().getId() == sendDTO.getMedication().getId())
                able = true;
        }
        Set<Medication> medications = new HashSet<Medication>();
        medications.add(sendDTO.getMedication());
        prescription.setMedications(medications);



        prescriptionService.save(prescription);
        return prescription == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Prescription is successfully added!", HttpStatus.CREATED);
    }


}
