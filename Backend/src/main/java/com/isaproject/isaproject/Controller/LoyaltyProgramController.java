package com.isaproject.isaproject.Controller;
import com.isaproject.isaproject.Model.HelpModel.LoyaltyProgram;
import com.isaproject.isaproject.Service.Implementations.LoyaltyProgramService;
import com.isaproject.isaproject.Validation.CommonValidatior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/loyaltyProgram")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoyaltyProgramController {
    @Autowired
    LoyaltyProgramService loyaltyProgramService;

    @PostMapping("/define")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<String> addLoyaltyProgram(@RequestBody LoyaltyProgram loyaltyProgram) {

        CommonValidatior commonVlidatior = new CommonValidatior();
        if(!commonVlidatior.checkValidationLoyaltyProgram2(loyaltyProgram)) {
            throw new IllegalArgumentException("Please fill in all the fields correctly!");
        }
        try {
            LoyaltyProgram program = loyaltyProgramService.save(loyaltyProgram);
            return program == null ?
                    new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                    new ResponseEntity<>("Supplier is successfully registred!", HttpStatus.CREATED);
        }
        catch (ObjectOptimisticLockingFailureException objectOptimisticLockingFailureException){
            throw new ObjectOptimisticLockingFailureException("Someone else has just changed loyalt program. Please try again.",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<LoyaltyProgram> getAll() {

        List<LoyaltyProgram> programs = loyaltyProgramService.findAll();
        if(programs.size()==0) {
            return ResponseEntity.ok(new LoyaltyProgram());
        }

        return programs == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(programs.get(0));
    }
}
