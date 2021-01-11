package com.isaproject.isaproject.psw.controller;

import java.util.ArrayList;
import java.util.List;

import com.isaproject.isaproject.psw.model.UrgentOrder;
import com.isaproject.isaproject.psw.service.IUrgentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isaproject.isaproject.psw.model.Hospital;
import com.isaproject.isaproject.psw.model.Pharmacy;
import com.isaproject.isaproject.psw.service.IPharmacyService;
import com.isaproject.isaproject.psw.service.PharmacyService;

@RestController
public class PharmacyController {
	@Autowired
	private IPharmacyService service;
	@Autowired
	private IUrgentOrderService serviceUrgentOrders;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")

    @GetMapping("/pharmacy/{api}")
	ResponseEntity<Pharmacy> getByAPIKey(@PathVariable String api)
    {
		System.out.println("Pogodio meotdu za apoteku");
        Pharmacy pharmacy = service.get(api);
        return pharmacy == null ? 
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            ResponseEntity.ok(pharmacy);
    }

    @GetMapping("/pharmacy")
	List<Pharmacy> getAllPharmacies()
    {
    	return service.getAll();
    }
    
	 @PostMapping("/pharmacy2")
	 ResponseEntity<String> register(@RequestBody Pharmacy pharmacy)
	 {
	        return service.add(pharmacy) ?
	            ResponseEntity.ok(pharmacy.getApiKey()) :
	            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	 }
	 

	 
	 
	 
	 
	 
	
}
