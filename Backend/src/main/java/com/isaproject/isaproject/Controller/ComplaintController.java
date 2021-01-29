package com.isaproject.isaproject.Controller;
import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Model.HelpModel.Complaint;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Address;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import com.isaproject.isaproject.Service.Implementations.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/complaint")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ComplaintController {
    @Autowired
    ComplaintService complaintService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<Complaint> add(@RequestBody ComplaintDTO complaintDTO)
    {
        Complaint complaint = complaintService.save(complaintDTO);
        return complaint == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(complaint);
    }

    @PostMapping("/answer")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    ResponseEntity<Complaint> answer(@RequestBody ComplaintReviewDTO complaintReviewDTO)
    {
        Complaint complaint = complaintService.sendAnswerToPatient(complaintReviewDTO);
        return complaint == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(complaint);
    }

    @GetMapping("all")
    ResponseEntity<List<ComplaintReviewDTO>> getAllWithDTO()
    {
        List<Complaint> complaints = complaintService.findAll();
        List<ComplaintReviewDTO> complaintReviewDTOS  = new ArrayList<>();
        for (Complaint complaint:complaints) {
            if(!complaint.isAnswered()) {
                if(complaint.getDermatologist()!=null) {
                    complaintReviewDTOS.add(new ComplaintReviewDTO(complaint.getId(),getPatientUserDTO(complaint.getPatient()), complaint.getSubject(),
                            getDermatologistUserDTO(complaint.getDermatologist()),
                            null, null, complaint.isAnswered(), complaint.getMassage(), complaint.getAnswer()));
                }
                else if(complaint.getPharmacist()!=null) {
                    complaintReviewDTOS.add(new ComplaintReviewDTO(complaint.getId(),getPatientUserDTO(complaint.getPatient()), complaint.getSubject(),null,
                            getPharmacistUserDTO(complaint.getPharmacist()), null, complaint.isAnswered(), complaint.getMassage(), complaint.getAnswer()));
                }
                else if(complaint.getPharmacy()!=null) {
                    complaintReviewDTOS.add(new ComplaintReviewDTO(complaint.getId(),getPatientUserDTO(complaint.getPatient()), complaint.getSubject(),null,
                           null, getPharmacyDto(complaint.getPharmacy()), complaint.isAnswered(), complaint.getMassage(), complaint.getAnswer()));
                }
                else {
                    complaintReviewDTOS.add(new ComplaintReviewDTO(complaint.getId(),getPatientUserDTO(complaint.getPatient()), complaint.getSubject(),null,
                            null, null, complaint.isAnswered(), complaint.getMassage(), complaint.getAnswer()));
                }
            }
        }

        return complaintReviewDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(complaintReviewDTOS);
    }


    private PharmacyDTO getPharmacyDto(Pharmacy pharmacy) {
        Address address = pharmacy.getAddress();
        AddressDTO addressDTO = new AddressDTO(address.getTown(), address.getStreet(), address.getNumber(), address.getPostalCode(), address.getCountry());
        return new PharmacyDTO(pharmacy.getPharmacyName(),addressDTO, pharmacy.getConsultingPrice());
    }

    private PersonUserDTO getPatientUserDTO(Patient patient) {

        Address patientAddress = patient.getAddress();
        AddressDTO addressDTO = new AddressDTO(patientAddress.getTown(), patientAddress.getStreet(), patientAddress.getNumber(), patientAddress.getPostalCode(), patientAddress.getCountry());
        return new PersonUserDTO(patient.getEmail(), patient.getPassword(), patient.getName(), patient.getSurname(),
                patient.getPhoneNumber(), addressDTO);
    }
    private PersonUserDTO getDermatologistUserDTO(Dermatologist dermatologist) {

        Address address = dermatologist.getAddress();
        AddressDTO addressDTO = new AddressDTO(address.getTown(), address.getStreet(), address.getNumber(), address.getPostalCode(), address.getCountry());
        return new PersonUserDTO(dermatologist.getEmail(), dermatologist.getPassword(), dermatologist.getName(), dermatologist.getSurname(),
                dermatologist.getPhoneNumber(), addressDTO);
    }

    private PersonUserDTO getPharmacistUserDTO(Pharmacist pharmacist) {

        Address address = pharmacist.getAddress();
        AddressDTO addressDTO = new AddressDTO(address.getTown(), address.getStreet(), address.getNumber(), address.getPostalCode(), address.getCountry());
        return new PersonUserDTO(pharmacist.getEmail(), pharmacist.getPassword(), pharmacist.getName(), pharmacist.getSurname(),
                pharmacist.getPhoneNumber(), addressDTO);
    }


}
