package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.MarkDTO;
import com.isaproject.isaproject.DTO.DermatologistDTO;
import com.isaproject.isaproject.DTO.UserBasicInfoDTO;
import com.isaproject.isaproject.DTO.WorkingHoursDermatologistDTO;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.MarkDermatologist;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Service.Implementations.DermatologistService;
import com.isaproject.isaproject.Service.Implementations.MarkDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/dermatologist")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DermatologistController {
    @Autowired
    DermatologistService dermatologistService;

    @Autowired
    MarkDermatologistService markService;

    @PostMapping("/register")
   // @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<String> addUser(@RequestBody DermatologistDTO userRequest) {

        PersonUser existUser = dermatologistService.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getEmail(), "Email already exists");
        }
        PersonUser user = dermatologistService.save(userRequest);
        return new ResponseEntity<>("Dermatologist is successfully registred!", HttpStatus.CREATED);
    }

    @GetMapping("/account")
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    ResponseEntity<Dermatologist> getMyAccount()
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
    public ResponseEntity<String> update(@RequestBody Dermatologist userRequest) {

        Dermatologist user = dermatologistService.update(userRequest);
        return user.getSurname() == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Dermatologist is successfully updated!", HttpStatus.CREATED);
    }

    @GetMapping("")
    ResponseEntity<List<Dermatologist>> getAll()
    {
        List<Dermatologist> dermatologists = dermatologistService.findAll();
        return dermatologists == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(dermatologists);
    }
    @PostMapping("/addPharmacy")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<String> addPharmacy(@RequestBody WorkingHoursDermatologistDTO dto) {

        if(dermatologistService.addPharmacy(dto)){
            return new ResponseEntity<>("Pharmacy is successfully registred!", HttpStatus.CREATED);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/basicInfo")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<List<UserBasicInfoDTO>> getDermatologistsBasicInfo()
    {   List<UserBasicInfoDTO> basicInfos = new ArrayList<>();
        List<Dermatologist> dermatologists = dermatologistService.findAll();
        for (Dermatologist dermatologist: dermatologists) {
            basicInfos.add(new UserBasicInfoDTO(dermatologist.getName() + " " + dermatologist.getSurname(), dermatologist.getEmail()));
        }
        return basicInfos == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(basicInfos);
    }


    @PostMapping("/leaveAMark")
    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<Dermatologist> leaveAMark(@RequestBody MarkDTO dto) {

        List<MarkDermatologist> markList = markService.findAll();
        List<Dermatologist> list = dermatologistService.findAll();
        Dermatologist dermatologist = new Dermatologist();

        double mark = 0;
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        int five = 0;

        Boolean hasDermatologist = false;
        Boolean hasPatient = false;

        for (MarkDermatologist mark1 : markList) {
            if (mark1.getDermatologist().getId() == dto.getDermatologist().getId()) {
                hasDermatologist = true;

                if (mark1.getPatient().getId() == dto.getPatient().getId()) {
                    hasPatient =true;
                }
            }
        }

        for (Dermatologist dermatologist1 : list) {

            if (dto.getDermatologist().getId() == dermatologist1.getId()) {
                mark = dermatologist1.getMarkDermatologist();
                dermatologist = dermatologist1;


            }
        }
        if(markList.size()==0){
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
            mark2.setPatient(dto.getPatient());
            mark2.setDermatologist(dto.getDermatologist());
            mark2.setPatientsMark(dto.getMark());

            MarkDermatologist mark3 = markService.save(mark2);

            double ocena = (one * 1 + two * 2 + three * 3 + four * 4 + five * 5)/(one+two+three+four+five);
            dermatologist.setMarkDermatologist(ocena);
            dermatologistService.update(dermatologist);

        }else {
            for (MarkDermatologist mark1 : markList) {
                if (hasDermatologist) {
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

                        MarkDermatologist mark2 = markService.save(mark1);

                        double ocena = (one * 1 + two * 2 + three * 3 + four * 4 + five * 5) / (one + two + three + four + five);
                        dermatologist.setMarkDermatologist(ocena);
                        dermatologistService.update(dermatologist);
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
                        MarkDermatologist mark2 = markService.save(mark1);

                        double ocena = (one * 1 + two * 2 + three * 3 + four * 4 + five * 5) / (one + two + three + four + five);
                        dermatologist.setMarkDermatologist(ocena);
                        dermatologistService.update(dermatologist);
                    }

                } else {

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
                    mark2.setPatient(dto.getPatient());
                    mark2.setDermatologist(dto.getDermatologist());
                    mark2.setPatientsMark(dto.getMark());
                    MarkDermatologist mark3 = markService.save(mark2);

                    double ocena = (one * 1 + two * 2 + three * 3 + four * 4 + five * 5) / (one + two + three + four + five);
                    dermatologist.setMarkDermatologist(ocena);
                    dermatologistService.update(dermatologist);


                }
            }
        }



        return dermatologist == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(dermatologist);

    }

}
