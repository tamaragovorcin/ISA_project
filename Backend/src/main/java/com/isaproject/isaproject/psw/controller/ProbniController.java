package com.isaproject.isaproject.psw.controller;

import com.isaproject.isaproject.psw.model.Pharmacy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProbniController {
    @CrossOrigin(origins = "*", allowedHeaders = "*")

    @GetMapping("/probni")
    public String getByAPIKey()
    {
        System.out.println("PROBNI");
        return "Probni";

    }

}
