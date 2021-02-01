package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.MarkDTO;
import com.isaproject.isaproject.DTO.MedicationDTO;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Mark;
import com.isaproject.isaproject.Model.Users.MarkMedication;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Service.Implementations.MarkMedicationService;
import com.isaproject.isaproject.Service.Implementations.MarkService;
import com.isaproject.isaproject.Service.Implementations.MedicationService;
import com.isaproject.isaproject.Service.Implementations.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medication")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MedicationController {


    @Autowired
    MarkMedicationService markService;

    @Autowired
    MedicationService medicationService;

    @PostMapping("/add")
    ResponseEntity<Medication> register(@RequestBody MedicationDTO medicationDTO)
    {
        Medication medication = medicationService.save(medicationDTO);
        return medication == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medication);
    }

    @GetMapping("")
    ResponseEntity<List<Medication>> getAll()
    {
        List<Medication> medications = medicationService.findAll();
        System.out.println(medications.size());
        return medications == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medications);
    }


    @PostMapping("/leaveAMark")
    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<Medication> leaveAMark(@RequestBody MarkDTO dto) {

       List<MarkMedication> markList = markService.findAll();
        List<Medication> list = medicationService.findAll();
        Medication medication = new Medication();

        double mark = 0;
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        int five = 0;

        Boolean hasMedication = false;
        Boolean hasPatient = false;

        for (MarkMedication mark1 : markList) {
            if (mark1.getMedication().getId() == dto.getMedication().getId()) {
                    hasMedication = true;

                if (mark1.getPatient().getId() == dto.getPatient().getId()) {
                    hasPatient =true;
                }
            }
        }

        for (Medication medication1 : list) {

            if (dto.getMedication().getId() == medication1.getId()) {
                mark = medication1.getMark();
                medication = medication1;


            }
        }
        if(markList.size()==0){
            MarkMedication mark2 = new MarkMedication();
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
            mark2.setMedication(dto.getMedication());
            mark2.setPatientsMark(dto.getMark());

            MarkMedication mark3 = markService.save(mark2);

            double ocena = (one * 1 + two * 2 + three * 3 + four * 4 + five * 5)/(one+two+three+four+five);
            medication.setMark(ocena);
            medicationService.update(medication);

        }else {
            for (MarkMedication mark1 : markList) {
                if (hasMedication) {
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

                        MarkMedication mark2 = markService.save(mark1);
                        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaa");
                        double ocena = (one * 1 + two * 2 + three * 3 + four * 4 + five * 5) / (one + two + three + four + five);
                        medication.setMark(ocena);
                        medicationService.update(medication);
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
                        MarkMedication mark2 = markService.save(mark1);

                        double ocena = (one * 1 + two * 2 + three * 3 + four * 4 + five * 5) / (one + two + three + four + five);
                        medication.setMark(ocena);
                        medicationService.update(medication);
                    }

                } else {

                    MarkMedication mark2 = new MarkMedication();
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
                    mark2.setMedication(dto.getMedication());
                    mark2.setPatientsMark(dto.getMark());
                    MarkMedication mark3 = markService.save(mark2);

                    double ocena = (one * 1 + two * 2 + three * 3 + four * 4 + five * 5) / (one + two + three + four + five);
                    medication.setMark(ocena);
                    medicationService.update(medication);


                }
            }
        }



        return medication == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medication);

    }
}
