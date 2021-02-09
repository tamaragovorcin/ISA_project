package com.isaproject.isaproject.Service.Implementations;
import com.isaproject.isaproject.DTO.AddressDTO;
import com.isaproject.isaproject.DTO.PharmacyDTO;
import com.isaproject.isaproject.DTO.WorkingHoursDermatologistDTO;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Address;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Repository.DermatologistRepository;
import com.isaproject.isaproject.Repository.PharmacyRepository;
import com.isaproject.isaproject.Service.IServices.IPharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PharmacyService implements IPharmacyService {
    @Autowired
    PharmacyRepository pharmacyRepository;
    @Autowired
    DermatologistRepository dermatologistRepository;
    @Autowired
    EPrescriptionService ePrescriptionService;
    @Autowired
    ConsultingService consultingService;
    @Autowired
    ExaminationService examinationService;

    @Override
    public Pharmacy findById(Integer id) {
        return pharmacyRepository.findById(id).get();
    }

    @Override
    public List<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }

    public Pharmacy findByPharmacyName(String pharmacyName) {
        return pharmacyRepository.findByPharmacyName(pharmacyName);
    }
    @Override
    public Pharmacy save(PharmacyDTO pharmacy) {
        Pharmacy pharmacy1 = new Pharmacy();
        pharmacy1.setPharmacyName(pharmacy.getPharmacyName());
        pharmacy1.setDescription(pharmacy.getDescription());
        AddressDTO addressDTO = pharmacy.getAddress();
        Address address = new Address(addressDTO.getTown(),addressDTO.getStreet(),addressDTO.getNumber(),addressDTO.getPostalCode(),addressDTO.getCountry());
        pharmacy1.setAddress(address);
        pharmacy1.setConsultingPrice(pharmacy.getConsultingPrice());

        return pharmacyRepository.save(pharmacy1);
    }

    @Override
    public Boolean savePharmacy(WorkingHoursDermatologistDTO dto) {
        Pharmacy ph = findById((dto.getPharmacyId()));
        try{
            Dermatologist dermatologist = dermatologistRepository.getOne(dto.getDermatologistId());
            ph.getDermatologists().add(dermatologist);
            this.pharmacyRepository.save(ph);
            return true;
        }catch (Exception e){
            return false;
        }

    }
    @Override
    public Pharmacy update(Pharmacy pharmacy) {
        return this.pharmacyRepository.save(pharmacy);
    }

    public List<Pharmacy> getAllPharmacyNames() {
        return this.pharmacyRepository.getAllPharmacyNames();
    }

    public boolean checkConnectionWithPharmacy(int pharmacyId) {
        Boolean hasEreceipt = ePrescriptionService.checkEReceiptInPharmacy(pharmacyId);
        Boolean hasConsulting = consultingService.checkIfPatientHasConsulting(pharmacyId);
        Boolean hasExamination = examinationService.checkIfPatientHasExamination(pharmacyId);
        Boolean hasTakenReservedMedication = false;
        return (!hasEreceipt && !hasConsulting && !hasExamination && !hasTakenReservedMedication) ? false :true;
    }
}
