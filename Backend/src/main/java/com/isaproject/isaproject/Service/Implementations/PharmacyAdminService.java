package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.AddressDTO;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.DTO.PharmacyAdminDTO;
import com.isaproject.isaproject.DTO.PharmacyDTO;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.*;
import com.isaproject.isaproject.Repository.AuthorityRepository;
import com.isaproject.isaproject.Repository.DermatologistRepository;
import com.isaproject.isaproject.Repository.PharmacyAdminRepository;
import com.isaproject.isaproject.Repository.PharmacyRepository;
import com.isaproject.isaproject.Service.IServices.IPharmacyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacyAdminService implements IPharmacyAdminService {
    @Autowired
    PharmacyAdminRepository pharmacyAdminRepository;
    @Autowired
    private AuthorityService authService;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PharmacyService pharmacyService;
    @Autowired
    private PharmacyRepository pharmacyRepository;
    @Autowired
    private DermatologistRepository dermatologistRepository;

    @Override
    public PharmacyAdmin findById(Integer id) {
        return pharmacyAdminRepository.findById(id).get();
    }

    @Override
    public PharmacyAdmin findByEmail(String email) {
        return pharmacyAdminRepository.findByEmail(email);
    }

    @Override
    public List<PharmacyAdmin> findAll() {
        return pharmacyAdminRepository.findAll();
    }

    @Override
    public PharmacyAdmin save(PharmacyAdminDTO userRequest) {
        PharmacyAdmin pharmacyAdmin =  new PharmacyAdmin();
        pharmacyAdmin.setName(userRequest.getFirstname());
        pharmacyAdmin.setSurname(userRequest.getSurname());
        AddressDTO addressDTO = userRequest.getAddress();
        Address address = new Address(addressDTO.getTown(),addressDTO.getStreet(),addressDTO.getNumber(),addressDTO.getPostalCode(),addressDTO.getCountry());
        pharmacyAdmin.setAddress(address);
        pharmacyAdmin.setEmail(userRequest.getEmail());
        pharmacyAdmin.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        pharmacyAdmin.setFirstLogged(true);
        pharmacyAdmin.setPhoneNumber(userRequest.getPhonenumber());
        Authority authorityPharmacyAdmin = authService.findByname("ROLE_PHARMACY_ADMIN");
        List<Authority> auth = new ArrayList<Authority>();
        if(authorityPharmacyAdmin==null) {
            authorityRepository.save(new Authority("ROLE_PHARMACY_ADMIN"));
            auth.add(authService.findByname("ROLE_PHARMACY_ADMIN"));
        }
        else {
            auth.add(authorityPharmacyAdmin);
        }
        pharmacyAdmin.setAuthorities(auth);
        pharmacyAdmin.setEnabled(true);
        pharmacyAdmin.setPharmacy(userRequest.getPharmacy());
        return pharmacyAdminRepository.save(pharmacyAdmin);
    }
    @Override
    public void delete(PharmacyAdmin admin) {
        pharmacyAdminRepository.delete(admin);
    }

    public void removeDermatologistFromPharmacy(Integer pharmacyId, Dermatologist dermatologist){
        Pharmacy pharmacy = pharmacyService.findById(pharmacyId);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(pharmacy.getPharmacyName());
        for(Dermatologist dermatologist1 : pharmacy.getDermatologists()){
            System.out.println(dermatologist1.getName() +" "+dermatologist.getSurname() );
        }
        //fale provjere da li je zakazan neki termin kod njega
        pharmacy.getDermatologists().remove(dermatologist);
        for(Dermatologist dermatologist1 : pharmacy.getDermatologists()){
            System.out.println(dermatologist1.getName() +" "+dermatologist.getSurname() );
        }
        this.dermatologistRepository.save(dermatologist);

    }
}
