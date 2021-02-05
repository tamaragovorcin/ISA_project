package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.Authentification.TokenUtils;
import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Examinations.Examination;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.HelpModel.MedicationPrice;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Pharmacy.Actions;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.Mark;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Service.IServices.IPersonUserService;
import com.isaproject.isaproject.Service.Implementations.*;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import com.isaproject.isaproject.Service.Implementations.ActionsService;
import com.isaproject.isaproject.Service.Implementations.ExaminationScheduleService;
import com.isaproject.isaproject.Service.Implementations.MedicationPriceService;
import com.isaproject.isaproject.Service.Implementations.PharmacyService;
import org.bouncycastle.crypto.prng.ReversedWindowGenerator;
import org.hibernate.annotations.GeneratorType;
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

import javax.swing.*;
import java.sql.Date;
import java.time.LocalDate;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api/pharmacy")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PharmacyController  {
    @Autowired
    PharmacyService pharmacyService;
    @Autowired
    MarkService markService;
    @Autowired
    ActionsService  actionsService;
    @Autowired
    MedicationPriceService medicationPriceService;
    @Autowired
    ExaminationScheduleService examinationScheduleService;
    @Autowired
    private Environment environment;
    @Autowired
    JavaMailSenderImpl mailSender;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private IPersonUserService userService;

    @Autowired
    ConsultingService consultingService;

    @Autowired
    ExaminationService examinationService;
    @Autowired
    EPrescriptionService ePrescriptionService;

    @Autowired
    PatientService patientService;

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
    //@PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<List<PharmacyNameDTO>> getAllPharmaciesNames()
    {
        List<Pharmacy> pharmacies = pharmacyService.findAll();
        List<PharmacyNameDTO>pharmaciesNames = new ArrayList<>();
        for (Pharmacy pharmacy: pharmacies) {
            pharmaciesNames.add(new PharmacyNameDTO(pharmacy.getPharmacyName(), pharmacy.getId()));
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
    @GetMapping("/medication/{id}")
    public ResponseEntity<List<MedicationFrontDTO>> getMedication(@PathVariable Integer id) {
        List<MedicationFrontDTO> medicationFrontDTOS = new ArrayList<MedicationFrontDTO>();
        List<MedicationPrice> medicationPrices = medicationPriceService.findAll();
        for (MedicationPrice med : medicationPrices) {
            if (med.getPharmacy().getId() == id) {
                MedicationFrontDTO medicationFrontDTO = new MedicationFrontDTO();
                medicationFrontDTO.setId(med.getMedication().getId());
                medicationFrontDTO.setName(med.getMedication().getName());
                medicationFrontDTO.setType(med.getMedication().getType());
                medicationFrontDTO.setForm(med.getMedication().getForm());
                medicationFrontDTO.setMark(med.getMedication().getMark());
                medicationFrontDTO.setIssuanceRegime(med.getMedication().getIssuanceRegime());
                medicationFrontDTO.setLoyaltyPoints(med.getMedication().getLoyaltyPoints());
                medicationFrontDTO.setPrice(med.getPrice());
                medicationFrontDTO.setManufacturer(med.getMedication().getSpecification().getManufacturer());
                medicationFrontDTO.setPharmacyName(med.getPharmacy().getPharmacyName());

                medicationFrontDTOS.add(medicationFrontDTO);
            }

        }
        return medicationFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medicationFrontDTOS);

    }

    @GetMapping("/freeExaminationTerms/{id}")
    public ResponseEntity<List<FreeExaminationTermsDTO>> getFreeExaminationTerms(@PathVariable Integer id) {
        Set<ExaminationSchedule> examinationTerms = pharmacyService.findById(id).getExaminationSchedules();
        List<FreeExaminationTermsDTO> freeExaminationTerms = new ArrayList<FreeExaminationTermsDTO>();
        for(ExaminationSchedule ex :  examinationTerms){
                System.out.println(ex.getDermatologist().getName());

                FreeExaminationTermsDTO term = new FreeExaminationTermsDTO();
                term.setId(ex.getId());
                term.setDermatologistName(ex.getDermatologist().getName());
                term.setDermatologistSurname(ex.getDermatologist().getSurname());
                term.setDermatologistMark(ex.getDermatologist().getMarkDermatologist());
                term.setDate(ex.getDate());
                term.setDuration(ex.getDuration());
                term.setFinished(ex.getFinished());
                term.setStartTime(ex.getStartTime());
                term.setPrice(ex.getPrice());
                term.setPharmacyId(ex.getPharmacy().getId());
            freeExaminationTerms.add(term);

        }
        return freeExaminationTerms == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(freeExaminationTerms);

    }
   /* @PostMapping("/addDermatologist")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<String> addUser(@RequestBody WorkingHoursDermatologistDTO dto) {

        if(pharmacyService.savePharmacy(dto)){
            return new ResponseEntity<>("Dermatologist is successfully added as employee!", HttpStatus.CREATED);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }*/

    @PostMapping("/addExaminationSchedule")
    //@PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<ExaminationSchedule> addSchedule(@RequestBody ExaminationScheduleDTO dto) {

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
            if(ex.getFinished()==false){
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

        }}


        return examinationScheduleFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(examinationScheduleFrontDTOS);

    }

    @PostMapping("/addExamination")
    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<String> addExamination(@RequestBody ExaminationDTO dto) {

        Patient patient = patientService.findById(dto.getPatient().getId());
        Boolean able = true;
        if(patient.getPenalties() > 3){
            able = false;
        }
        Examination examination = examinationService.save(dto);
        List<ExaminationSchedule> examinationSchedule = new ArrayList<ExaminationSchedule>();
        examinationSchedule = examinationScheduleService.findAll();
        ExaminationSchedule examinationSchedule1 = new ExaminationSchedule();

        for(ExaminationSchedule es: examinationSchedule){
            if(es.getId()==dto.getExaminationId()){
                examinationSchedule1 = es;
                examinationScheduleService.update(es, true);

            }
        }

        if(able) {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(dto.getPatient().getEmail());
            mail.setSubject("Successfuly reserved dermatologist apointment!");
            mail.setFrom(environment.getProperty("spring.mail.username"));
            //mail.setFrom("pharmacyisa@gmail.com");
            mail.setText("You have successfully reserved an appointment on : "
                    + examinationSchedule1.getDate() + " at " + examinationSchedule1.getStartTime() + ". Your doctor is " + examinationSchedule1.getDermatologist().getName() + " " + examinationSchedule1.getDermatologist().getSurname());

            mailSender.send(mail);
        }

        return able == true ?
                new ResponseEntity<>("Consulting is successfully reserved!", HttpStatus.CREATED) :
                new ResponseEntity<>("You are not able to reserve a medication because you have 3 or more penalties!", HttpStatus.CREATED);

    }

    @GetMapping("dateClosest/{id}")
        //@PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<List<ExaminationFrontDTO>> dateClosest(@PathVariable Integer id) {


        List<Examination> examinationSchedule = new ArrayList<Examination>();
        examinationSchedule = examinationService.findAll();


        Collections.sort(examinationSchedule, new Comparator<Examination>() {
            public int compare(Examination o1, Examination o2) {
                return o1.getExaminationSchedule().getDate().compareTo(o2.getExaminationSchedule().getDate());
            }
        });

        List<ExaminationFrontDTO> examinationScheduleFrontDTOS = new ArrayList<ExaminationFrontDTO>();

        for (Examination ex : examinationSchedule) {

            if (id == ex.getPatient().getId()) {


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
                LocalDate date5 = LocalDate.now();
                if ((ex.getExaminationSchedule().getDate()).compareTo(date5) < 0) {
                    examinationScheduleFrontDTO.setFinished(true);
                } else {
                    examinationScheduleFrontDTO.setFinished(false);
                }
                examinationScheduleFrontDTOS.add(examinationScheduleFrontDTO);
            }
        }

        return examinationScheduleFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(examinationScheduleFrontDTOS);
    }

    @GetMapping("dateFurthest/{id}")
        //@PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<List<ExaminationFrontDTO>> dateFurthest(@PathVariable Integer id) {


        List<Examination> examinationSchedule = new ArrayList<Examination>();
        examinationSchedule = examinationService.findAll();


        Collections.sort(examinationSchedule, new Comparator<Examination>() {
            public int compare(Examination o1, Examination o2) {
                return o1.getExaminationSchedule().getDate().compareTo(o2.getExaminationSchedule().getDate());
            }
        });
        Collections.reverse(examinationSchedule);

        List<ExaminationFrontDTO> examinationScheduleFrontDTOS = new ArrayList<ExaminationFrontDTO>();

        for (Examination ex : examinationSchedule) {

            if (id == ex.getPatient().getId()) {


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
                LocalDate date5 = LocalDate.now();
                if ((ex.getExaminationSchedule().getDate()).compareTo(date5) < 0) {
                    examinationScheduleFrontDTO.setFinished(true);
                } else {
                    examinationScheduleFrontDTO.setFinished(false);
                }
                examinationScheduleFrontDTOS.add(examinationScheduleFrontDTO);
            }
        }

        return examinationScheduleFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(examinationScheduleFrontDTOS);
    }

    @GetMapping("sortpricelowest/{id}")
        //@PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<List<ExaminationFrontDTO>> sortpricelowest(@PathVariable Integer id)
    {



        List<Examination> examinationSchedule = new ArrayList<Examination>();
        examinationSchedule = examinationService.findAll();

        Collections.sort(examinationSchedule, new Comparator<Examination>() {
            @Override
            public int compare(Examination c1, Examination c2) {
                return Double.compare(c1.getExaminationSchedule().getPrice(), c2.getExaminationSchedule().getPrice());

            }
        });
        List<ExaminationFrontDTO> examinationScheduleFrontDTOS = new ArrayList<ExaminationFrontDTO>();

        for( Examination ex : examinationSchedule){

            if(id == ex.getPatient().getId()) {


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
                LocalDate date5 = LocalDate.now();
                if((ex.getExaminationSchedule().getDate()).compareTo(date5)<0){
                    examinationScheduleFrontDTO.setFinished(true);
                }
                else{
                    examinationScheduleFrontDTO.setFinished(false);
                }
                examinationScheduleFrontDTOS.add(examinationScheduleFrontDTO);
            }
        }

        return examinationScheduleFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(examinationScheduleFrontDTOS);
    }

    @GetMapping("sortpricehighest/{id}")
        //@PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<List<ExaminationFrontDTO>> sortpricehighest(@PathVariable Integer id)
    {

        List<Examination> examinationSchedule = new ArrayList<Examination>();
        examinationSchedule = examinationService.findAll();

        Collections.sort(examinationSchedule, new Comparator<Examination>() {
            @Override
            public int compare(Examination c1, Examination c2) {
                return Double.compare(c1.getExaminationSchedule().getPrice(), c2.getExaminationSchedule().getPrice());

            }
        });
        Collections.reverse(examinationSchedule);
        List<ExaminationFrontDTO> examinationScheduleFrontDTOS = new ArrayList<ExaminationFrontDTO>();

        for( Examination ex : examinationSchedule){

            if(id == ex.getPatient().getId()) {


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
                LocalDate date5 = LocalDate.now();
                if((ex.getExaminationSchedule().getDate()).compareTo(date5)<0){
                    examinationScheduleFrontDTO.setFinished(true);
                }
                else{
                    examinationScheduleFrontDTO.setFinished(false);
                }
                examinationScheduleFrontDTOS.add(examinationScheduleFrontDTO);
            }
        }

        return examinationScheduleFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(examinationScheduleFrontDTOS);
    }


    @GetMapping("durationLongest/{id}")
        //@PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<List<ExaminationFrontDTO>> durationLongest(@PathVariable Integer id)
    {

        List<Examination> examinationSchedule = new ArrayList<Examination>();
        examinationSchedule = examinationService.findAll();

        Collections.sort(examinationSchedule, new Comparator<Examination>() {
            @Override
            public int compare(Examination c1, Examination c2) {
                return Double.compare(c1.getExaminationSchedule().getDuration(), c2.getExaminationSchedule().getDuration());

            }
        });
        Collections.reverse(examinationSchedule);
        List<ExaminationFrontDTO> examinationScheduleFrontDTOS = new ArrayList<ExaminationFrontDTO>();

        for( Examination ex : examinationSchedule) {

            if (id == ex.getPatient().getId()) {


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
                LocalDate date5 = LocalDate.now();
                if ((ex.getExaminationSchedule().getDate()).compareTo(date5) < 0) {
                    examinationScheduleFrontDTO.setFinished(true);
                } else {
                    examinationScheduleFrontDTO.setFinished(false);
                }
                examinationScheduleFrontDTOS.add(examinationScheduleFrontDTO);
            }
        }

        return examinationScheduleFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(examinationScheduleFrontDTOS);
    }


    @GetMapping("durationShortest/{id}")
        //@PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<List<ExaminationFrontDTO>> durationShortest(@PathVariable Integer id)
    {


        List<Examination> examinationSchedule = new ArrayList<Examination>();
        examinationSchedule = examinationService.findAll();

        Collections.sort(examinationSchedule, new Comparator<Examination>() {
            @Override
            public int compare(Examination c1, Examination c2) {
                return Double.compare(c1.getExaminationSchedule().getDuration(), c2.getExaminationSchedule().getDuration());

            }
        });
        List<ExaminationFrontDTO> examinationScheduleFrontDTOS = new ArrayList<ExaminationFrontDTO>();

        for( Examination ex : examinationSchedule){

            if(id == ex.getPatient().getId()) {


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
                LocalDate date5 = LocalDate.now();
                if((ex.getExaminationSchedule().getDate()).compareTo(date5)<0){
                    examinationScheduleFrontDTO.setFinished(true);
                }
                else{
                    examinationScheduleFrontDTO.setFinished(false);
                }
                examinationScheduleFrontDTOS.add(examinationScheduleFrontDTO);
            }
        }

        return examinationScheduleFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(examinationScheduleFrontDTOS);
    }

    @GetMapping("/cancelExamination/{id}")
    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<List<ExaminationFrontDTO>> ExaminationPatient(@PathVariable Integer id) {

        List<Examination> examinationSchedule = new ArrayList<Examination>();
        examinationSchedule = examinationService.findAll();


        List<ExaminationFrontDTO> examinationScheduleFrontDTOS = new ArrayList<ExaminationFrontDTO>();

        for( Examination ex : examinationSchedule){

                            if(id == ex.getPatient().getId()) {


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
                                LocalDate date5 = LocalDate.now();
                                if((ex.getExaminationSchedule().getDate()).compareTo(date5)<0){
                                    examinationScheduleFrontDTO.setFinished(true);
                                }
                                else{
                                    examinationScheduleFrontDTO.setFinished(false);
                                }
                                examinationScheduleFrontDTOS.add(examinationScheduleFrontDTO);
                            }
                            }

        return examinationScheduleFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(examinationScheduleFrontDTOS);

    }


    @GetMapping("/cancel/{id}")
    public ResponseEntity<String> cancel(@PathVariable Integer id)
    {
        LocalDate date = LocalDate.now().plusDays(1);
        Boolean able = false;
        List<ExaminationSchedule> examinationSchedule1 = new ArrayList<ExaminationSchedule>();
        examinationSchedule1 = examinationScheduleService.findAll();
        List<Examination> examinationSchedule = new ArrayList<Examination>();
        examinationSchedule = examinationService.findAll();
        Examination examination = new Examination();
        for( Examination ex : examinationSchedule){
            if(ex.getId() == id){
                examination = ex;
                LocalTime time = examination.getExaminationSchedule().getStartTime();
                if(examination.getExaminationSchedule().getDate().isBefore(date) && LocalTime.now().isBefore(time)) {
                    able = true;
                    examinationService.delete(ex);
                }
            }
        }

        if(able) {
            for (ExaminationSchedule es : examinationSchedule1) {
                if (es.getId() == examination.getExaminationSchedule().getId()) {

                    examinationScheduleService.update(es, false);

                }
            }
        }
        return able == true ?
                new ResponseEntity<>("You have successfully cancelled an appointment!", HttpStatus.CREATED) :
                new ResponseEntity<>("You are not able to cancel the appointment because it is in the next 24 hours!", HttpStatus.CREATED);


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


    public Boolean ableToRatePharmacist(Integer pharmacistId, Integer patientId){

        Boolean able = false;

        List<Consulting> consultings = consultingService.findAll();


        for(Consulting consulting: consultings){
            if(consulting.getPharmacist().getId() == pharmacistId && consulting.getPatient().getId()== patientId){
                able = true;

            }
        }



        return able;
    }


    @PostMapping("/leaveAMark")
    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<Pharmacy> leaveAMark(@RequestBody MarkDTO dto) {

        Boolean able =true;//ableToRatePharmacist(dto.getPharmacist().getId(), dto.getPatient().getId());

        if (able) {

            List<Mark> markList = markService.findAll();


            List<Pharmacy> list = pharmacyService.findAll();
            Pharmacy pharmacy = new Pharmacy();
            double mark = 0;
            int one = 0;
            int two = 0;
            int three = 0;
            int four = 0;
            int five = 0;

            Boolean hasPharmacy = false;
            Boolean hasPatient = false;

            for (Mark mark1 : markList) {
                if (mark1.getPharmacy().getId() == dto.getPharmacy().getId()) {
                    hasPharmacy = true;

                    if (mark1.getPatient().getId() == dto.getPatient().getId()) {
                        hasPatient = true;
                    }
                }
            }

            for (Pharmacy pharmacy1 : list) {

                if (dto.getPharmacy().getId() == pharmacy1.getId()) {
                    mark = pharmacy1.getMark();
                    pharmacy = pharmacy1;


                }
            }
            if (markList.size() == 0) {
                Mark mark2 = new Mark();
                if (dto.getMark() == 1) {
                    one += 1;
                    mark2.setMarkOne(one);
                } else if (dto.getMark() == 2) {
                    two += 1;
                    mark2.setMarkTwo(two);
                } else if (dto.getMark() == 3) {
                    three += 1;
                    mark2.setMarkThree(three);

                } else if (dto.getMark() == 4) {
                    four += 1;
                    mark2.setMarkFour(four);
                } else {
                    five += 1;
                    mark2.setMarkFive(five);
                }
                mark2.setPatient(dto.getPatient());
                mark2.setPharmacy(dto.getPharmacy());

                mark2.setPatientsMark(dto.getMark());
                Mark mark3 = markService.save(mark2);

                double ocena = (one * 1 + two * 2 + three * 3 + four * 4 + five * 5) / (one + two + three + four + five);
                pharmacy.setMark(ocena);
                pharmacyService.update(pharmacy);

            } else {
                for (Mark mark1 : markList) {
                    if (hasPharmacy) {
                        if (hasPatient) {
                            one = 0;
                            two = 0;
                            three = 0;
                            four = 0;
                            five = 0;
                            one = mark1.getMarkOne();
                            two = mark1.getMarkTwo();
                            three = mark1.getMarkThree();
                            four = mark1.getMarkFour();
                            five = mark1.getMarkFive();

                            int grade = mark1.getPatientsMark();

                            if (grade == 1) {
                                one -= 1;
                                mark1.setMarkOne(one);
                            } else if (grade == 2) {
                                two -= 1;
                                mark1.setMarkTwo(two);
                            } else if (grade == 3) {
                                three -= 1;
                                mark1.setMarkThree(three);

                            } else if (grade == 4) {
                                four -= 1;
                                mark1.setMarkFour(four);
                            } else {
                                five -= 1;
                                mark1.setMarkFive(five);
                            }

                            if (dto.getMark() == 1) {
                                one += 1;
                                mark1.setMarkOne(one);
                            } else if (dto.getMark() == 2) {
                                two += 1;
                                mark1.setMarkTwo(two);
                            } else if (dto.getMark() == 3) {
                                three += 1;
                                mark1.setMarkThree(three);

                            } else if (dto.getMark() == 4) {
                                four += 1;
                                mark1.setMarkFour(four);
                            } else {
                                five += 1;
                                mark1.setMarkFive(five);
                            }

                            mark1.setPatientsMark(dto.getMark());

                            Mark mark2 = markService.save(mark1);

                            double ocena = (one * 1 + two * 2 + three * 3 + four * 4 + five * 5) / (one + two + three + four + five);
                            pharmacy.setMark(ocena);
                            pharmacyService.update(pharmacy);
                        } else {
                            one = 0;
                            two = 0;
                            three = 0;
                            four = 0;
                            five = 0;
                            one = mark1.getMarkOne();
                            two = mark1.getMarkTwo();
                            three = mark1.getMarkThree();
                            four = mark1.getMarkFour();
                            five = mark1.getMarkFive();

                            if (dto.getMark() == 1) {
                                one += 1;
                                mark1.setMarkOne(one);
                            } else if (dto.getMark() == 2) {
                                two += 1;
                                mark1.setMarkTwo(two);
                            } else if (dto.getMark() == 3) {
                                three += 1;
                                mark1.setMarkThree(three);

                            } else if (dto.getMark() == 4) {
                                four += 1;
                                mark1.setMarkFour(four);
                            } else {
                                five += 1;
                                mark1.setMarkFive(five);
                            }
                            mark1.setPatientsMark(dto.getMark());
                            Mark mark2 = markService.save(mark1);

                            double ocena = (one * 1 + two * 2 + three * 3 + four * 4 + five * 5) / (one + two + three + four + five);
                            pharmacy.setMark(ocena);
                            pharmacyService.update(pharmacy);
                        }

                    } else {

                        Mark mark2 = new Mark();
                        if (dto.getMark() == 1) {
                            one += 1;
                            mark2.setMarkOne(one);
                        } else if (dto.getMark() == 2) {
                            two += 1;
                            mark2.setMarkTwo(two);
                        } else if (dto.getMark() == 3) {
                            three += 1;
                            mark2.setMarkThree(three);

                        } else if (dto.getMark() == 4) {
                            four += 1;
                            mark2.setMarkFour(four);
                        } else {
                            five += 1;
                            mark2.setMarkFive(five);
                        }
                        mark2.setPatient(dto.getPatient());
                        mark2.setPharmacy(dto.getPharmacy());
                        mark2.setPatientsMark(dto.getMark());
                        Mark mark3 = markService.save(mark2);

                        double ocena = (one * 1 + two * 2 + three * 3 + four * 4 + five * 5) / (one + two + three + four + five);
                        pharmacy.setMark(ocena);
                        pharmacyService.update(pharmacy);


                    }
                }
            }


            return pharmacy == null ?
                    new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                    ResponseEntity.ok(pharmacy);

        } else {
            return null;
        }
    }


        @GetMapping("/medicationAvailability/{code}")
        public ResponseEntity<List<PharmacyMedicationAvailabilityDTO>> getAvailability ( @PathVariable long code){

            List<PharmacyMedicationAvailabilityDTO> pharmacyAvailability = getAvailabilityInPharmacies(code);

            return pharmacyAvailability == null ?
                    new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                    ResponseEntity.ok(pharmacyAvailability);
        }

        private List<PharmacyMedicationAvailabilityDTO> getAvailabilityInPharmacies ( long code){
            List<PharmacyMedicationAvailabilityDTO> pharmacyList = new ArrayList<>();
            List<Pharmacy> pharmacies = pharmacyService.findAll();
            for (Pharmacy pharmacy : pharmacies) {
                for (MedicationPrice medicationPrice : pharmacy.getMedicationPrices()) {
                    if (medicationPrice.getMedication().getCode() == code) {
                        pharmacyList.add(new PharmacyMedicationAvailabilityDTO(pharmacy.getId(), medicationPrice.getPrice(), pharmacy.getMark(),
                                new AddressDTO(pharmacy.getAddress().getTown(), pharmacy.getAddress().getStreet(), pharmacy.getAddress().getNumber(),
                                        pharmacy.getAddress().getPostalCode(), pharmacy.getAddress().getCountry()), pharmacy.getPharmacyName()));
                    } else {
                    }
                }

            }
            return pharmacyList;
        }


    @GetMapping("checkForPharmacy/{pharmacyId}")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<String> checkPossibilityPharmacy(@PathVariable Integer pharmacyId)
    {
        Boolean hasEreceipt = ePrescriptionService.checkEReceiptInPharmacy(pharmacyId);
        Boolean hasConsulting = consultingService.checkIfPatientHasConsulting(pharmacyId);
        Boolean hasExamination = examinationService.checkIfPatientHasExamination(pharmacyId);

        return (hasEreceipt == false && hasConsulting==false)?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok("Successfully");
    }





    @GetMapping("from1to5")
    //@PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<List<PharmacyFrontDTO>> from1to5()
    {
       List<PharmacyFrontDTO> pharmacyFrontDTOS = new ArrayList<PharmacyFrontDTO>();
        List<Pharmacy> pharmacies = pharmacyService.findAll();
       //for(Pharmacy pharmacy: pharmacies) {
        Collections.sort(pharmacies, new Comparator<Pharmacy>() {
            @Override
            public int compare(Pharmacy c1, Pharmacy c2) {
                return Double.compare(c1.getMark(), c2.getMark());
            }
        });


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

    @GetMapping("from5to1")
        //@PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<List<PharmacyFrontDTO>> from5to1()
    {
        List<PharmacyFrontDTO> pharmacyFrontDTOS = new ArrayList<PharmacyFrontDTO>();
        List<Pharmacy> pharmacies = pharmacyService.findAll();
        //for(Pharmacy pharmacy: pharmacies) {
        Collections.sort(pharmacies, new Comparator<Pharmacy>() {
            @Override
            public int compare(Pharmacy c1, Pharmacy c2) {
                return Double.compare(c1.getMark(), c2.getMark());

            }
        });

        Collections.reverse(pharmacies);

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



    @GetMapping("cityfromztoa")
        //@PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<List<PharmacyFrontDTO>> cityfromztoa()
    {
        List<PharmacyFrontDTO> pharmacyFrontDTOS = new ArrayList<PharmacyFrontDTO>();
        List<Pharmacy> pharmacies = pharmacyService.findAll();
        //for(Pharmacy pharmacy: pharmacies) {

        Collections.sort(pharmacies, new Comparator<Pharmacy>() {
            @Override
            public int compare(Pharmacy c1, Pharmacy c2) {
                return CharSequence.compare(c1.getAddress().getTown(), c2.getAddress().getTown());

            }
        });

        Collections.reverse(pharmacies);

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

    @GetMapping("cityfromatoz")
        //@PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<List<PharmacyFrontDTO>> cityfromatoz()
    {
        List<PharmacyFrontDTO> pharmacyFrontDTOS = new ArrayList<PharmacyFrontDTO>();
        List<Pharmacy> pharmacies = pharmacyService.findAll();
        //for(Pharmacy pharmacy: pharmacies) {
        Collections.sort(pharmacies, new Comparator<Pharmacy>() {
            @Override
            public int compare(Pharmacy c1, Pharmacy c2) {
                return CharSequence.compare(c1.getAddress().getTown(), c2.getAddress().getTown());

            }
        });


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

    @GetMapping("namefromztoa")
        //@PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<List<PharmacyFrontDTO>> namefromztoa()
    {
        List<PharmacyFrontDTO> pharmacyFrontDTOS = new ArrayList<PharmacyFrontDTO>();
        List<Pharmacy> pharmacies = pharmacyService.findAll();
        //for(Pharmacy pharmacy: pharmacies) {
        Collections.sort(pharmacies, new Comparator<Pharmacy>() {
            @Override
            public int compare(Pharmacy c1, Pharmacy c2) {
                return CharSequence.compare(c1.getPharmacyName(), c2.getPharmacyName());

            }
        });
        Collections.reverse(pharmacies);

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
    @GetMapping("namefromatoz")
        //@PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<List<PharmacyFrontDTO>> namefromatoz()
    {
        List<PharmacyFrontDTO> pharmacyFrontDTOS = new ArrayList<PharmacyFrontDTO>();
        List<Pharmacy> pharmacies = pharmacyService.findAll();
        //for(Pharmacy pharmacy: pharmacies) {
        Collections.sort(pharmacies, new Comparator<Pharmacy>() {
            @Override
            public int compare(Pharmacy c1, Pharmacy c2) {
                return CharSequence.compare(c1.getPharmacyName(), c2.getPharmacyName());

            }
        });


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

    @GetMapping("filterMark/{i}")
        //@PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<List<PharmacyFrontDTO>> filterMark(@PathVariable Integer i)
    {
        List<PharmacyFrontDTO> pharmacyFrontDTOS = new ArrayList<PharmacyFrontDTO>();
        List<Pharmacy> pharmacies = pharmacyService.findAll();
        //for(Pharmacy pharmacy: pharmacies) {

        for (Pharmacy ph : pharmacies){
            if(ph.getMark()>=i && ph.getMark()<=i+1) {
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
}
