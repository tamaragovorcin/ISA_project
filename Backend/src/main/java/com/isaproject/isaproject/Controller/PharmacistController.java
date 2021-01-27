
package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.DTO.PharmacistDTO;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import com.isaproject.isaproject.Service.Implementations.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacist")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PharmacistController {

    @Autowired
    PharmacistService pharmacistService;

    @PostMapping("/register")
   //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<String> addUser(@RequestBody PersonUserDTO userRequest) {

        PersonUser existUser = pharmacistService.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getEmail(), "Email already exists");
        }
        PersonUser user = pharmacistService.save(userRequest);
        return new ResponseEntity<>("Pharmacist is successfully registred!", HttpStatus.CREATED);
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
    @GetMapping("")
    ResponseEntity<List<Pharmacist>> getAll()
    {
        List<Pharmacist> pharmacists = pharmacistService.findAll();
        return pharmacists == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacists);
    }
    @PostMapping("/update")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<String> update(@RequestBody Pharmacist userRequest) {

        Pharmacist user = pharmacistService.update(userRequest);
        return user.getSurname() == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Pharmacist is successfully updated!", HttpStatus.CREATED);
    }
}
