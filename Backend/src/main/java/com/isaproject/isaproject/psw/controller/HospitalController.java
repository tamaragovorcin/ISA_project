package com.isaproject.isaproject.psw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isaproject.isaproject.psw.model.Hospital;
import com.isaproject.isaproject.psw.service.IHospitalService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HospitalController {

	@Autowired
	private IHospitalService service;

	@GetMapping("/pswapi/{api}")
	ResponseEntity<String> getByAPIKey(@PathVariable String api)
	{
		Hospital hospital = service.get(api);
		return hospital == null ?
				new ResponseEntity<>(HttpStatus.NOT_FOUND) :
				ResponseEntity.ok(hospital.getName());
	}
	@GetMapping("/pswapi")
	List<Hospital> getAllPharmacies()
	{
		List<Hospital> hospitals = service.getAll();
		if(hospitals!= null) return (List<Hospital>) ResponseEntity.ok(hospitals);
		else {
			return (List<Hospital>) new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}



	}
	@PostMapping("/pswapi")
	ResponseEntity<String> register(@RequestBody Hospital hospital)
	{
		return service.add(hospital) ?
				ResponseEntity.ok(hospital.getApiKey()) :
				new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}


}
