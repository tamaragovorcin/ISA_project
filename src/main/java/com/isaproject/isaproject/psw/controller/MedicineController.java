package com.isaproject.isaproject.psw.controller;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


import com.isaproject.isaproject.psw.model.MedicineName;
import org.hibernate.boot.MetadataBuilder;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;

import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.isaproject.isaproject.psw.model.Medicine;

import com.isaproject.isaproject.psw.service.IFilesStorageService;
import com.isaproject.isaproject.psw.service.IMedicineService;

import javax.servlet.ServletException;

@RestController
public class MedicineController {
	@Autowired
	private IMedicineService service;
	private IFilesStorageService storageService;
	private WebClient.Builder webClientBuilder;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")

    @GetMapping("/medicine")
	List<Medicine> getAllMedicines()
    {
    	return service.getAll();
    }
	 @GetMapping("/medicine/description/{medicine}")
		String getMedicineDescription(@PathVariable String medicine)
	    {
		 	List<Medicine> medicines= service.getAll();
		 	for (Medicine medicine2 : medicines) {
				if(medicine2.getName().equals(medicine)) {
					return medicine2.getDescription();
				}
			}
		 	return "";
	    }/*
	 @RequestMapping(value = "/medicineRequested", method = RequestMethod.GET)
	    public List<String> getMedicines() {

     	System.out.println("POGODIO");
		 List<String> medName = new ArrayList<String>();
	        try {
	            List<Medicine> dtoList = service.getAll();
	           
	            for (Medicine medicine : dtoList) {
					medName.add(medicine.getName());
					System.out.println(medicine.getName());
				}
	        } catch (Exception e) {
	            e.printStackTrace();
	            
	        }
	        return medName;
	    }
*/
	@RequestMapping(value = "/medicineRequested", method = RequestMethod.GET)
	public List<MedicineName> getMedicines() {

		List<MedicineName> medName = new ArrayList<MedicineName>();
		try {
			List<Medicine> dtoList = service.getAll();
			for (Medicine medicine : dtoList) {
				medName.add(new MedicineName(medicine.getName()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medName;
	}


	private String getMedicineDescription2(String medicine) {
		List<Medicine> medicines= service.getAll();
		for (Medicine medicine2 : medicines) {
			if(medicine2.getName().equals(medicine)) {
				return medicine2.getDescription();
			}
		}
		return "";
	}
	@RequestMapping(value = "/upload/medicine/{medicine}", method = RequestMethod.GET)
	public ResponseEntity<?> reportHttpMedicine(@PathVariable String medicine) throws IOException, ServletException {
		String fileName = medicine + ".txt";
		FileWriter writer;
		File f = new File("src/main/resources/MedicineDescription/" + fileName);
		if (!f.exists()) {
			writer = new FileWriter("src/main/resources/MedicineDescription/" + fileName);
			writer.write(getMedicineDescription2(medicine) + "\n");
			writer.flush();
			writer.close();
		}

		ResponseEntity respEntity = null;

		byte[] reportBytes = null;

		File result = new File("src/main/resources/MedicineDescription/" + fileName);
		System.out.println(result);
		if (result.exists()) {
			InputStream inputStream = new FileInputStream("src/main/resources/MedicineDescription/" + fileName);
			String type = result.toURL().openConnection().guessContentTypeFromName(fileName);
			System.out.println("TIP  " + type);
			ByteArrayResource out = new ByteArrayResource(Files.readAllBytes(Path.of("src/main/resources/MedicineDescription/" + fileName)));
			System.out.println("OUT  " + out);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("content-disposition", "attachment; filename=" + fileName);
			responseHeaders.add("Content-Type", type);

			respEntity = new ResponseEntity(out, responseHeaders, HttpStatus.OK);
		} else {
			respEntity = new ResponseEntity("File Not Found", HttpStatus.OK);
		}
		return respEntity;
	}



	  
}
