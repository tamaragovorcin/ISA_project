package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.DTO.MarkDTO;
import com.isaproject.isaproject.DTO.DermatologistDTO;
import com.isaproject.isaproject.DTO.UserBasicInfoDTO;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Examinations.Examination;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;

import com.isaproject.isaproject.Model.Users.*;

import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.MarkDermatologist;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.PharmacyAdmin;
import com.isaproject.isaproject.Service.Implementations.*;
import com.isaproject.isaproject.Validation.CommonValidatior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/dermatologist")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DermatologistController {
    @Autowired
    DermatologistService dermatologistService;

    @Autowired
    MarkDermatologistService markService;

    @Autowired
    PatientService patientService;

    @Autowired
    ExaminationService examinationService;

    @Autowired
    ExaminationScheduleService examinationScheduleService;

    @Autowired
    PharmacyAdminService pharmacyAdminService;


    @PostMapping("/register")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<String> addUser(@RequestBody DermatologistDTO userRequest) {

        CommonValidatior commonVlidatior = new CommonValidatior();
        if(!commonVlidatior.checkValidationDermatologist(userRequest)) {
            throw new IllegalArgumentException("Please fill in all the fields correctly!");
        }
        if(!userRequest.getPassword().equals(userRequest.getRewritePassword())) {
            throw new IllegalArgumentException("Please make sure your password and rewrite password match!");
        }
        PersonUser existUser = dermatologistService.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            throw new ResourceConflictException("Entered email already exists", "Email already exists");
        }
        PersonUser user = dermatologistService.save(userRequest);
        return new ResponseEntity<>("Dermatologist is successfully registred!", HttpStatus.CREATED);
    }

    @GetMapping("/account")
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<Dermatologist> getMyAccount()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        Dermatologist dermatologist = dermatologistService.findById(user.getId());
        return dermatologist == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(dermatologist);
    }


    @PostMapping("/update")
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    ResponseEntity<Dermatologist> update(@RequestBody DermatologistDTO person)
    {

        Dermatologist patient = dermatologistService.update(person);
        return patient == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patient);
    }
    @GetMapping("/front")
    @PreAuthorize("hasAnyRole('PATIENT', 'SUPPLIER', 'SYSTEM_ADMIN', 'DERMATOLOGIST', 'PHARMACIST')")
    ResponseEntity<List<DermatologistsFrontDTO>> getAllFrontDermatologists()
    {
        List<DermatologistsFrontDTO> dermatologists = new ArrayList<DermatologistsFrontDTO>();
        for(Dermatologist dermatologist:   dermatologistService.findAll()){
            List<String> pharmacies = new ArrayList<>();
            for(Pharmacy pharmacy : dermatologist.getPharmacies()){
                pharmacies.add(pharmacy.getPharmacyName());
            }
            dermatologists.add(new DermatologistsFrontDTO(dermatologist.getName(),dermatologist.getSurname(),dermatologist.getMarkDermatologist(),pharmacies));

        }
        return dermatologists == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(dermatologists);
    }
    @GetMapping("/searchPharmacy/{name}")
    @PreAuthorize("hasAnyRole('PATIENT', 'SUPPLIER', 'SYSTEM_ADMIN', 'DERMATOLOGIST', 'PHARMACIST')")
    ResponseEntity<List<DermatologistsFrontDTO>> getByPharmacy(@PathVariable String name)
    {
        List<Dermatologist> dermatologists= dermatologistService.findByPharmacy(name);
        List<DermatologistsFrontDTO> dermatologistsFrontDTOS = new ArrayList<>();
        for (Dermatologist dermatologist: dermatologists) {
            List<String> pharmacies = new ArrayList<>();
            for(Pharmacy pharmacy : dermatologist.getPharmacies()){
                pharmacies.add(pharmacy.getPharmacyName());
            }
            DermatologistsFrontDTO dermatologistsFrontDTO = new DermatologistsFrontDTO(dermatologist.getName(),dermatologist.getSurname(),dermatologist.getMarkDermatologist(),pharmacies);
            dermatologistsFrontDTOS.add(dermatologistsFrontDTO);
        }
        return dermatologistsFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(dermatologistsFrontDTOS);
    }

    @GetMapping("/searchMark/{MarkMin}/{MarkMax}")
    @PreAuthorize("hasAnyRole('PATIENT', 'SUPPLIER', 'SYSTEM_ADMIN', 'DERMATOLOGIST', 'PHARMACIST')")
    ResponseEntity<List<DermatologistsFrontDTO>> getAllByMark(@PathVariable int MarkMin,@PathVariable int MarkMax )
    {
        List<Dermatologist> dermatologists= dermatologistService.findByMark(MarkMin,MarkMax);
        List<DermatologistsFrontDTO> dermatologistsFrontDTOS = new ArrayList<>();
        for (Dermatologist dermatologist: dermatologists) {
            List<String> pharmacies = new ArrayList<>();
            for(Pharmacy pharmacy : dermatologist.getPharmacies()){
                pharmacies.add(pharmacy.getPharmacyName());
            }
            DermatologistsFrontDTO dermatologistsFrontDTO = new DermatologistsFrontDTO(dermatologist.getName(),dermatologist.getSurname(),dermatologist.getMarkDermatologist(),pharmacies);
            dermatologistsFrontDTOS.add(dermatologistsFrontDTO);
        }
        return dermatologistsFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(dermatologistsFrontDTOS);
    }
    @PostMapping("/searchName")
    @PreAuthorize("hasAnyRole('PATIENT', 'SUPPLIER', 'SYSTEM_ADMIN', 'DERMATOLOGIST', 'PHARMACIST')")
    ResponseEntity<List<DermatologistsFrontDTO>> getAllByName(@RequestBody PharmacistSearchDTO dto)
    {
        List<Dermatologist> dermatologists= dermatologistService.findByName(dto.getFirstName(),dto.getSurName());
        List<DermatologistsFrontDTO> dermatologistsFrontDTOS = new ArrayList<>();
        for (Dermatologist dermatologist: dermatologists) {
            List<String> pharmacies = new ArrayList<>();
            for(Pharmacy pharmacy : dermatologist.getPharmacies()){
                pharmacies.add(pharmacy.getPharmacyName());
            }
            DermatologistsFrontDTO dermatologistsFrontDTO = new DermatologistsFrontDTO(dermatologist.getName(),dermatologist.getSurname(),dermatologist.getMarkDermatologist(),pharmacies);
            dermatologistsFrontDTOS.add(dermatologistsFrontDTO);
        }
        return dermatologistsFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(dermatologistsFrontDTOS);
    }


    @GetMapping("/allDermatologistsFront")
    @PreAuthorize("hasAnyRole('PATIENT', 'SUPPLIER', 'SYSTEM_ADMIN', 'DERMATOLOGIST', 'PHARMACIST','PHARMACY_ADMIN')")
    List<DermatologistFrontDTO> getDermatologists()
    {
        List<DermatologistFrontDTO> dermatologists = new ArrayList<DermatologistFrontDTO>();
        for(Dermatologist derm : dermatologistService.findAll()){
            DermatologistFrontDTO dermatologistFrontDTO = new DermatologistFrontDTO();
            dermatologistFrontDTO.setFirstname(derm.getName());
            dermatologistFrontDTO.setSurname(derm.getSurname());
            dermatologistFrontDTO.setEmail(derm.getEmail());
            dermatologistFrontDTO.setId(derm.getId());
            dermatologistFrontDTO.setPhonenumber(derm.getPhoneNumber());
            dermatologistFrontDTO.setMarkDermatologist(derm.getMarkDermatologist());
            dermatologists.add(dermatologistFrontDTO);
        }

        return dermatologists;
    }
    @GetMapping("/notInPharmacy")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    List<DermatologistFrontDTO> getDermatologistsNot()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        List<DermatologistFrontDTO> dermatologists = new ArrayList<>();
        List<Dermatologist> dermatologists1 = dermatologistService.findAll();
        for(Dermatologist  dermatologist : pharmacyAdmin.getPharmacy().getDermatologists()){
          for(Dermatologist derm : dermatologistService.findAll()){
              if(derm.getId() == dermatologist.getId()) {
                  dermatologists1.remove(derm);
              }
        }
        }
        for (Dermatologist dermatologist : dermatologists1){
            DermatologistFrontDTO dermatologistFrontDTO = new DermatologistFrontDTO();
            System.out.println("IMEEE"+dermatologist.getName());
            dermatologistFrontDTO.setFirstname(dermatologist.getName());
            dermatologistFrontDTO.setSurname(dermatologist.getSurname());
            dermatologistFrontDTO.setEmail(dermatologist.getEmail());
            dermatologistFrontDTO.setId(dermatologist.getId());
            dermatologistFrontDTO.setPhonenumber(dermatologist.getPhoneNumber());
            dermatologistFrontDTO.setMarkDermatologist(dermatologist.getMarkDermatologist());
            dermatologists.add(dermatologistFrontDTO);
        }

        return dermatologists;
    }

    @PostMapping("/addPharmacy")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<String> addPharmacy(@RequestBody DermaotlogistPharmacyDTO dto) {
        if(dermatologistService.addPharmacy(dto)){
            return new ResponseEntity<>("Dermatologist is successfully added as employee in pharmacy!", HttpStatus.CREATED);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/basicInfo")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<UserBasicInfoDTO>> getDermatologistsBasicInfo()
    {   List<UserBasicInfoDTO> basicInfos = new ArrayList<>();
        List<Dermatologist> dermatologists = dermatologistService.findAll();
        for (Dermatologist dermatologist: dermatologists) {
            basicInfos.add(new UserBasicInfoDTO(dermatologist.getName() + " " + dermatologist.getSurname(), dermatologist.getEmail(), dermatologist.getId()));
        }
        return basicInfos == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(basicInfos);
    }
    @GetMapping("")
    public ResponseEntity<List<Dermatologist>> getAll()
    {
        List<Dermatologist> dermatologists = dermatologistService.findAll();
        return dermatologists == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(dermatologists);
    }

    public Boolean ableToRateDermatologist(Integer dermatologistId, Integer patientId){

        Boolean able = false;
        List<Examination> examinations = examinationService.findAll();
        List<ExaminationSchedule> examinationSchedules = examinationScheduleService.findAll();

        Boolean found = false;
        Integer examinationId = -1;

        for(ExaminationSchedule examinationSchedule: examinationSchedules){
            if(examinationSchedule.getDermatologist().getId() == dermatologistId){
                found=true;
                examinationId = examinationSchedule.getId();
            }
        }

        if(found=true) {
            for (Examination examination : examinations) {
                if(examination.getExaminationSchedule().getId() == examinationId && examination.getPatient().getId() == patientId){
                    if(examination.getShowedUp()){
                    able = true;
                }}
            }
        }

        return able;
    }



    @PostMapping("/leaveAMark")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<String> leaveAMark(@RequestBody MarkDTO dto) {


        Boolean able = false;

        able = ableToRateDermatologist(dto.getDermatologist(), dto.getPatient());

        if(able) {


            List<MarkDermatologist> markList = markService.findAll();
            List<Dermatologist> list = dermatologistService.findAll();
            Dermatologist dermatologist = new Dermatologist();


            double mark = 0;
            int one = 0;
            int two = 0;
            int three = 0;
            int four = 0;
            int five = 0;

            Boolean hasDermatologist = true;
            Boolean hasPatient = false;

            for (MarkDermatologist mark1 : markList) {
                if (mark1.getDermatologist().getId() == dto.getDermatologist() && mark1.getPatient().getId() == dto.getPatient()) {
                    hasDermatologist = false;

                    if (mark1.getPatient().getId() != dto.getPatient() && mark1.getDermatologist().getId() == dto.getDermatologist()) {
                        hasPatient = true;
                    }
                }
            }

            dermatologist = dermatologistService.findById(dto.getDermatologist());

            if(hasDermatologist == true){
                MarkDermatologist mark2 = new MarkDermatologist();
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
                Patient patient = patientService.findById(dto.getPatient());
                mark2.setPatient(patient);
                Dermatologist dermatologist1 = dermatologistService.findById(dto.getDermatologist());
                mark2.setDermatologist(dermatologist1);
                mark2.setPatientsMark(dto.getMark());
                MarkDermatologist mark3 = markService.save(mark2);

            }




            for (MarkDermatologist mark1 : markList) {
                if (mark1.getPatient().getId() == dto.getPatient() && mark1.getDermatologist().getId() == dto.getDermatologist()) {

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

                    MarkDermatologist mark2 = markService.save(mark1);

                } else if(mark1.getPatient().getId() != dto.getPatient() && mark1.getDermatologist().getId() == dto.getDermatologist() && hasPatient) {

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
                    MarkDermatologist mark2 = new MarkDermatologist();
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
                    mark2.setPatientsMark(dto.getMark());
                    Patient patient = patientService.findById(dto.getPatient());
                    mark2.setPatient(patient);
                    Dermatologist dermatologist1 = dermatologistService.findById(dto.getDermatologist());
                    mark2.setDermatologist(dermatologist1);
                    MarkDermatologist mark4 = markService.save(mark2);


                }


            }


            List<MarkDermatologist> marks = markService.findAll();

            one = 0;
            two = 0;
            three = 0;
            four = 0;
            five = 0;

            for(MarkDermatologist mark4 : marks) {
                if (mark4.getDermatologist().getId() == dto.getDermatologist()) {
                    one += mark4.getMarkOne();
                    two += mark4.getMarkTwo();
                    three += mark4.getMarkThree();
                    four += mark4.getMarkFour();
                    five += mark4.getMarkFive();
                }
            }
            if(one+two+three+four+five == 0){
                able= false;
            }
            else {
                double ocena = (one * 1 + two * 2 + three * 3 + four * 4 + five * 5) / (one + two + three + four + five);

                dermatologist.setMarkDermatologist(ocena);
                dermatologistService.updateMark(dermatologist);

            }


        }
        return able == true ?
                new ResponseEntity<>("You have successfully rated a dermatologist!", HttpStatus.CREATED) :
                new ResponseEntity<>("You are not able to leave a mark for the dermatologist that you had not had an appointment with!", HttpStatus.CREATED);

    }

    @GetMapping("/examinationSchedule")
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    ResponseEntity<Set<ExaminationScheduleFrontDTO>> getOurExSch() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser) currentUser.getPrincipal();
        Dermatologist dermatologist = dermatologistService.findById(user.getId());
        HashSet<ExaminationScheduleFrontDTO> cons = new HashSet<>();

        for (ExaminationSchedule c : dermatologist.getExaminationSchedules()) {

                cons.add(new ExaminationScheduleFrontDTO(c.getId(),dermatologist.getName(), dermatologist.getSurname(),  c.getPharmacy().getPharmacyName(),  c.getDate(), c.getStartTime(), c.getDuration(), c.getPrice()));
        }
        return dermatologist.getExaminationSchedules() == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(cons);
    }

    @GetMapping("/examinations")
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    ResponseEntity<Set<ExaminationNoteDTO>> getOurExamination() {
        //System.out.println("Pogoiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser) currentUser.getPrincipal();
        Dermatologist dermatologist = dermatologistService.findById(user.getId());
        HashSet<ExaminationNoteDTO> cons = new HashSet<>();

        for (Examination e : examinationService.findAll()) {
            if (e.getExaminationSchedule().getDate().isAfter(LocalDate.now()) && e.getExaminationSchedule().getDermatologist().getId()== user.getId())
                cons.add(new ExaminationNoteDTO(e.getId(), e.getPatient().getId(), e.getPatient().getName(), e.getPatient().getSurname(), e.getExaminationSchedule().getPharmacy().getPharmacyName(), e.getExaminationSchedule().getDate(), e.getExaminationSchedule().getStartTime()));

        }
        return cons == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(cons);
    }

    @GetMapping("/myPatients")
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    ResponseEntity<Set<PatientForFrontDTO>> getOurPatients() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser) currentUser.getPrincipal();
       Dermatologist dermatologist = dermatologistService.findById(user.getId());
        HashSet<PatientForFrontDTO> persons = new HashSet<>();

        for (Examination e : examinationService.findAll()) {
            if (e.getExaminationSchedule().getDermatologist().getId() == dermatologist.getId() && !persons.contains(e))
                persons.add(new PatientForFrontDTO(e.getPatient().getId(),e.getPatient().getEmail(), e.getPatient().getName(), e.getPatient().getSurname(), e.getPatient().getPhoneNumber()));
        }
        return persons== null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(persons);
    }

}
