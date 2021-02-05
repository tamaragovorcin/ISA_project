package com.isaproject.isaproject.Controller;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.SystemAdmin;
import com.isaproject.isaproject.Service.Implementations.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/systemAdmin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SystemAdminController {
    @Autowired
    SystemAdminService systemAdminService;

    @PostMapping("/register")
    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<String> addUser(@RequestBody PersonUserDTO userRequest) {

        PersonUser existUser = systemAdminService.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getEmail(), "Email already exists");
        }
        PersonUser user = systemAdminService.save(userRequest);
        return new ResponseEntity<>("System admin is successfully registred!", HttpStatus.CREATED);
    }

    @GetMapping("/account")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    ResponseEntity<SystemAdmin> getMyAccount()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        SystemAdmin systemAdmin = systemAdminService.findById(user.getId());
        return systemAdmin == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(systemAdmin);
    }
}