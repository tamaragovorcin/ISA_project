package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.DTO.PharmacyAdminDTO;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.PharmacyAdmin;
import com.isaproject.isaproject.Model.Users.Supplier;
import com.isaproject.isaproject.Service.Implementations.PharmacyAdminService;
import com.isaproject.isaproject.Service.Implementations.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pharmacyAdmin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PharmacyAdminController {
    @Autowired
    PharmacyAdminService pharmacyAdminService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
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
}
