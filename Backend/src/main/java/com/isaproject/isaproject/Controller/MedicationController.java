package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.MedicationDTO;
import com.isaproject.isaproject.DTO.MedicationSearchDTO;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.DTO.SpecificationDTO;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Medicine.Specification;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Service.Implementations.MedicationService;
import com.isaproject.isaproject.Service.Implementations.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/medication")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MedicationController {

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
        return medications == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medications);
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

    private MedicationSearchDTO getMedicationSearchDTO(Medication medication) {
        Specification specification = medication.getSpecification();
        SpecificationDTO specificationDTO = new SpecificationDTO(specification.getContraIndications(),
                specification.getStructure(), specification.getRecommendedConsumption(), specification.getManufacturer());
        return new MedicationSearchDTO(medication.getName(), medication.getForm(), medication.getType(),
                medication.getIssuanceRegime(), medication.getMark(), specificationDTO);
    }

}
