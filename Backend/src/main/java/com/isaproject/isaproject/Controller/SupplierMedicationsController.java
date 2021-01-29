package com.isaproject.isaproject.Controller;
import com.isaproject.isaproject.DTO.SupplierMedicationReviewDTO;
import com.isaproject.isaproject.DTO.SupplierMedicationUpdateQuantityDTO;
import com.isaproject.isaproject.DTO.SupplierMedicationsDTO;
import com.isaproject.isaproject.Model.Medicine.SupplierMedications;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.Supplier;
import com.isaproject.isaproject.Service.Implementations.SupplierMedicationService;
import com.isaproject.isaproject.Service.Implementations.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/supplierMedications")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SupplierMedicationsController {
    @Autowired
    SupplierMedicationService supplierMedicationService;

    @Autowired
    SupplierService supplierService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('SUPPLIER')")
    public ResponseEntity<String> add(@RequestBody SupplierMedicationsDTO supplierMedicationsDTO) {
        SupplierMedications supplierMedications = supplierMedicationService.save(supplierMedicationsDTO);
        return supplierMedications == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Medication is successfully added!", HttpStatus.CREATED);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('SUPPLIER')")
    ResponseEntity<List<SupplierMedicationReviewDTO>> getAllMedications()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        Supplier supplier = supplierService.findById(user.getId());
        Set<SupplierMedications> supplierMedications = supplier.getSupplierMedications();
        List<SupplierMedicationReviewDTO> supplierMedicationsDTOS = new ArrayList<>();

        for (SupplierMedications medication:supplierMedications ) {
            supplierMedicationsDTOS.add(new SupplierMedicationReviewDTO(medication.getId(), medication.getQuantity(),
                    medication.getReservedQuantity(),medication.getName(), medication.getCode(), medication.getForm(),
                    medication.getType()));
        }
        return supplierMedicationsDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(supplierMedicationsDTOS);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('SUPPLIER')")
    public ResponseEntity<String> update(@RequestBody SupplierMedicationUpdateQuantityDTO supplierMedicationDTO) {

        SupplierMedications supplierMedications = supplierMedicationService.update(supplierMedicationDTO);
        return supplierMedications == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Medication is successfully added!", HttpStatus.CREATED);
    }
}
