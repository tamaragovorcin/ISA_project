package com.isaproject.isaproject.Controller;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.Supplier;
import com.isaproject.isaproject.Service.Implementations.SupplierService;
import com.isaproject.isaproject.Validation.CommonValidatior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supplier")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<String> addUser(@RequestBody PersonUserDTO userRequest) {
        CommonValidatior commonVlidatior = new CommonValidatior();
        if(!commonVlidatior.checkValidationPersonUser(userRequest)) {
            throw new IllegalArgumentException("Please fill in all the fields correctly!");
        }
        PersonUser existUser = supplierService.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            throw new ResourceConflictException("Entered email already exists", "Email already exists");
        }
        if(userRequest.getPassword().isEmpty() || userRequest.getRewritePassword().isEmpty()) {
            throw new IllegalArgumentException("Please fill all the required fields!");
        }
        if(!userRequest.getPassword().equals(userRequest.getRewritePassword())) {
            throw new IllegalArgumentException("Please make sure your password and rewrite password match!");
        }
        PersonUser user = supplierService.save(userRequest);
        return new ResponseEntity<>("Supplier is successfully registred!", HttpStatus.CREATED);
    }

    @GetMapping("/account")
    @PreAuthorize("hasRole('SUPPLIER')")
    ResponseEntity<Supplier> getMyAccount()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        Supplier supplier = supplierService.findById(user.getId());
        return supplier == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(supplier);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('SUPPLIER')")
    public ResponseEntity<String> update(@RequestBody Supplier userRequest) {

        CommonValidatior commonVlidatior = new CommonValidatior();
        if(!commonVlidatior.checkValidationUpdateSupplier(userRequest)) {
            throw new IllegalArgumentException("Please fill in all the fields correctly!");
        }
        Supplier user = supplierService.update(userRequest);
        return user == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Supplier is successfully updated!", HttpStatus.CREATED);
    }
}
