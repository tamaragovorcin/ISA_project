package com.isaproject.isaproject.Controller;
import com.isaproject.isaproject.DTO.LoyaltyProgramDTO;
import com.isaproject.isaproject.Model.HelpModel.LoyaltyProgram;
import com.isaproject.isaproject.Service.Implementations.LoyaltyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loyaltyProgram")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoyaltyProgramController {
    @Autowired
    LoyaltyProgramService loyaltyProgramService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<String> addLoyaltyProgram(@RequestBody LoyaltyProgramDTO loyaltyProgramDTO) {

        LoyaltyProgram program = loyaltyProgramService.save(loyaltyProgramDTO);
        return program == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Supplier is successfully registred!", HttpStatus.CREATED);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<String> update(@RequestBody LoyaltyProgram loyaltyProgram) {

        LoyaltyProgram program = loyaltyProgramService.update(loyaltyProgram);
        return program == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Supplier is successfully updated!", HttpStatus.CREATED);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<LoyaltyProgram> getAll() {

        List<LoyaltyProgram> programs = loyaltyProgramService.findAll();
        return programs == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(programs.get(0));
    }
}
