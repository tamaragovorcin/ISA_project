package com.isaproject.isaproject.Controller;


import com.isaproject.isaproject.Authentification.TokenUtils;
import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.Examinations.Examination;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.Pharmacy.Actions;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Service.IServices.IPersonUserService;
import com.isaproject.isaproject.Service.Implementations.ActionsService;
import com.isaproject.isaproject.Service.Implementations.ExaminationScheduleService;
import com.isaproject.isaproject.Service.Implementations.ExaminationService;
import com.isaproject.isaproject.Service.Implementations.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private TokenUtils tokenUtils;
    @Autowired
    private IPersonUserService userService;

    @Autowired
    ExaminationService examinationService;

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
        return actions == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(actions);
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


    @GetMapping("/allPharmacistPharmacies")
    ResponseEntity<List<PharmacyFrontDTO>> getAllPharmaciesPharmacies()
    {
        List<Pharmacy> pharmacies = pharmacyService.findAll();
        List<PharmacyFrontDTO> pharmacyFrontDTOS = new ArrayList<PharmacyFrontDTO>();

        for (Pharmacy ph : pharmacies){

            PharmacyFrontDTO pf = new PharmacyFrontDTO();
            pf.setId(ph.getId());
            pf.setCountry(ph.getAddress().getCountry());
            pf.setNumber(ph.getAddress().getNumber());
            pf.setPostalCode(ph.getAddress().getPostalCode());
            pf.setStreet(ph.getAddress().getStreet());
            pf.setPharmacyName(ph.getPharmacyName());
            pf.setMark(ph.getMark());
            pf.setCity(ph.getAddress().getTown());

            pharmacyFrontDTOS.add(pf);

        }
        return pharmacyFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyFrontDTOS);
    }


    @PostMapping("/addDermatologist")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<String> addUser(@RequestBody WorkingHoursDermatologistDTO dto) {
        System.out.println("pogodiooo" +dto.getPharmacy().getPharmacyName());
        System.out.println("pogodiooooooooooooooooooooooooooooooooooooo" +dto.getDermatologist().getName());
        if(pharmacyService.savePharmacy(dto)){
            return new ResponseEntity<>("Pharmacy is successfully registred!", HttpStatus.CREATED);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PostMapping("/addExaminationSchedule")
    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ExaminationSchedule> addSchedule(@RequestBody ExaminationScheduleDTO dto) {
        System.out.println("pogodiooooooooooooooooooooooooooooooooooooo" +dto.getPharmacy().getPharmacyName());
        ExaminationSchedule examinationSchedule = examinationScheduleService.save(dto);
        return examinationSchedule == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(examinationSchedule);

    }

    @GetMapping("/ExaminationSchedule")
    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<List<ExaminationScheduleFrontDTO>> ExaminationSchedule() {

        List<ExaminationSchedule> examinationSchedule = new ArrayList<ExaminationSchedule>();
        examinationSchedule = examinationScheduleService.findAll();
        List<ExaminationScheduleFrontDTO> examinationScheduleFrontDTOS = new ArrayList<ExaminationScheduleFrontDTO>();

        for( ExaminationSchedule ex : examinationSchedule){

            ExaminationScheduleFrontDTO examinationScheduleFrontDTO = new ExaminationScheduleFrontDTO();
            examinationScheduleFrontDTO.setId(ex.getId());
            examinationScheduleFrontDTO.setPharmacy(ex.getPharmacy().getPharmacyName());
            examinationScheduleFrontDTO.setDate(ex.getDate());
            examinationScheduleFrontDTO.setDuration(ex.getDuration());
            examinationScheduleFrontDTO.setPrice(ex.getPrice());
            examinationScheduleFrontDTO.setDermatologistFirst(ex.getDermatologist().getName());
            examinationScheduleFrontDTO.setDermatologistLast(ex.getDermatologist().getSurname());
            examinationScheduleFrontDTO.setStartTime(ex.getStartTime());

            examinationScheduleFrontDTOS.add(examinationScheduleFrontDTO);

        }


        return examinationScheduleFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(examinationScheduleFrontDTOS);

    }

    @PostMapping("/addExamination")
    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<Examination> addExamination(@RequestBody ExaminationDTO dto) {
        System.out.println(dto);
        Examination examination = examinationService.save(dto);
        return examination == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(examination);

    }

    @GetMapping("/cancelExamination/{id}")
    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<List<ExaminationFrontDTO>> ExaminationPatient(@PathVariable Integer id) {

        List<Examination> examinationSchedule = new ArrayList<Examination>();
        examinationSchedule = examinationService.findAll();


        List<ExaminationFrontDTO> examinationScheduleFrontDTOS = new ArrayList<ExaminationFrontDTO>();

        for( Examination ex : examinationSchedule){

                            if(id == ex.getPatient().getId()){

                            }
                                ExaminationFrontDTO examinationScheduleFrontDTO = new ExaminationFrontDTO();
                                examinationScheduleFrontDTO.setId(ex.getId());
                                examinationScheduleFrontDTO.setPharmacy(ex.getExaminationSchedule().getPharmacy().getPharmacyName());
                                examinationScheduleFrontDTO.setPatientId(ex.getPatient().getId());
                                examinationScheduleFrontDTO.setDate(ex.getExaminationSchedule().getDate());
                                examinationScheduleFrontDTO.setDuration(ex.getExaminationSchedule().getDuration());
                                examinationScheduleFrontDTO.setPrice(ex.getExaminationSchedule().getPrice());
                                examinationScheduleFrontDTO.setDermatologistFirst(ex.getExaminationSchedule().getDermatologist().getName());
                                examinationScheduleFrontDTO.setDermatologistLast(ex.getExaminationSchedule().getDermatologist().getSurname());
                                examinationScheduleFrontDTO.setStartTime(ex.getExaminationSchedule().getStartTime());

                                examinationScheduleFrontDTOS.add(examinationScheduleFrontDTO);

                            }

        return examinationScheduleFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(examinationScheduleFrontDTOS);

    }


    @GetMapping("/cancel/{id}")
    void cancel(@PathVariable Integer id)
    {

        List<Examination> examinationSchedule = new ArrayList<Examination>();
        examinationSchedule = examinationService.findAll();

        for( Examination ex : examinationSchedule){
            if(ex.getId() == id){
                examinationService.delete(ex);
            }
        }

    }

    @GetMapping("/searchName/{name}")
    ResponseEntity<List<PharmacyFrontDTO>>  searchPharmaciesByName(@PathVariable String name)
    {

        List<Examination> examinationSchedule = new ArrayList<Examination>();
        examinationSchedule = examinationService.findAll();

        List<Pharmacy> pharmacies = pharmacyService.findAll();
        List<PharmacyFrontDTO> pharmacyFrontDTOS = new ArrayList<PharmacyFrontDTO>();

        for (Pharmacy ph : pharmacies){
            if(ph.getPharmacyName().equals(name)) {
                System.out.println("Tu sammmm");
                PharmacyFrontDTO pf = new PharmacyFrontDTO();
                pf.setId(ph.getId());
                pf.setCountry(ph.getAddress().getCountry());
                pf.setNumber(ph.getAddress().getNumber());
                pf.setPostalCode(ph.getAddress().getPostalCode());
                pf.setStreet(ph.getAddress().getStreet());
                pf.setPharmacyName(ph.getPharmacyName());
                pf.setMark(ph.getMark());
                pf.setCity(ph.getAddress().getTown());

                pharmacyFrontDTOS.add(pf);
            }

        }
        return pharmacyFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyFrontDTOS);
    }

    @GetMapping("/searchCity/{city}")
    ResponseEntity<List<PharmacyFrontDTO>>  searchPharmaciesByCity(@PathVariable String city)
    {

        List<Examination> examinationSchedule = new ArrayList<Examination>();
        examinationSchedule = examinationService.findAll();

        List<Pharmacy> pharmacies = pharmacyService.findAll();
        List<PharmacyFrontDTO> pharmacyFrontDTOS = new ArrayList<PharmacyFrontDTO>();

        for (Pharmacy ph : pharmacies){
            if(ph.getAddress().getTown().equals(city)) {
                System.out.println("Tu sammmm");
                PharmacyFrontDTO pf = new PharmacyFrontDTO();
                pf.setId(ph.getId());
                pf.setCountry(ph.getAddress().getCountry());
                pf.setNumber(ph.getAddress().getNumber());
                pf.setPostalCode(ph.getAddress().getPostalCode());
                pf.setStreet(ph.getAddress().getStreet());
                pf.setPharmacyName(ph.getPharmacyName());
                pf.setMark(ph.getMark());
                pf.setCity(ph.getAddress().getTown());

                pharmacyFrontDTOS.add(pf);
            }

        }
        return pharmacyFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyFrontDTOS);
    }


    @GetMapping("searchMark/{MarkMin}/{MarkMax}")
    ResponseEntity<List<PharmacyFrontDTO>> getAllByMark(@PathVariable int MarkMin,@PathVariable int MarkMax ) {
       /* List<Pharmacy> pharmacies= pharmacyService.findByMark(MarkMin,MarkMax);
        List<PharmacyFrontDTO> medicationsForFront = new ArrayList<>();
        for (Pharmacy ph: pharmacies) {
            PharmacyFrontDTO pharmacyFrontDTO = getMedicationSearchDTO(medication);
            medicationsForFront.add(medicationSearchDTO);
        }
        return medicationsForFront == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medicationsForFront);
    }*/
        return null;
    }
}
