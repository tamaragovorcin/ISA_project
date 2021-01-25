package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.MedicationDTO;
import com.isaproject.isaproject.DTO.SpecificationDTO;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Medicine.Specification;
import com.isaproject.isaproject.Repository.AuthorityRepository;
import com.isaproject.isaproject.Repository.MedicationRepository;
import com.isaproject.isaproject.Repository.PatientRepository;
import com.isaproject.isaproject.Service.IServices.IMedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MedicationService implements IMedicationService {
    @Autowired
    MedicationRepository medicationRepository;

    @Override
    public Medication findById(Integer id) {
        return null;
    }

    @Override
    public List<Medication> findAll() {
        return medicationRepository.findAll();
    }

    @Override
    public Medication save(MedicationDTO medicationDTO) {
        Long a;
        Medication medication1 = new Medication();
        medication1.setName(medicationDTO.getName());
        medication1.setCode(medicationDTO.getCode());
        medication1.setForm(medicationDTO.getForm());
        medication1.setType(medicationDTO.getType());
        medication1.setIssuanceRegime(medicationDTO.getIssuanceRegime());
        medication1.setLoyaltyPoints(medicationDTO.getLoyaltyPoints());
        medication1.setMark(medicationDTO.getMark());
        Specification specification =  new Specification();
        specification.setContraIndications(medicationDTO.getSpecification().getContraIndications());
        specification.setManufacturer(medicationDTO.getSpecification().getManufacturer());
        specification.setStructure(medicationDTO.getSpecification().getStructure());
        specification.setRecommendedConsumption(medicationDTO.getSpecification().getRecommendedConsumption());

        medication1.setSpecification(specification);

        return medicationRepository.save(medication1);
       
    }

    @Override
    public void delete(Medication medication) {

    }
}