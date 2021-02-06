package com.isaproject.isaproject.Controller;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Service.IServices.IPersonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonUserController {

    @Autowired
    private IPersonUserService userService;

    @GetMapping("/account")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<PersonUser> getMyAccount()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        return user == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(user);
    }

    @PostMapping("/update")
    ResponseEntity<PersonUser> update(@RequestBody PersonUserDTO person)
    {
        PersonUser per = userService.findByEmail(person.getEmail());
        Integer id = per.getId();
        userService.delete(per);
        PersonUser patient = userService.updateProfile(person, id);
        return patient == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patient);
    }
}