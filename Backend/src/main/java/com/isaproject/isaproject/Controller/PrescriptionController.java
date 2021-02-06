package com.isaproject.isaproject.Controller;


import com.isaproject.isaproject.DTO.ConsultingDTO;
import com.isaproject.isaproject.DTO.Prescription2DTO;
import com.isaproject.isaproject.DTO.PrescriptionDTO;
import com.isaproject.isaproject.DTO.PrescriptionSendDTO;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Examinations.Examination;
import com.isaproject.isaproject.Model.Examinations.Prescription;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Service.Implementations.ConsultingService;
import com.isaproject.isaproject.Service.Implementations.ExaminationService;
import com.isaproject.isaproject.Service.Implementations.PatientService;
import com.isaproject.isaproject.Service.Implementations.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/add")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<String> addPrescription(@RequestBody PrescriptionSendDTO sendDTO) {

        Consulting consulting = consultingService.findById(sendDTO.getConsultingId());
        PrescriptionDTO prescription = new PrescriptionDTO();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(consulting.getPharmacist().getPharmacy().getPharmacyName());
        prescription.setPatient(consulting.getPatient());
        prescription.setPharmacy(consulting.getPharmacist().getPharmacy());
        prescription.setDate(consulting.getDate());
        prescription.setDurationOfTherapy(sendDTO.getDurationOfTherapy());
        prescription.setTaken(false);
        prescription.setMedications(sendDTO.getMedications());



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
        prescription.setMedications(sendDTO.getMedications());



        prescriptionService.save(prescription);
        return prescription == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Prescription is successfully added!", HttpStatus.CREATED);
    }
}
