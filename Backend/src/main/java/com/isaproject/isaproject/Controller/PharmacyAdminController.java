package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.DTO.PharmacyAdminDTO;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.HelpModel.MedicationPrice;
import com.isaproject.isaproject.Model.Pharmacy.Actions;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.*;
import com.isaproject.isaproject.Service.Implementations.MedicationPriceService;
import com.isaproject.isaproject.Service.Implementations.PharmacyAdminService;
import com.isaproject.isaproject.Service.Implementations.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/pharmacyAdmin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PharmacyAdminController {
    @Autowired
    PharmacyAdminService pharmacyAdminService;
    @Autowired
    MedicationPriceService medicationPriceService;

    @PostMapping("/register")
   // @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<String> addUser(@RequestBody PharmacyAdminDTO userRequest) {
        System.out.println(userRequest.getPharmacy().getPharmacyName());

        PersonUser existUser = pharmacyAdminService.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getEmail(), "Email already exists");
        }
        PersonUser user = pharmacyAdminService.save(userRequest);
        return new ResponseEntity<>("Supplier is successfully registred!", HttpStatus.CREATED);
    }

    @GetMapping("/account")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<PharmacyAdmin> getMyAccount()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        return pharmacyAdmin == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyAdmin);
    }
    @PostMapping("/update")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<PharmacyAdmin> update(@RequestBody PharmacyAdminDTO person)
    {
        PharmacyAdmin per = pharmacyAdminService.findByEmail(person.getEmail());
        Integer id = per.getId();
        pharmacyAdminService.delete(per);
        PharmacyAdmin patient = pharmacyAdminService.save(person);
        return patient == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patient);
    }
    @GetMapping("/myPharmacy")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<Pharmacy> getMyPharmacy()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        return pharmacyAdmin.getPharmacy() == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyAdmin.getPharmacy());
    }
    @GetMapping("/dermatologists")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<Set<Dermatologist>> getOurDermatologists()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        return pharmacyAdmin.getPharmacy().getDermatologists() == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyAdmin.getPharmacy().getDermatologists());
    }
    @GetMapping("/actions")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<Set<Actions>> getActions()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        return pharmacyAdmin.getPharmacy().getActions() == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyAdmin.getPharmacy().getActions());
    }
    @GetMapping("/pharmacists")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<Set<Pharmacist>> getPharmacists()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        return pharmacyAdmin.getPharmacy().getPharmacists() == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyAdmin.getPharmacy().getPharmacists());
    }
    @GetMapping("/medication")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<List<MedicationPrice>> getMedication()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();

        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());

        return medicationPriceService.findByPharmacy(pharmacyAdmin.getPharmacy().getId()) == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medicationPriceService.findByPharmacy(pharmacyAdmin.getPharmacy().getId()));
    }
}