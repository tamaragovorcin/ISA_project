package com.isaproject.isaproject.Controller;


import com.isaproject.isaproject.Authentification.TokenUtils;
import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.Examinations.Examination;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.Pharmacy.Actions;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.Mark;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Service.IServices.IPersonUserService;
import com.isaproject.isaproject.Service.Implementations.*;
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
import java.time.LocalDate;
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
    MarkService markService;
    @Autowired
    ActionsService  actionsService;
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
    public ResponseEntity<Examination> addExamination(@RequestBody ExaminationDTO dto) {

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


        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(dto.getPatient().getEmail());
        mail.setSubject("Successfuly reserved dermatologist apointment!");
        mail.setFrom(environment.getProperty("spring.mail.username"));
        //mail.setFrom("pharmacyisa@gmail.com");
        mail.setText("You have successfully reserved an appointment on : "
                + examinationSchedule1.getDate() +" at " + examinationSchedule1.getStartTime()+ ". Your doctor is "+ examinationSchedule1.getDermatologist().getName() + " " + examinationSchedule1.getDermatologist().getSurname());

        mailSender.send(mail);

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
    void cancel(@PathVariable Integer id)
    {
        List<ExaminationSchedule> examinationSchedule1 = new ArrayList<ExaminationSchedule>();
        examinationSchedule1 = examinationScheduleService.findAll();
        List<Examination> examinationSchedule = new ArrayList<Examination>();
        examinationSchedule = examinationService.findAll();
        Examination examination = new Examination();
        for( Examination ex : examinationSchedule){
            if(ex.getId() == id){
                examination = ex;
                examinationService.delete(ex);
            }
        }




        for(ExaminationSchedule es: examinationSchedule1){
            if(es.getId()==examination.getExaminationSchedule().getId()){


                examinationScheduleService.update(es, false);

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

    @PostMapping("/leaveAMark")
    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<Pharmacy> leaveAMark(@RequestBody MarkDTO dto) {
        /*Boolean find = markService.find();
        if(find == false){
        List<Mark> markList = markService.findAll();}*/

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
                    hasPatient =true;
                }
                }
                }

        for (Pharmacy pharmacy1 : list) {

            if (dto.getPharmacy().getId() == pharmacy1.getId()) {
                mark = pharmacy1.getMark();
                pharmacy = pharmacy1;


            }
        }
    if(markList.size()==0){
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

        double ocena = (one * 1 + two * 2 + three * 3 + four * 4 + five * 5)/(one+two+three+four+five);
        pharmacy.setMark(ocena);
        pharmacyService.update(pharmacy);

    }else {
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
                }




                else {
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

        }

}
