package com.isaproject.isaproject.Controller;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.DTO.UserBasicInfoDTO;
import com.isaproject.isaproject.DTO.WorkingHoursDermatologistDTO;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.Pharmacist;
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

        if(dermatologistService.addPharmacy(dto)){
            return new ResponseEntity<>("Pharmacy is successfully registred!", HttpStatus.CREATED);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/basicInfo")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<List<UserBasicInfoDTO>> getDermatologistsBasicInfo()
    {   List<UserBasicInfoDTO> basicInfos = new ArrayList<>();
        List<Dermatologist> dermatologists = dermatologistService.findAll();
        for (Dermatologist dermatologist: dermatologists) {
            basicInfos.add(new UserBasicInfoDTO(dermatologist.getName() + " " + dermatologist.getSurname(), dermatologist.getEmail()));
        }
        return basicInfos == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(basicInfos);
    }

}
