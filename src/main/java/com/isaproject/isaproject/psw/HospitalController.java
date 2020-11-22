package com.isaproject.isaproject.psw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HospitalController {
	
	@Autowired
	private IHospitalService service;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/pswapi/{api}")
	ResponseEntity<String> getByAPIKey(@PathVariable String api)
    {
        Hospital hospital = service.get(api);
        return hospital == null ? 
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            ResponseEntity.ok(hospital.getName());
    }

}
