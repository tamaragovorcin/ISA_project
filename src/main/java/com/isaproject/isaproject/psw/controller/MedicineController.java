package com.isaproject.isaproject.psw.controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


import com.isaproject.isaproject.psw.model.MedicineName;
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
	private WebClient createWebClientWithServerURLAndDefaultValues() {
		
		return WebClient.builder()
				.baseUrl("http://localhost:8081")
				.defaultCookie("cookieKey", "cookieValue")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
				.build();
	}

	@RequestMapping(value = "/upload/medicine/{medicine}", method = RequestMethod.GET)
	public ResponseEntity<?> reportHttpMedicine(@PathVariable String medicine) throws IOException, ServletException {
		System.out.println("a*******************************************************");
		System.out.println("a*********************        "+ medicine+ "       ******************");
		WebClient webClient = WebClient.builder().build();
		webClient.post()
				.uri("http://localhost:57942/api/report/http/recieve")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters.fromMultipartData(fromFile(new File("psw2.txt"))))
				.retrieve()
				.bodyToMono(String.class)
				.block();
		return ResponseEntity.ok().build();

	}

	/*
	@RequestMapping(value = "/upload/medicine/{medicine}", method = RequestMethod.GET)
	public ResponseEntity<?> reportHttpMedicine(@PathVariable String medicine) throws IOException, ServletException {
		WebClient webClient = webClientBuilder.build();
		webClient.post()
				.uri("http://localhost:8082/api/sharingPrescription/http/recieve")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters.fromMultipartData(fromFile(new File("psw2.txt"))))
				.retrieve()
				.bodyToMono(String.class)
				.block();
		return ResponseEntity.ok().build();

		/*
		* Resource resource = new ClassPathResource("other.xml.pdf");
		webTestClient.post()
                .uri("/resource")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_XML) // ignored
                .body(BodyInserters.fromResource(resource)) // sets Content-Type to application/pdf
                .exchange()
                .expectBody(ResponseBody.class)
                .returnResult();


		WebClient.RequestBodySpec uri1 = createWebClientWithServerURLAndDefaultValues().method(HttpMethod.POST)
				.uri("/resource");
		BodyInserter<String, ReactiveHttpOutputMessage> inserter3 = BodyInserters.fromObject("body");
		WebClient.ResponseSpec response1 = uri1
				.body(inserter3)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
				.acceptCharset(Charset.forName("UTF-8"))
				.ifNoneMatch("*")
				.ifModifiedSince(ZonedDateTime.now())
				.retrieve();
		return ResponseEntity.ok().build(); */
	//}

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
