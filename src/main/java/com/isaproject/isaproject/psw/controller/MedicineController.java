package com.isaproject.isaproject.psw.controller;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.isaproject.isaproject.psw.model.Medicine;

import com.isaproject.isaproject.psw.service.IFilesStorageService;
import com.isaproject.isaproject.psw.service.IMedicineService;






@RestController
public class MedicineController {
	@Autowired
	private IMedicineService service;
	private IFilesStorageService storageService;

	
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
	    public ResponseEntity<?> getMedicines() {
	        ResponseEntity<?> result;
	        try {
	            List<Medicine> dtoList = service.getAll();
	            result = dtoList == null ?
	                    ResponseEntity.badRequest().body("Bad request!") : new ResponseEntity<>(dtoList, HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            result = ResponseEntity.badRequest().body("Bad request!");
	        }
	        return result;
	    }
	 /*
	 @GetMapping("/medicine/description2/{medicine}")
		ResponseEntity getMedicineDescription2(@PathVariable String medicine)
	    {  ResponseEntity result;
	    	String foundDescription ="";
		 	List<Medicine> medicines= service.getAll();
		 	for (Medicine medicine2 : medicines) {
				if(medicine2.getName().equals(medicine)) {
					foundDescription= medicine2.getDescription();
				}
			}
		 	 try {
		          result = foundDescription == null ?
		                  ResponseEntity.badRequest().body("Bad request!") : new ResponseEntity(foundDescription, HttpStatus.OK);
		      } catch (Exception e) {
		          e.printStackTrace();
		          result = ResponseEntity.badRequest().body("Bad request!");
		      }
		 	 System.out.println(result.toString());
		      return result;
	    }

	 
	 
	  
	  
	  

	  
	  @RequestMapping(value = "/medicine/description2/{medicine}", method = RequestMethod.GET)
	    public ResponseEntity getMedicineDescription2(@PathVariable String medicine) {
		  ResponseEntity result;
	    	String foundDescription ="";
		 	List<Medicine> medicines= service.getAll();
		 	for (Medicine medicine2 : medicines) {
				if(medicine2.getName().equals(medicine)) {
					foundDescription= medicine2.getDescription();
				}
			}
		 	 try {
		          result = foundDescription == null ?
		                  ResponseEntity.badRequest().body("Bad request!") : new ResponseEntity(foundDescription, HttpStatus.OK);
		      } catch (Exception e) {
		          e.printStackTrace();
		          result = ResponseEntity.badRequest().body("Bad request!");
		      }
		 	 System.out.println(result.toString());
		      return result;
	    }
	  
	 
	 // @ResponseMapping(value = "/medicine/files/{medicine}", method = RequestMethod.GET)
	  
	  @GetMapping("/medicine/files/{medicine}")
	  @ResponseBody
	  public ResponseEntity<Resource> getFile(@PathVariable String medicine) throws MalformedURLException {
		  HttpHeaders headers = new HttpHeaders();
		    headers.add("Content-Type", "multipart/form-data");
		 
		// Path fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()) .toAbsolutePath().normalize();;
		//Resource file = (Resource) storageService.load("Panadol.txt");
		  //Resource file = @"src/main/resources/"+ "Panadol.txt";
		 // File file = resources.get("src/main/resources/Panadol.txt");
		 // Path filePath = fileStorageLocation.resolve("src/main/resources/Panadol.txt").normalize();
		 // System.out.println(filePath + "--------------------------------------------------------------------------");
		// Resource resource = new UrlResource(("D:\\FAKULTET\\4. GODINA\\ISA\\isaproject\\src\\main\\resources"));
		  Resource resource = resourceLoader.getResource("src/main/resources/Panadol.txt");
		 if(resource.exists()) {
			 System.out.println("POSTOJI *************************************************************************");
		 }
		
	     return ResponseEntity.ok()
	       .contentType(MediaType.MULTIPART_FORM_DATA).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "Panadol.txt" + "\"").body(resource);
	  }
	 
	  @PostMapping("/")
		public String handleFileUpload(@RequestParam("file") MultipartFile file,
				RedirectAttributes redirectAttributes) {

			storageService.store(file);
			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded " + file.getOriginalFilename() + "!");

			return "redirect:/";
		}
	   
	  */
	  
	  
}
