package com.isaproject.isaproject.psw.controller;

import com.isaproject.isaproject.psw.model.MedicineQuantity;
import com.isaproject.isaproject.psw.model.Tender;
import com.isaproject.isaproject.psw.service.HttpClientService;
import com.isaproject.isaproject.service.ScheduledTasksGrpc;
import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tender")
public class TenderController {

    private final HttpClientService client;
    private ScheduledTasksGrpc scheduledTasks;
    @Autowired
    public TenderController(HttpClientService client) {
        this.client = client;
    }

    @PostMapping("/sendOffer")
    ResponseEntity<String> sendTenderOffer(@RequestBody Tender tender)
    {




        String tenderString ="TENDER&";
        tenderString+=createTenderString(tender);
        System.out.println(tenderString);

        System.out.println("\n\n\nTENDER OFFER INFORMATION\n");
        System.out.println("Tender id: " + tender.getId());
        System.out.println("Pharmacy: " + tender.getPharmacyApi());
        System.out.println("Price: " + tender.getPriceOffer());
        System.out.println("Medicine name   -   quantity   - availableQuantity");
        for (MedicineQuantity medicine: tender.getMedicineQuantityList()) {
            System.out.println(medicine.getName()+"    "  +medicine.getQuantity() + "    "   +medicine.getAvailableQunatity());

        }


        HttpURLConnection connection = null;
        try {

            URL url = new URL("url endpoint");

            if (url.getProtocol().equalsIgnoreCase("http://localhost:57942/api/tender/offer")) {
                connection = (HttpsURLConnection) url.openConnection();
            } else {
                connection = (HttpURLConnection) url.openConnection();
            }
            //  Set connection properties
            connection.setRequestMethod("POST"); // get or post
            connection.setReadTimeout(3 * 1000);
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            if (tenderString != null) {
                OutputStream os = connection.getOutputStream();

                os.write(tenderString.getBytes(StandardCharsets.UTF_8));

                os.flush();
                os.close();
            }

            int responseCode = connection.getResponseCode();
            System.out.println(responseCode + "                      code");

        }
        catch(Exception e) {
           // System.out.println("OVDEEEEEEEEEEEEEEEEEEEEE");
        }
        //scheduledTasks = new ScheduledTasksGrpc();
        //scheduledTasks.sendMessageToServer();
    /*
        ResponseEntity<?> result;
        try {
            boolean action = client.sendTenderOffer(tenderString);
            result = action ? new ResponseEntity<>(HttpStatus.OK) : ResponseEntity.badRequest().body("Bad request!");
        } catch (Exception e) {
            e.printStackTrace();
            result = ResponseEntity.badRequest().body("Bad request!");
        }
    */

        return tender == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok("ok");

    }


    private String createTenderString(Tender tender) {
        String tenderString ="";
        tenderString+="tenderId_"+tender.getId()+"&";
        tenderString+="pharmacyApi_"+tender.getPharmacyApi() +"&";
        tenderString+="priceOffer_" + tender.getPriceOffer()+"&";
        List<MedicineQuantity> medicineQuantityList = tender.getMedicineQuantityList();
        for (MedicineQuantity medicine: medicineQuantityList ) {
            tenderString+=medicine.getName() + "_" +medicine.getAvailableQunatity()+";";
        }
        tenderString = tenderString.substring(0, tenderString.length() - 1);

        return tenderString;
    }

    @GetMapping("/active")
    ResponseEntity<List<Tender>> getActiveTenders() throws IOException {
        List<Tender> tenders = getTendersFromFiles();
        return tenders == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(tenders);
    }
    @GetMapping("/{tenderId}")
    ResponseEntity<Tender> getTenderById(@PathVariable String tenderId) throws IOException {

        Tender tender = getTenderByTenderId(tenderId);
        return tender == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(tender);
    }

    private Tender getTenderByTenderId(String tenderId) throws IOException {
        List<Tender> tenders = getTendersFromFiles();
        for (Tender tender: tenders) {
            if(tender.getId()==(Integer.parseInt(tenderId))){
                return tender;
            }
        }
        return new Tender();
    }

    @RequestMapping(value = "/http", method = RequestMethod.POST)
    public ResponseEntity<String> tenderHttp(@RequestBody String tenderRequest) throws IOException, ServletException {
        System.out.println("POGODIO METODU          " + tenderRequest);
        String fileName = getFileNameForTender();
        FileWriter writer;

        writer = new FileWriter("src/main/resources/Tenders/" + fileName);
        writer.write(tenderRequest + "\n");
        writer.flush();
        writer.close();

        return null;
    }

    private String getFileNameForTender() {
        int tenderId = 1;
        String fileName = tenderId + "_tender" + ".txt";
        FileWriter writer;
        File f = new File("src/main/resources/Tenders/" + fileName);
        if (!f.exists()) {
            return fileName;
        }
        while (f.exists()) {
            tenderId += 1;
            fileName = tenderId + "_tender" + ".txt";
            f = new File("src/main/resources/Tenders/" + fileName);
        }
        return fileName;
    }


    private List<Tender> getTendersFromFiles() throws IOException {
        final File remoteDir = new File("src/main/resources/Tenders/");
        FileWriter writer;
        String[] contents = remoteDir.list();
        List<Tender> tenders = new ArrayList<Tender>();
        for (String fileName : contents) {
            File localFile = new File("src/main/resources/Tenders/" + fileName);
            Tender tender = getTender(fileName, localFile);
            tenders.add(tender);
        }

        return tenders;
    }

    private Tender getTender(String fileName, File localFile) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(localFile));
        String text = fileReader.readLine();
        String textParts[] = text.split("&");
        String medicinesWithQuantity = textParts[0];
        String date = textParts[1];
        String medicinesWithQuantityParts[] = medicinesWithQuantity.split(";");
        List<MedicineQuantity> medicineQuantityList = new ArrayList<MedicineQuantity>();
        for (String medicineQuantity : medicinesWithQuantityParts) {
            String oneMedicineQuantityParts[] = medicineQuantity.split("_");
            MedicineQuantity medicine = new MedicineQuantity(oneMedicineQuantityParts[0], Integer.parseInt(oneMedicineQuantityParts[1]));
            medicineQuantityList.add(medicine);
        }
        Tender tender = new Tender(getTenderId(fileName),date,medicineQuantityList);
        return tender;
    }

    private int getTenderId(String fileName) {
        String fileNameParts []=fileName.split("_");
        return Integer.parseInt(fileNameParts[0]);
    }
}