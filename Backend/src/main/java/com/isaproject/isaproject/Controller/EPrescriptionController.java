package com.isaproject.isaproject.Controller;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Model.HelpModel.MedicationPrice;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Service.Implementations.MedicationPriceService;
import com.isaproject.isaproject.Service.Implementations.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

@RestController
@RequestMapping("/api/erecipes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EPrescriptionController {

    @Autowired
    PharmacyService pharmacyService;

    @Autowired
    MedicationPriceService medicationPriceService;

    @PostMapping("/file")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<List<QRcodeInformationDTO>> hello(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                System.out.println(file.getOriginalFilename());
                BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
                File destination = new File("src/main/resources/qr/" + file.getOriginalFilename());
                ImageIO.write(src, "png", destination);
                String decodedText = decodeQRCode(new File("src/main/resources/qr/" + file.getOriginalFilename()));
                if (decodedText == null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                } else {
                    List<QRcodeInformationDTO> medicationsInQRcode = getMedicationsInQRcode(decodedText);

                    return medicationsInQRcode == null ?
                            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                            ResponseEntity.ok(medicationsInQRcode);
                }
            } catch (IOException | NotFoundException e) {
                new ResponseEntity<>(HttpStatus.NOT_FOUND);            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/availability")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<List<PharmacyMedicationAvailabilityDTO>> getAvailability(@RequestBody List<QRcodeInformationDTO> listMedications) {

        List<PharmacyMedicationAvailabilityDTO> pharmacyAvailability = getAvailabilityInPharmacies(listMedications);

        return pharmacyAvailability == null ?
                            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                            ResponseEntity.ok(pharmacyAvailability);
    }

    @PostMapping("/choosePharmacy")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<String> choosePharmacyForEReceipt(@RequestBody ChoosenPharmacyDTO choosenPharmacy) {
        return medicationPriceService.updateMedicineQuantityEreceipt(choosenPharmacy) == false ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok("Successfully updated!");
    }



    private List<PharmacyMedicationAvailabilityDTO> getAvailabilityInPharmacies(List<QRcodeInformationDTO> medicationsInQRcode) {
        List<PharmacyMedicationAvailabilityDTO> pharmacyList = new ArrayList<>();
        List<Pharmacy> pharmacies = pharmacyService.findAll();
        for(Pharmacy pharmacy : pharmacies) {
            double hasMedicationsPrice = pharmacyHasAllMedications(pharmacy.getMedicationPrices(),medicationsInQRcode);
            if(hasMedicationsPrice>0) {
                pharmacyList.add(new PharmacyMedicationAvailabilityDTO(pharmacy.getId(), hasMedicationsPrice, pharmacy.getMark(),
                        new AddressDTO(pharmacy.getAddress().getTown(), pharmacy.getAddress().getStreet(), pharmacy.getAddress().getNumber(),
                                pharmacy.getAddress().getPostalCode(), pharmacy.getAddress().getCountry()), pharmacy.getPharmacyName()));
            }
        }
        return pharmacyList;
    }

    private double pharmacyHasAllMedications(Set<MedicationPrice> medicationPrices, List<QRcodeInformationDTO> medicationsInQRcode) {
        double sumPrice = 0;
        for (QRcodeInformationDTO medication : medicationsInQRcode) {
            double price = existsInPharmacy(medicationPrices,medication);
            if(price<0) {return -1;}
            else {
                sumPrice+=price;
            }
        }
        return sumPrice;
    }

    private double existsInPharmacy(Set<MedicationPrice> medicationPrices, QRcodeInformationDTO medication) {
        for (MedicationPrice medicationPrice : medicationPrices) {
            if(medicationPrice.getMedication().getCode()==medication.getMedicationCode() &&
                    medicationPrice.getMedication().getName().equals(medication.getMedicationName())  &&
                    medicationPrice.getQuantity()>medication.getQuantity()) {
                return medicationPrice.getPrice()*medication.getQuantity();
            }
        }
        return -1;
    }

    private List<QRcodeInformationDTO> getMedicationsInQRcode(String decodedText) {
        List<QRcodeInformationDTO> qrList = new ArrayList<>();
        if(decodedText.contains(";")) {
            String []medications = decodedText.split(";");
            for (String medication: medications) {
                String []medicationParts = medication.split("_");
                qrList.add(new QRcodeInformationDTO(medicationParts[0],Long.parseLong(medicationParts[1]),Integer.parseInt(medicationParts[2])));
            }
        }
        else {
            String []medicationParts = decodedText.split("_");
            qrList.add(new QRcodeInformationDTO(medicationParts[0],Long.parseLong(medicationParts[1]),Integer.parseInt(medicationParts[2])));
        }
        return qrList;
    }

    private static String decodeQRCode(File qrCodeimage) throws IOException, NotFoundException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException n) {
            System.out.println("There is no QR code in the image");
            return null;
        }
    }
}


