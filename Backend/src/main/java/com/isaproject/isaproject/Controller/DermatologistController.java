package com.isaproject.isaproject.Controller;
import com.isaproject.isaproject.DTO.DermatologistDTO;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.DTO.WorkingHoursDermatologistDTO;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Service.Implementations.DermatologistService;
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

    @PostMapping("/register")
   // @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<String> addUser(@RequestBody PersonUserDTO userRequest) {

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
    ResponseEntity<Dermatologist> update(@RequestBody PersonUserDTO person)
    {
        Dermatologist per = dermatologistService.findByEmail(person.getEmail());
        Integer id = per.getId();
        //dermatologistService.delete(per);
        Dermatologist patient = dermatologistService.save(person);
        return patient == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patient);
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
        System.out.println("pogodiooo" +dto.getPharmacy().getPharmacyName());
        System.out.println("pogodiooooooooooooooooooooooooooooooooooooo" +dto.getDermatologist().getName());
        if(dermatologistService.addPharmacy(dto)){
            return new ResponseEntity<>("Pharmacy is successfully registred!", HttpStatus.CREATED);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
