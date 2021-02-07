package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.DTO.MarkDTO;
import com.isaproject.isaproject.DTO.DermatologistDTO;
import com.isaproject.isaproject.DTO.UserBasicInfoDTO;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.Examinations.Examination;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.MarkDermatologist;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.PharmacyAdmin;
import com.isaproject.isaproject.Service.Implementations.*;
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

    @Autowired
    PatientService patientService;

    @Autowired
    ExaminationService examinationService;

    @Autowired
    ExaminationScheduleService examinationScheduleService;

    @Autowired
    PharmacyAdminService pharmacyAdminService;


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
    public ResponseEntity<String> update(@RequestBody Dermatologist userRequest) {

        Dermatologist user = dermatologistService.update(userRequest);
        return user.getSurname() == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Dermatologist is successfully updated!", HttpStatus.CREATED);
    }
    @GetMapping("/front")
    @PreAuthorize("hasAnyRole('PATIENT', 'SUPPLIER', 'SYSTEM_ADMIN', 'DERMATOLOGIST', 'PHARMACIST')")
    ResponseEntity<List<DermatologistsFrontDTO>> getAllFront()
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
    @GetMapping("")
    public ResponseEntity<List<Dermatologist>> getAll()
    {
        List<Dermatologist> dermatologists = dermatologistService.findAll();
        return dermatologists == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(dermatologists);
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
                    able = true;
                }
            }
        }

        return able;
    }

    @PostMapping("/leaveAMark")
    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<String> leaveAMark(@RequestBody MarkDTO dto) {


        Boolean able = false;

        able = ableToRateDermatologist(dto.getDermatologist().getId(), dto.getPatient().getId());

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

            Boolean hasDermatologist = false;
            Boolean hasPatient = false;

            for (MarkDermatologist mark1 : markList) {
                if (mark1.getDermatologist().getId() == dto.getDermatologist().getId()) {
                    hasDermatologist = true;

                    if (mark1.getPatient().getId() == dto.getPatient().getId()) {
                        hasPatient = true;
                    }
                }
            }

            for (Dermatologist dermatologist1 : list) {

                if (dto.getDermatologist().getId() == dermatologist1.getId()) {
                    mark = dermatologist1.getMarkDermatologist();
                    dermatologist = dermatologist1;


                }
            }
            if (markList.size() == 0) {
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
                dermatologistService.updateMark(dermatologist);

            } else {
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
                            dermatologistService.updateMark(dermatologist);
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
                            MarkDermatologist mark2 = markService.save(mark1);

                            double ocena = (one * 1 + two * 2 + three * 3 + four * 4 + five * 5) / (one + two + three + four + five);
                            dermatologist.setMarkDermatologist(ocena);
                            dermatologistService.updateMark(dermatologist);
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
                        dermatologistService.updateMark(dermatologist);


                    }
                }
            }




    }
        return able == true ?
                new ResponseEntity<>("You have successfully rated a dermatologist!", HttpStatus.CREATED) :
                new ResponseEntity<>("You are not able to leave a mark for the dermatologist that you had not had an appointment with!", HttpStatus.CREATED);

    }

}
