package com.isaproject.isaproject.Service.Implementations;
import com.isaproject.isaproject.DTO.ChoosenPharmacyDTO;
import com.isaproject.isaproject.DTO.QRcodeInformationDTO;
import com.isaproject.isaproject.Model.Examinations.EPrescription;
import com.isaproject.isaproject.Model.Medicine.MedicationEPrescription;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Repository.EPrescriptionRepository;
import com.isaproject.isaproject.Repository.MedicationEPrescriptionRepository;
import com.isaproject.isaproject.Repository.PatientRepository;
import com.isaproject.isaproject.Service.IServices.IEPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class EPrescriptionService implements IEPrescriptionService {
    @Autowired
    EPrescriptionRepository ePrescriptionRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    MedicationEPrescriptionRepository medicationEPrescriptionRepository;

    @Autowired
    MedicationPriceService medicationPriceService;

    @Autowired
    PatientService patientService;


    @Override
    public EPrescription findById(Integer id) {
        return ePrescriptionRepository.findById(id).get();
    }

    @Override
    public List<EPrescription> findAll() {
        return ePrescriptionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public EPrescription save(ChoosenPharmacyDTO choosenPharmacy) {
        try {
            Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
            PersonUser user = (PersonUser) currentUser.getPrincipal();
            Patient patient = patientRepository.getOne(user.getId());
            EPrescription ePrescription = new EPrescription();
            ePrescription.setPatient(patient);
            ePrescription.setDate(LocalDate.now());
            ePrescription.setCode(choosenPharmacy.getCode());
            ePrescription.setPharmacyId(choosenPharmacy.getPharmacyId());
            EPrescription ePrescription1= ePrescriptionRepository.save(ePrescription);
            List<QRcodeInformationDTO> qRcodeInformationDTOS = choosenPharmacy.getMedications();
            for (QRcodeInformationDTO medication : qRcodeInformationDTOS) {
                MedicationEPrescription medicationEPrescription = new MedicationEPrescription();
                medicationEPrescription.setCode(medication.getMedicationCode());
                medicationEPrescription.setName(medication.getMedicationName());
                medicationEPrescription.setQuantity(medication.getQuantity());
                medicationEPrescription.setePrescription(ePrescription1);
                medicationEPrescriptionRepository.save(medicationEPrescription);
            }
            return ePrescription1;
        }
        catch (Exception e) {
            return null;
        }
    }

    public Boolean checkEReceiptInPharmacy(Integer pharmacyId) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        Patient patient = patientRepository.findById(user.getId()).get();

        Set<EPrescription> ePrescriptions = patient.getePrescriptions();
        for (EPrescription ePrescription : ePrescriptions) {
            if(ePrescription.getPharmacyId()==pharmacyId) {
                return true;}
        }
        return false;
    }

    @Transactional(readOnly = false)
    public Boolean proccedEReceipt(ChoosenPharmacyDTO choosenPharmacy) {
          return medicationPriceService.updateMedicineQuantityEreceipt(choosenPharmacy) == false ||
                patientService.informPatientAboutEreceipt(choosenPharmacy.getMedications())==false ||
                save(choosenPharmacy)==null ?
                 true : false;
    }

    public EPrescription findByCode(String code) {
        return ePrescriptionRepository.findByCode(code);
    }
}
