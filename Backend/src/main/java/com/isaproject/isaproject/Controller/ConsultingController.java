package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.ConsultingDTO;
import com.isaproject.isaproject.DTO.ConsultingForBackDTO;
import com.isaproject.isaproject.DTO.ConsultingNoteDTO;
import com.isaproject.isaproject.DTO.OfferReviewDTO;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Orders.Offer;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import com.isaproject.isaproject.Repository.ConsultingRepository;
import com.isaproject.isaproject.Repository.PatientRepository;
import com.isaproject.isaproject.Service.Implementations.ConsultingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/consulting")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ConsultingController {
    @Autowired
    ConsultingService consultingService;

    @Autowired
    ConsultingRepository consultingRepository;

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
    public ResponseEntity<String> update(@RequestBody ConsultingForBackDTO consulting) {

        Consulting consulting1 = consultingService.findById(consulting.getConsultingId());
        consulting1.setDuration(consulting.getDuration());
        consulting1.setInformation(consulting.getInformation());
        consulting1.setShowedUp(true);
        this.consultingRepository.save(consulting1);
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
