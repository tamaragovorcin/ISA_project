package com.isaproject.isaproject.Controller;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Model.Examinations.EPrescription;
import com.isaproject.isaproject.Model.HelpModel.LoyaltyProgram;
import com.isaproject.isaproject.Model.HelpModel.MedicationPrice;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Repository.LoyaltyProgramRepository;
import com.isaproject.isaproject.Service.Implementations.EPrescriptionService;
import com.isaproject.isaproject.Service.Implementations.MedicationPriceService;
import com.isaproject.isaproject.Service.Implementations.PatientService;
import com.isaproject.isaproject.Service.Implementations.PharmacyService;
import com.isaproject.isaproject.Validation.CommonValidatior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

@RestController
@RequestMapping("/api/erecipes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EPrescriptionController {

    @Autowired
    PharmacyService pharmacyService;

    @Autowired
    MedicationPriceService medicationPriceService;

    @Autowired
    PatientService patientService;

    @Autowired
    EPrescriptionService ePrescriptionService;
    @Autowired
    LoyaltyProgramRepository loyaltyProgramRepository;

    @PostMapping("/file")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<EPrescriptionFullInfoDTO> hello(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
                File destination = new File("src/main/resources/qr/" + file.getOriginalFilename());
                ImageIO.write(src, "png", destination);
                String decodedText = decodeQRCode(new File("src/main/resources/qr/" + file.getOriginalFilename()));
                if (decodedText == null) {
                    throw new IllegalArgumentException("Please upload correct QR code!");
                } else {
                    String code = getEReceiptCode(decodedText);
                    EPrescription ePrescription = ePrescriptionService.findByCode(code);
                    if(ePrescription!=null) {
                        throw new IllegalArgumentException("This eReceipt is already used!");
                    }
                    List<QRcodeInformationDTO> medicationsInQRcode = getMedicationsInQRcode(decodedText);
                    if(medicationsInQRcode==null) {
                        throw new IllegalArgumentException("Please try later!");
                    }
                    List<PharmacyMedicationAvailabilityDTO> pharmacyAvailability = getAvailabilityInPharmacies(medicationsInQRcode);
                    EPrescriptionFullInfoDTO ePrescriptionFullInfoDTO = new EPrescriptionFullInfoDTO(pharmacyAvailability,medicationsInQRcode,code);
                    return pharmacyAvailability == null ?
                            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                            ResponseEntity.ok(ePrescriptionFullInfoDTO);

                }
            } catch (IOException | NotFoundException e) {
                throw new IllegalArgumentException("Please upload correct QR code!");}
        }
        throw new IllegalArgumentException("Please upload correct QR code!");
    }
    @PostMapping("/file/noAuthentication")
    ResponseEntity<List<QRcodeInformationDTO>> uploadFile(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
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
    private String getEReceiptCode(String decodedText) {
        String []code = decodedText.split("!");
        return code[0];
    }


    @PostMapping("/availability/pharmacy")
    ResponseEntity<String> getAvailabilityInPharmacy(@RequestBody MedicineAvailabilityQRDTO listMedications) {
        Pharmacy pharmacy = pharmacyService.findById(listMedications.getPharmacy());
        for(PharmacyMedicationAvailabilityDTO dto : getAvailabilityInPharmacies(listMedications.getListMedications())) {
            if(dto.getPharmacyName().equals(pharmacy.getPharmacyName()))
                return new ResponseEntity<>("Requested medication is available in our pharmacy.", HttpStatus.CREATED);

        }
        return new ResponseEntity<>("Requested medication is not available in our pharmacy.", HttpStatus.CREATED);

    }

    @PostMapping("/choosePharmacy")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<String> choosePharmacyForEReceipt(@RequestBody ChoosenPharmacyDTO choosenPharmacy) {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        Patient patient = patientService.findById(user.getId());
        if(patient.getPenalties()>3) {
            throw new IllegalArgumentException("You are not able to get medicaions! You have too many penalties");
        }
        CommonValidatior commonVlidatior = new CommonValidatior();
        if(!commonVlidatior.checkEprescription(choosenPharmacy)) {
            throw new IllegalArgumentException("Please fill in all the fields correctly!");
        }
        return ePrescriptionService.proccedEReceipt(choosenPharmacy) ==null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok("Successfully updated!");
    }

    @GetMapping("/all")
    ResponseEntity<List<EPrescription>> getall() {
        List<EPrescription> ePrescriptions = ePrescriptionService.findAll();
        return ePrescriptions == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(ePrescriptions);
    }

    @GetMapping("/myEprescriptions")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<Set<EPrescription>> getMyEPrescriptions() {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        Patient patient = patientService.findById(user.getId());

        Set<EPrescription> ePrescriptions = patient.getePrescriptions();
        return ePrescriptions == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(ePrescriptions);
    }

    private List<PharmacyMedicationAvailabilityDTO> getAvailabilityInPharmacies(List<QRcodeInformationDTO> medicationsInQRcode) {
        List<PharmacyMedicationAvailabilityDTO> pharmacyList = new ArrayList<>();
        List<Pharmacy> pharmacies = pharmacyService.findAll();
        for(Pharmacy pharmacy : pharmacies) {
            double hasMedicationsPrice = pharmacyHasAllMedications(pharmacy.getMedicationPrices(),medicationsInQRcode);
            double priceWithLoyaltyProgram = setPriceWithLoyaltyProgram(hasMedicationsPrice);
            if(hasMedicationsPrice>0) {
                pharmacyList.add(new PharmacyMedicationAvailabilityDTO(pharmacy.getId(), priceWithLoyaltyProgram, pharmacy.getMark(),
                        new AddressDTO(pharmacy.getAddress().getTown(), pharmacy.getAddress().getStreet(), pharmacy.getAddress().getNumber(),
                                pharmacy.getAddress().getPostalCode(), pharmacy.getAddress().getCountry()), pharmacy.getPharmacyName()));
            }
        }
        return pharmacyList;
    }

    private double setPriceWithLoyaltyProgram(double hasMedicationsPrice) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();

        Patient patient = patientService.findById(user.getId());
        String status = patient.getLoyaltyCategory();
        double newPrice = 0;

        try {
            LoyaltyProgram loyaltyProgram = loyaltyProgramRepository.findAll().get(0);
            if(status.equals("REGULAR")) {
                newPrice = hasMedicationsPrice - hasMedicationsPrice * (loyaltyProgram.getRegularDiscount()/100);
            }
            else if(status.equals("SILVER")) {
                newPrice = hasMedicationsPrice - hasMedicationsPrice * (loyaltyProgram.getSilverDiscount()/100);
            }
            else if(status.equals("GOLD")) {
                newPrice = hasMedicationsPrice -hasMedicationsPrice * (loyaltyProgram.getGoldenDiscount()/100);
            }
        }
        catch(Exception e) {
            return hasMedicationsPrice;
        }

        return newPrice;
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
            String []code = decodedText.split("!");
            String []medications = code[1].split(";");
            for (String medication: medications) {
                String []medicationParts = medication.split("_");
                qrList.add(new QRcodeInformationDTO(medicationParts[0],Long.parseLong(medicationParts[1]),Integer.parseInt(medicationParts[2])));
            }
        }
        else {
            String []code = decodedText.split("!");
            String []medicationParts = code[1].split("_");
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


