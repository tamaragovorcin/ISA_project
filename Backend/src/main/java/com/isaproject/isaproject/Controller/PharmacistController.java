
package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Examinations.Examination;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Users.*;
import com.isaproject.isaproject.Repository.MarkPharmacistRepository;
import com.isaproject.isaproject.Service.Implementations.ConsultingService;
import com.isaproject.isaproject.Service.Implementations.MarkPharmacistService;
import com.isaproject.isaproject.Service.Implementations.PharmacistService;
import com.isaproject.isaproject.Service.Implementations.PharmacyAdminService;
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

    @PostMapping("/register")
    //@PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<String> addUser(@RequestBody PharmacistDTO userRequest) {
        System.out.println(userRequest.getPharmacy().getPharmacyName());

        PersonUser existUser = pharmacistService.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getEmail(), "Email already exists");
        }
        Pharmacist user = pharmacistService.save(userRequest);
        return new ResponseEntity<>("Supplier is successfully registred!", HttpStatus.CREATED);
    }

    @GetMapping("")
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
    public ResponseEntity<String> update(@RequestBody Pharmacist userRequest) {

        Pharmacist user = pharmacistService.update(userRequest);
        return user.getSurname() == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Pharmacist is successfully updated!", HttpStatus.CREATED);
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


    @PostMapping("/delete")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<String> addUser(@RequestBody Pharmacist pharmacist) {
        System.out.println(pharmacist.getName());
        pharmacistService.delete(pharmacist);
        return new ResponseEntity<>("Pharmacist is successfully removed!", HttpStatus.CREATED);
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
    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
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
                if (mark1.getPharmacist().getId() == dto.getPharmacist().getId()) {
                    hasPharmacist = true;

                    if (mark1.getPatient().getId() == dto.getPatient().getId()) {
                        hasPatient = true;
                    }
                }
            }

            for (Pharmacist pharmacist1 : list) {

                if (dto.getPharmacist().getId() == pharmacist1.getId()) {
                    mark = pharmacist1.getMarkPharmacist();
                    pharmacist = pharmacist1;


                }
            }
            if (markList.size() == 0) {
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

                double ocena = (one * 1 + two * 2 + three * 3 + four * 4 + five * 5) / (one + two + three + four + five);
                pharmacist.setMarkPharmacist(ocena);
                pharmacistService.updateMark(pharmacist);

            } else {
                for (MarkPharmacist mark1 : markList) {
                    if (hasPharmacist) {
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

                            MarkPharmacist mark2 = markService.save(mark1);
                            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaa");
                            double ocena = (one * 1 + two * 2 + three * 3 + four * 4 + five * 5) / (one + two + three + four + five);
                            pharmacist.setMarkPharmacist(ocena);
                            pharmacistService.updateMark(pharmacist);
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
                            MarkPharmacist mark2 = markService.save(mark1);

                            double ocena = (one * 1 + two * 2 + three * 3 + four * 4 + five * 5) / (one + two + three + four + five);
                            pharmacist.setMarkPharmacist(ocena);
                            pharmacistService.updateMark(pharmacist);
                        }

                    } else {

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

                        double ocena = (one * 1 + two * 2 + three * 3 + four * 4 + five * 5) / (one + two + three + four + five);
                        pharmacist.setMarkPharmacist(ocena);
                        pharmacistService.updateMark(pharmacist);


                    }
                }
            }


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
}








