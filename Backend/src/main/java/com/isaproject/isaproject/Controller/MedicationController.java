package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.MarkDTO;
import com.isaproject.isaproject.DTO.MedicationDTO;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Users.*;
import com.isaproject.isaproject.Service.Implementations.*;
import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Model.HelpModel.MedicationPrice;
import com.isaproject.isaproject.Model.Medicine.Specification;
import com.isaproject.isaproject.Validation.CommonValidatior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/medication")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MedicationController {


    @Autowired
    MarkMedicationService markService;

    @Autowired
    MedicationService medicationService;

    @Autowired
    MedicationPriceService medicationPriceService;

    @Autowired
    MedicationReservationService medicationReservationService;


    @PostMapping("/add")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    ResponseEntity<Medication> register(@RequestBody MedicationDTO medicationDTO)
    {
        CommonValidatior commonVlidatior = new CommonValidatior();
        if(!commonVlidatior.checkValidationMedication(medicationDTO)) {
            throw new IllegalArgumentException("Please fill in all the fields correctly!");
        }
        Medication existingMedication = medicationService.findByName(medicationDTO.getName());
        if(existingMedication != null)
        {
            throw new ResourceConflictException("Medication name already exists", "Medication name already exists");
        }
        Medication existingMedication2 = medicationService.findByCode(medicationDTO.getCode());
        if(existingMedication2 != null)
        {
            throw new ResourceConflictException("Medication code already exists", "Medication code already exists");
        }
        Medication medication = medicationService.save(medicationDTO);
        return medication == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medication);
    }
    @PostMapping("/priceInPharmacy")
    ResponseEntity<MedicationPrice> addToPharmacy(@RequestBody MedicationPriceDTO medicationPriceDTO)
    {
        MedicationPrice medicationPrice = medicationPriceService.updatePrice(medicationPriceDTO);
        return medicationPrice == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medicationPrice);
    }

    @GetMapping("")
    //@PreAuthorize("anyRole('SYSTEM_ADMIN')")
    ResponseEntity<List<Medication>> getAll()
    {
        List<Medication> medications = medicationService.findAll();
        return medications == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medications);
    }


    public Boolean ableToRateMedications(Integer medicationId, Integer patientId){

        Boolean able = false;
        List<MedicationReservation> medicationReservations = medicationReservationService.findAll();
        for(MedicationReservation medicationReservation: medicationReservations){
            if(medicationReservation.getMedicine().getId() == medicationId && medicationReservation.getPatient().getId() == patientId){
                able=true;
            }
        }
        return able;
    }

    @PostMapping("/leaveAMark")
    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<String> leaveAMark(@RequestBody MarkDTO dto) {

        Boolean able = false;

        able = ableToRateMedications(dto.getMedication().getId(), dto.getPatient().getId());
        if(able) {
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
                        hasPatient = true;
                    }
                }
            }

            for (Medication medication1 : list) {

                if (dto.getMedication().getId() == medication1.getId()) {
                    mark = medication1.getMark();
                    medication = medication1;


                }
            }
            if (markList.size() == 0) {
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

            } else {
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


        }
        return able == true ?
                new ResponseEntity<>("You have successfully rated a medication!", HttpStatus.CREATED) :
                new ResponseEntity<>("You are not able to leave a mark for the medication because you have not had it!", HttpStatus.CREATED);

    }
    @GetMapping("searchName/{medicationName}")
    ResponseEntity<MedicationSearchDTO> getAll(@PathVariable String medicationName)
    {
        Medication medication= medicationService.findByName(medicationName);
        MedicationSearchDTO medicationSearchDTO = getMedicationSearchDTO(medication);
        return medicationSearchDTO == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medicationSearchDTO);
    }

    @GetMapping("searchForm/{medicationForm}")
    ResponseEntity<List<MedicationSearchDTO>> getAllByForm(@PathVariable String medicationForm)
    {
        List<Medication> medications= medicationService.findByForm(medicationForm);
        List<MedicationSearchDTO> medicationsForFront = new ArrayList<>();
        for (Medication medication: medications) {
            MedicationSearchDTO medicationSearchDTO = getMedicationSearchDTO(medication);
            medicationsForFront.add(medicationSearchDTO);
        }
        return medicationsForFront == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medicationsForFront);
    }
    @GetMapping("searchType/{medicationType}")
    ResponseEntity<List<MedicationSearchDTO>> getAllByType(@PathVariable String medicationType)
    {
        List<Medication> medications= medicationService.findByType(medicationType);
        List<MedicationSearchDTO> medicationsForFront = new ArrayList<>();
        for (Medication medication: medications) {
            MedicationSearchDTO medicationSearchDTO = getMedicationSearchDTO(medication);
            medicationsForFront.add(medicationSearchDTO);
        }
        return medicationsForFront == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medicationsForFront);
    }

    @GetMapping("searchMark/{MarkMin}/{MarkMax}")
    ResponseEntity<List<MedicationSearchDTO>> getAllByType(@PathVariable int MarkMin,@PathVariable int MarkMax )
    {
        List<Medication> medications= medicationService.findByMark(MarkMin,MarkMax);
        List<MedicationSearchDTO> medicationsForFront = new ArrayList<>();
        for (Medication medication: medications) {
            MedicationSearchDTO medicationSearchDTO = getMedicationSearchDTO(medication);
            medicationsForFront.add(medicationSearchDTO);
        }
        return medicationsForFront == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medicationsForFront);
    }
    @GetMapping("getAll")
    ResponseEntity<List<MedicationSearchDTO>> getAllMedications()
    {
        List<Medication> medications= medicationService.findAll();
        List<MedicationSearchDTO> medicationsForFront = new ArrayList<>();
        for (Medication medication: medications) {
            MedicationSearchDTO medicationSearchDTO = getMedicationSearchDTO(medication);
            medicationsForFront.add(medicationSearchDTO);
        }
        return medicationsForFront == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medicationsForFront);
    }
   

    private MedicationSearchDTO getMedicationSearchDTO(Medication medication) {
        Specification specification = medication.getSpecification();
        SpecificationDTO specificationDTO = new SpecificationDTO(specification.getContraIndications(),
                specification.getStructure(), specification.getRecommendedConsumption(), specification.getManufacturer());

        return new MedicationSearchDTO(medication.getId(), medication.getName(), medication.getForm(), medication.getType(),
                medication.getIssuanceRegime(), medication.getMark(), medication.getCode(),specificationDTO);
    }

}
