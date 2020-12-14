package com.isaproject.isaproject.psw.controller;

import com.isaproject.isaproject.psw.model.MedicineInPharmacy;
import com.isaproject.isaproject.psw.model.Pharmacy;
import com.isaproject.isaproject.psw.model.PharmacyName;
import com.isaproject.isaproject.psw.service.IMedicinesPharmacyService;
import com.isaproject.isaproject.psw.service.IPharmacyService;
import org.bouncycastle.pqc.asn1.ParSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/medicinePharmacy")
public class MedicinesPharmacyController {
    @Autowired
    private IMedicinesPharmacyService service;
    private IPharmacyService pharmacyService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")

    @GetMapping("/all")
    @ResponseBody
    public List<MedicineInPharmacy> getAllPharmacies()
    {
        return service.getAll();
    }

    public List<PharmacyName> getAllPharmaciesForMedicine(String medicine, int quantity)
    {
       /* seem.out.println(medicine);rvice.add(new MedicineInPharmacy(1,"api1","Pancef",2));
        service.add(new MedicineInPharmacy(2,"api2","Pancef",20));
        service.add(new MedicineInPharmacy(3,"api3","Pancef",5));
        service.add(new MedicineInPharmacy(4,"api4","Pancef",100));
        service.add(new MedicineInPharmacy(5,"api1","Paracetamol",2));
        service.add(new MedicineInPharmacy(6,"api2","Paracetamol",25));
        service.add(new MedicineInPharmacy(7,"api3","Defrinol",100));
        service.add(new MedicineInPharmacy(8,"api4","Defrinol",20));
        service.add(new MedicineInPharmacy(9,"api1","Brufen",5));
        */

        System.out.println(service.getAll().size());
        for (MedicineInPharmacy medicine1 : service.getAll()) {  System.out.println("************");}

        List<PharmacyName> pharmacies = new ArrayList<>();
        for (MedicineInPharmacy medicine1 : service.getAll()) {
            if(medicine1.getMedicineName().equals(medicine)) {
                if(medicine1.getQuantity()>= quantity) {
                    pharmacies.add(getPharmacyWithApi(medicine1.getPharmacyApi()));
                }
            }
        }
        return pharmacies;
    }

    private PharmacyName getPharmacyWithApi(String pharmacyApi) {
        if(pharmacyApi.equals("api1")) { return new PharmacyName("Jankovic 1", "Beograd","api1");}
        else if(pharmacyApi.equals("api2")) { return new PharmacyName("Jankovic 2", "Novi Sad","api2");}
        else if(pharmacyApi.equals("api3")) { return new PharmacyName("Jankovic 3", "Nis","api3");}
        else if(pharmacyApi.equals("api4")) { return new PharmacyName("Jankovic 4", "Kragujevac","api4");}
        else { return null;}


    }

    @RequestMapping(value = "/{medicine}", method = RequestMethod.GET)
    public ResponseEntity<?> reportHttpMedicineAvailability(@PathVariable String medicine) throws IOException, ServletException {
        String []med_pars = medicine.split("_");
        String medicine1  = med_pars[0];
        String quantityS = med_pars[1];
        int quantity  = Integer.parseInt(quantityS);
        System.out.println("************************"+medicine1 + "    "+quantity+ "***************************");

        String fileName = medicine1 + "_" + quantity + ".txt";
        FileWriter writer;
        File f = new File("src/main/resources/MedicineAvailability/" + fileName);
        if (!f.exists()) {
            writer = new FileWriter("src/main/resources/MedicineAvailability/" + fileName);
            writer.write(getMedicineAvailabilityFile(medicine1,quantity) + "\n");
            writer.flush();
            writer.close();
        }


        ResponseEntity respEntity = null;

        byte[] reportBytes = null;

        File result = new File("src/main/resources/MedicineAvailability/" + fileName);
        System.out.println(result);
        if (result.exists()) {
            InputStream inputStream = new FileInputStream("src/main/resources/MedicineAvailability/" + fileName);
            String type = result.toURL().openConnection().guessContentTypeFromName(fileName);
            System.out.println("TIP  " + type);
            ByteArrayResource out = new ByteArrayResource(Files.readAllBytes(Path.of("src/main/resources/MedicineAvailability/" + fileName)));
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

    private String getMedicineAvailabilityFile(String medicine, int quantity) {
        List<PharmacyName> medicines = getAllPharmaciesForMedicine(medicine,quantity);
        String stringBuilder = "";
        for (PharmacyName pharmacyName : medicines) {
            stringBuilder+=pharmacyName.getName() + "_"+pharmacyName.getCity() + "_"+pharmacyName.getApi()+";";
        }
        if(stringBuilder.length()>5) {
            stringBuilder = stringBuilder.substring(0, stringBuilder.length() - 1);
        }


        return stringBuilder;
    }

/* INSERT INTO medicinesPharmacy(id,pharmacyApi, medicineName, quantity) VALUES(1,'api1','Pancef',5);
INSERT INTO medicinesPharmacy(id,pharmacyApi, medicineName, quantity) VALUES(2,'api2','Pancef',1);
INSERT INTO medicinesPharmacy(id,pharmacyApi, medicineName, quantity) VALUES(3,'api3','Pancef',20);
INSERT INTO medicinesPharmacy(id,pharmacyApi, medicineName, quantity) VALUES(4,'api4','Pancef',100);
INSERT INTO medicinesPharmacy(id,pharmacyApi, medicineName, quantity) VALUES(5,'api1','Defrinol',5);
INSERT INTO medicinesPharmacy(id,pharmacyApi, medicineName, quantity) VALUES(6,'api2','Defrinol',9);
INSERT INTO medicinesPharmacy(id,pharmacyApi, medicineName, quantity) VALUES(7,'api3','Brufen',20);
INSERT INTO medicinesPharmacy(id,pharmacyApi, medicineName, quantity) VALUES(8,'api4','Brufen',100);
INSERT INTO medicinesPharmacy(id,pharmacyApi, medicineName, quantity) VALUES(9,'api4','Paracetamol',3);*/








}
