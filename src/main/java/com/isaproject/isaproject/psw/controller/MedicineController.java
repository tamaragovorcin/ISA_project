package com.isaproject.isaproject.psw.controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import org.hibernate.boot.MetadataBuilder;
import org.springframework.beans.factory.annotation.Autowired;


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
	    }
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

	public MultiValueMap<String, HttpEntity<?>> fromFile(File file) {
		MultipartBodyBuilder builder = new MultipartBodyBuilder();
		builder.part("file", new FileSystemResource(file));
		return builder.build();
	}
	@RequestMapping(value = "/upload/medicine/{medicine}", method = RequestMethod.GET)
	public ResponseEntity<?> reportHttpMedicine(@PathVariable String medicine) throws IOException, ServletException {
		System.out.println("a*******************************************************");

		final WebClient webClient = webClientBuilder.build();
		webClient.post()
				.uri("http://localhost:57942/api/report/http/recieve")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters.fromMultipartData(fromFile(new File("src/main/resources/psw.txt"))))
				.retrieve()
				.bodyToMono(String.class)
				.block();
		return ResponseEntity.ok().build();

	}

	private String getRandomString() {
		return new Random().nextInt(999999) + "_" + System.currentTimeMillis();
	}
	String UPLOAD_DIR = "src/main/resources/psw.txt";
	private File getTargetFile(String fileExtn, String fileName) {
		File targetFile = new File(UPLOAD_DIR + fileName + fileExtn);
		return targetFile;
	}

	private String getFileExtension(MultipartFile inFile) {
		String fileExtention = inFile.getOriginalFilename().substring(inFile.getOriginalFilename().lastIndexOf('.'));
		return fileExtention;
	}

	  
	  
}
