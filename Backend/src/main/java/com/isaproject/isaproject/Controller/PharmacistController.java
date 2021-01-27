package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.PharmacistDTO;
import com.isaproject.isaproject.DTO.PharmacyAdminDTO;
import com.isaproject.isaproject.DTO.UserBasicInfoDTO;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import com.isaproject.isaproject.Model.Users.PharmacyAdmin;
import com.isaproject.isaproject.Service.Implementations.PharmacistService;
import com.isaproject.isaproject.Service.Implementations.PharmacyAdminService;
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
@RequestMapping("/api/pharmacist")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PharmacistController {
    @Autowired
    PharmacistService pharmacistService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<String> addUser(@RequestBody PharmacistDTO userRequest) {
        System.out.println(userRequest.getPharmacy().getPharmacyName());

        PersonUser existUser = pharmacistService.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getEmail(), "Email already exists");
        }
        Pharmacist user = pharmacistService.save(userRequest);
        return new ResponseEntity<>("Supplier is successfully registred!", HttpStatus.CREATED);
    }
    @PostMapping("/delete")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<String> removeUser(@RequestBody Pharmacist pharmacist) {
        System.out.println(pharmacist.getName());
        pharmacistService.delete(pharmacist);
        return new ResponseEntity<>("Pharmacist is successfully removed!", HttpStatus.CREATED);
    }

    @GetMapping("/account")
    @PreAuthorize("hasRole('PHARMACIST')")
    ResponseEntity<Pharmacist> getMyAccount()
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
    ResponseEntity<List<UserBasicInfoDTO>> getPharmacistsBasicInfo()
    {   List<UserBasicInfoDTO> basicInfos = new ArrayList<>();
        List<Pharmacist> pharmacists = pharmacistService.findAll();
        for (Pharmacist pharmacist: pharmacists) {
            basicInfos.add(new UserBasicInfoDTO(pharmacist.getName() + " " + pharmacist.getSurname(), pharmacist.getEmail()));
        }
        return basicInfos == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(basicInfos);
    }
}
