package com.isaproject.isaproject.psw.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.isaproject.isaproject.psw.model.Medicine;
import com.isaproject.isaproject.psw.model.MedicineName;
import com.isaproject.isaproject.psw.service.IFilesStorageService;
import com.isaproject.isaproject.psw.service.IMedicineService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MedicineController {
	@Autowired
	private IMedicineService service;
	private IFilesStorageService storageService;
	private WebClient.Builder webClientBuilder;


	@GetMapping("/medicine")
	List<Medicine> getAllMedicines()
	{
		return service.getAll();
	}

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
	@RequestMapping(value = "/description/medicine/{medicine}", method = RequestMethod.GET)
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
