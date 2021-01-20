package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.AddressDTO;
import com.isaproject.isaproject.DTO.PharmacyDTO;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Address;
import com.isaproject.isaproject.Repository.PharmacyRepository;
import com.isaproject.isaproject.Service.IServices.IPharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacyService implements IPharmacyService {
    @Autowired
    PharmacyRepository pharmacyRepository;

    @Override
    public Pharmacy findById(Integer id) {
        return pharmacyRepository.getOne(id);
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
        AddressDTO addressDTO = pharmacy.getAddress();
        Address address = new Address(addressDTO.getTown(),addressDTO.getStreet(),addressDTO.getNumber(),addressDTO.getPostalCode(),addressDTO.getCountry());
        pharmacy1.setAddress(address);
        pharmacy1.setConsultingPrice(pharmacy.getConsultingPrice());
        return pharmacyRepository.save(pharmacy1);
    }
}
