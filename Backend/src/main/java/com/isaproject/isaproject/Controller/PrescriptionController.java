package com.isaproject.isaproject.Controller;



import com.isaproject.isaproject.DTO.*;
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

import com.isaproject.isaproject.Model.Users.PharmacyAdmin;
import com.isaproject.isaproject.Repository.MedicationPriceRepository;
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
    MedicationService medicationService;
    @Autowired
    MedicationPriceService medicationPriceService;
    @Autowired
    LackMedicineService lackMedicineService;
    @Autowired
    JavaMailSenderImpl mailSender;


    @Autowired
    Environment environment;

    @Autowired
    MedicationPriceRepository medicationPriceRepository;

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
                if(p.getPatient().getId() == consulting.getPatient().getId() && p.getMedication().getId() == sendDTO.getMedicationId()) {
                able = true;
            throw new IllegalArgumentException("Patient is alergist on this medicine!");}

        }
        Boolean haveMedicine = false;
        MedicationPrice medicationPrice = new MedicationPrice();
         Set<MedicationPrice> medicationPrices = consulting.getPharmacist().getPharmacy().getMedicationPrices();
        for (MedicationPrice mp: medicationPrices) {
            if (mp.getMedication().getId() == sendDTO.getMedicationId() && mp.getQuantity() > 0) {
                haveMedicine = true;
                medicationPrice= mp;



            } else {
                LackMedicineDTO lackMedicineDTO = new LackMedicineDTO();
                Medication medication = medicationService.findById(sendDTO.getMedicationId());
                lackMedicineDTO.setNameMedicine(medication.getName());
                lackMedicineDTO.setNamePharmacy(consulting.getPharmacist().getPharmacy().getPharmacyName());
                lackMedicineService.save(lackMedicineDTO);

            }
        }
        Set<Medication> medications = new HashSet<Medication>();
        Medication medication = medicationService.findById(sendDTO.getMedicationId());
        medications.add(medication);
        if(able == false && haveMedicine ==true) {
            prescription.setMedications(medications);


            prescriptionService.save(prescription);
        }
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
