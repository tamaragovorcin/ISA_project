package com.isaproject.isaproject.psw.controller;

import com.isaproject.isaproject.psw.model.Pharmacy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProbniController {
    @CrossOrigin(origins = "*", allowedHeaders = "*")

    @GetMapping("/probni")
    public String getByAPIKey()
    {
        System.out.println("PROBNI");
        return "Probni";

    }

}
