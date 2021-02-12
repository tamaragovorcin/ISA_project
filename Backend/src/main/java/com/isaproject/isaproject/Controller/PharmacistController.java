package com.isaproject.isaproject.Controller;
import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Users.*;
import com.isaproject.isaproject.Service.Implementations.ConsultingService;
import com.isaproject.isaproject.Service.Implementations.MarkPharmacistService;
import com.isaproject.isaproject.Service.Implementations.PharmacistService;
import com.isaproject.isaproject.Service.Implementations.PharmacyService;
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
@RequestMapping("/api/pharmacist")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PharmacistController {
    @Autowired
    PharmacistService pharmacistService;

    @Autowired
    MarkPharmacistService markService;

    @Autowired
    ConsultingService consultingService;

    @Autowired
    PharmacyService pharmacyService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<String> addUser(@RequestBody PharmacistDTO userRequest) {
        if(userRequest.getPassword().isEmpty() || userRequest.getRewritePassword().isEmpty()) {
            throw new IllegalArgumentException("Please fill all the required fields!");
        }
        if(!userRequest.getPassword().equals(userRequest.getRewritePassword())) {
            throw new IllegalArgumentException("Please make sure your password and rewrite password match!");
        }

        PersonUser existUser = pharmacistService.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getEmail(), "Email already exists");
        }
        Pharmacist user = pharmacistService.save(userRequest);
        return new ResponseEntity<>("Pharmacist is successfully registred!", HttpStatus.CREATED);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    ResponseEntity<List<Pharmacist>> getAll() {
        List<Pharmacist> pharmacists = pharmacistService.findAll();
        return pharmacists == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacists);
    }

    @GetMapping("/searchPharmacy/{name}")
    @PreAuthorize("hasAnyRole('PATIENT', 'SUPPLIER', 'SYSTEM_ADMIN', 'DERMATOLOGIST', 'PHARMACIST')")
    ResponseEntity<List<PharmacistFrontDTO>> getByPharmacy(@PathVariable String name)
    {
        List<Pharmacist> pharmacists= pharmacistService.findByPharmacy(name);
        List<PharmacistFrontDTO> pharmacistFrontDTOS = new ArrayList<>();
        for (Pharmacist pharmacist: pharmacists) {
            PharmacistFrontDTO pharmacyFrontDTO = new PharmacistFrontDTO(pharmacist.getName(),pharmacist.getSurname(),pharmacist.getMarkPharmacist(),pharmacist.getPharmacy().getPharmacyName());
            pharmacistFrontDTOS.add(pharmacyFrontDTO);
        }
        return pharmacistFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacistFrontDTOS);
    }

    @GetMapping("/searchMark/{MarkMin}/{MarkMax}")
    @PreAuthorize("hasAnyRole('PATIENT', 'SUPPLIER', 'SYSTEM_ADMIN', 'DERMATOLOGIST', 'PHARMACIST')")
    ResponseEntity<List<PharmacistFrontDTO>> getAllByMark(@PathVariable int MarkMin,@PathVariable int MarkMax )
    {
        List<Pharmacist> pharmacists= pharmacistService.findByMark(MarkMin,MarkMax);
        List<PharmacistFrontDTO> pharmacistFrontDTOS = new ArrayList<>();
        for (Pharmacist pharmacist: pharmacists) {
            PharmacistFrontDTO pharmacyFrontDTO = new PharmacistFrontDTO(pharmacist.getName(),pharmacist.getSurname(),pharmacist.getMarkPharmacist(),pharmacist.getPharmacy().getPharmacyName());
            pharmacistFrontDTOS.add(pharmacyFrontDTO);
        }

        return  ResponseEntity.ok(pharmacistFrontDTOS);
    }
    @PostMapping("/searchName")
    @PreAuthorize("hasAnyRole('PATIENT', 'SUPPLIER', 'SYSTEM_ADMIN', 'DERMATOLOGIST', 'PHARMACIST')")
    ResponseEntity<List<PharmacistFrontDTO>> getAllByName(@RequestBody PharmacistSearchDTO dto)
    {
        List<Pharmacist> pharmacists= pharmacistService.findByName(dto.getFirstName(),dto.getSurName());
        List<PharmacistFrontDTO> pharmacistFrontDTOS = new ArrayList<>();
        for (Pharmacist pharmacist: pharmacists) {
            PharmacistFrontDTO pharmacyFrontDTO = new PharmacistFrontDTO(pharmacist.getName(),pharmacist.getSurname(),pharmacist.getMarkPharmacist(),pharmacist.getPharmacy().getPharmacyName());
            pharmacistFrontDTOS.add(pharmacyFrontDTO);
        }


        return  ResponseEntity.ok(pharmacistFrontDTOS);
    }


    @PostMapping("/update")
    @PreAuthorize("hasRole('PHARMACIST')")
    ResponseEntity<Pharmacist> update(@RequestBody PharmacistDTO person)
    {

        Pharmacist patient = pharmacistService.update(person);
        return patient == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patient);
    }


    @GetMapping("/front")
    @PreAuthorize("hasAnyRole('PATIENT', 'SUPPLIER', 'SYSTEM_ADMIN', 'DERMATOLOGIST', 'PHARMACIST')")
    ResponseEntity<List<PharmacistFrontDTO>> getAllFront()
    {
        List<PharmacistFrontDTO> pharmacists = new ArrayList<PharmacistFrontDTO>();
        for(Pharmacist pharmacist:   pharmacistService.findAll()){
            pharmacists.add(new PharmacistFrontDTO(pharmacist.getName(),pharmacist.getSurname(),pharmacist.getMarkPharmacist(),pharmacist.getPharmacy().getPharmacyName()));
        }
        return pharmacists == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacists);
    }


    public Boolean ableToRatePharmacist(Integer pharmacistId, Integer patientId) {
        Boolean able = false;
        List<Consulting> consultings = consultingService.findAll();
        for (Consulting consulting : consultings) {
            if (consulting.getPatient().getId() == patientId && consulting.getPharmacist().getId() == pharmacistId) {
                able = true;
            }
        }
        return able;
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<String> deletePharmacist(@PathVariable Integer id) {
        Pharmacist pharmacist = pharmacistService.findById(id);
        String answer = pharmacistService.delete(pharmacist);
        return new ResponseEntity<>(answer, HttpStatus.CREATED);
    }

    @GetMapping("/account")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<Pharmacist> getMyAccount()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        Pharmacist pharmacist = pharmacistService.findById(user.getId());
        return pharmacist == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacist);
    }

    @GetMapping("/basicInfo")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<UserBasicInfoDTO>> getPharmacistsBasicInfo() {
        List<UserBasicInfoDTO> basicInfos = new ArrayList<>();
        List<Pharmacist> pharmacists = pharmacistService.findAll();
        for (Pharmacist pharmacist : pharmacists) {
            basicInfos.add(new UserBasicInfoDTO(pharmacist.getName() + " " + pharmacist.getSurname(), pharmacist.getEmail(), pharmacist.getId()));
        }
        return basicInfos == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(basicInfos);
    }

    @PostMapping("/leaveAMark")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<String> leaveAMark(@RequestBody MarkDTO dto) {

        String response = "";
        Boolean able = ableToRatePharmacist(dto.getPharmacist().getId(), dto.getPatient().getId());

        if (able) {

            List<MarkPharmacist> markList = markService.findAll();
            List<Pharmacist> list = pharmacistService.findAll();
            Pharmacist pharmacist = new Pharmacist();

            double mark = 0;
            int one = 0;
            int two = 0;
            int three = 0;
            int four = 0;
            int five = 0;

            Boolean hasPharmacist = false;
            Boolean hasPatient = false;

            for (MarkPharmacist mark1 : markList) {
                if (mark1.getPharmacist().getId() == dto.getPharmacist().getId() && mark1.getPatient().getId() == dto.getPatient().getId()) {
                    hasPharmacist = false;

                    if (mark1.getPatient().getId() != dto.getPatient().getId() && mark1.getPharmacist().getId() == dto.getPharmacist().getId()) {
                        hasPatient = true;
                    }
                }
            }

            pharmacist = pharmacistService.findById(dto.getPharmacist().getId());

            if(hasPharmacist == true){
                MarkPharmacist mark2 = new MarkPharmacist();
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
                mark2.setPharmacist(dto.getPharmacist());
                mark2.setPatientsMark(dto.getMark());
                MarkPharmacist mark3 = markService.save(mark2);

            }




            if (markList.size() == 0) {
                MarkPharmacist mark2 = new MarkPharmacist();
                if (dto.getMark() == 1) {
                    one = 1;
                    mark2.setMarkOne(one);
                } else if (dto.getMark() == 2) {
                    two = 1;
                    mark2.setMarkTwo(two);
                } else if (dto.getMark() == 3) {
                    three = 1;
                    mark2.setMarkThree(three);

                } else if (dto.getMark() == 4) {
                    four = 1;
                    mark2.setMarkFour(four);
                } else {
                    five = 1;
                    mark2.setMarkFive(five);
                }
                mark2.setPatient(dto.getPatient());
                mark2.setPharmacist(dto.getPharmacist());

                mark2.setPatientsMark(dto.getMark());
                MarkPharmacist mark3 = markService.save(mark2);


            } else {
                for (MarkPharmacist mark1 : markList) {
                    if (mark1.getPatient().getId() == dto.getPatient().getId() && mark1.getPharmacist().getId() == dto.getPharmacist().getId()) {

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

                        MarkPharmacist mark2 = markService.save(mark1);

                    } else if(mark1.getPatient().getId() != dto.getPatient().getId() && mark1.getPharmacist().getId() == dto.getPharmacist().getId() && hasPatient) {

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
                        MarkPharmacist mark2 = new MarkPharmacist();
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
                        mark2.setPatient(dto.getPatient());
                        mark2.setPharmacist(dto.getPharmacist());
                        MarkPharmacist mark4 = markService.save(mark2);


                    }

                }
            }


            List<MarkPharmacist> marks = markService.findAll();

            one = 0;
            two = 0;
            three = 0;
            four = 0;
            five = 0;

            for(MarkPharmacist mark4 : marks) {
                if (mark4.getPharmacist().getId() == dto.getPharmacist().getId()) {
                    one += mark4.getMarkOne();
                    two += mark4.getMarkTwo();
                    three += mark4.getMarkThree();
                    four += mark4.getMarkFour();
                    five += mark4.getMarkFive();
                }
            }
            System.out.println(one + two + three + four + five);
            double ocena = (one * 1 + two * 2 + three * 3 + four * 4 + five * 5) / (one + two + three + four + five);
            System.out.println(ocena);
            pharmacist.setMarkPharmacist(ocena);
            pharmacistService.updateMark(pharmacist);



        }

        return able == true ?
                new ResponseEntity<>("You have successfully rated a pharmacist!", HttpStatus.CREATED) :
                new ResponseEntity<>("You are not able to leave a mark for the pharmacist that you had not had a consultation with!", HttpStatus.CREATED);

    }


    @GetMapping("/consultings")
    @PreAuthorize("hasRole('PHARMACIST')")
    ResponseEntity<Set<ConsultingNoteDTO>> getOurConsultings() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser) currentUser.getPrincipal();
        Pharmacist pharmacyAdmin = pharmacistService.findById(user.getId());
        HashSet<ConsultingNoteDTO> cons = new HashSet<>();

        for (Consulting c : pharmacyAdmin.getConsulting()) {
            if (c.getDate().isAfter(LocalDate.now()))
                cons.add(new ConsultingNoteDTO(c.getId(), c.getPatient().getId(), c.getPatient().getName(), c.getPatient().getSurname(), c.getDate(), c.getStartTime()));
        }
        return pharmacyAdmin.getConsulting() == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(cons);
    }


    @GetMapping("/myPatients")
    @PreAuthorize("hasRole('PHARMACIST')")
    ResponseEntity<List<PatientForFrontDTO>> getOurPatients() {
        PatientForFrontDTO newP = new PatientForFrontDTO();
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser) currentUser.getPrincipal();
        Pharmacist pharmacist = pharmacistService.findById(user.getId());
        List<PatientForFrontDTO> persons = new ArrayList<>();

        for (Consulting c : consultingService.findAll()) {
            if (c.getPharmacist().getId() == pharmacist.getId()) {


                persons.add(new PatientForFrontDTO(c.getPatient().getId(), c.getPatient().getEmail(), c.getPatient().getName(), c.getPatient().getSurname(), c.getPatient().getPhoneNumber()));
            }
        }


        return persons== null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(persons);
    }



}