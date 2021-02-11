
package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.*;
import com.isaproject.isaproject.Repository.AuthorityRepository;
import com.isaproject.isaproject.Repository.DermatologistRepository;
import com.isaproject.isaproject.Service.IServices.IDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DermatologistService implements IDermatologistService {
    @Autowired
    DermatologistRepository dermatologistRepository;
    @Autowired
    private AuthorityService authService;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PharmacyService pharmacyService;

    @Override
    public Dermatologist findById(Integer id) {
        return dermatologistRepository.findById(id).get();
    }

    @Override
    public Dermatologist findByEmail(String email) {
        return dermatologistRepository.findByEmail(email);
    }

    @Override
    public List<Dermatologist> findByPharmacy(String name) {
        List<Dermatologist> dermatologists = new ArrayList<>();
        for(Dermatologist dermatologist : dermatologistRepository.findAll()){
            for(Pharmacy pharmacy : dermatologist.getPharmacies()){
                if(pharmacy.getPharmacyName().equals(name)){
                    dermatologists.add(dermatologist);
                }
            }

        }
        return dermatologists;
    }

    @Override
    public List<Dermatologist> findByMark(int markMin, int markMax) {
        List<Dermatologist> dermatologists = new ArrayList<>();
        for(Dermatologist dermatologist : dermatologistRepository.findAll()){
            if(dermatologist.getMarkDermatologist() <= markMax && dermatologist.getMarkDermatologist() >= markMin){
                dermatologists.add(dermatologist);
            }
        }
        return dermatologists;
    }

    @Override
    public List<Dermatologist> findByName(String name, String surname) {
        List<Dermatologist> dermatologists = new ArrayList<>();
        for(Dermatologist dermatologist : dermatologistRepository.findAll()){
            if(dermatologist.getSurname().toLowerCase().contains(surname.toLowerCase()) && dermatologist.getName().toLowerCase().contains(name.toLowerCase())){
                dermatologists.add(dermatologist);
            }
        }
        return dermatologists;
    }

    @Override
    public List<Dermatologist> findAll() {
        return dermatologistRepository.findAll();
    }

    @Override
    public Dermatologist save(DermatologistDTO userRequest) {
        Dermatologist dermatologist =  new Dermatologist();
        dermatologist.setName(userRequest.getFirstname());
        dermatologist.setSurname(userRequest.getSurname());
        AddressDTO addressDTO = userRequest.getAddress();
        Address address = new Address(addressDTO.getTown(),addressDTO.getStreet(),addressDTO.getNumber(),addressDTO.getPostalCode(),addressDTO.getCountry());
        dermatologist.setAddress(address);
        dermatologist.setMarkDermatologist(0);
        dermatologist.setEmail(userRequest.getEmail());
        dermatologist.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        dermatologist.setFirstLogged(true);
        dermatologist.setPhoneNumber(userRequest.getPhonenumber());
        Authority authorityDermatologist = authService.findByname("ROLE_DERMATOLOGIST");
        List<Authority> auth = new ArrayList<Authority>();
        if(authorityDermatologist==null) {
            authorityRepository.save(new Authority("ROLE_DERMATOLOGIST"));
            auth.add(authService.findByname("ROLE_DERMATOLOGIST"));
        }
        else {
            auth.add(authorityDermatologist);
        }
        dermatologist.setAuthorities(auth);
        dermatologist.setEnabled(true);
        return dermatologistRepository.save(dermatologist);
    }

    @Override

    public Dermatologist updateMark(Dermatologist dermatologist0) {
        return dermatologistRepository.save(dermatologist0);
    }

    @Override
    public void delete(Pharmacist userRequest) {

    }

    @Override
    public Dermatologist update(DermatologistDTO dto) {
        Dermatologist admin = findByEmail(dto.getEmail());
        Integer id = admin.getId();
        Dermatologist per = dermatologistRepository.getOne(id);
        per.setName(dto.getFirstname());
        per.setPhoneNumber(dto.getPhonenumber());
        per.setSurname(dto.getSurname());
        AddressDTO addressDTO = dto.getAddress();
        Address address = new Address(addressDTO.getTown(),addressDTO.getStreet(),addressDTO.getNumber(),addressDTO.getPostalCode(),addressDTO.getCountry());
        per.setAddress(address);
        return this.dermatologistRepository.save(per);
    }

    public Boolean addPharmacy(DermaotlogistPharmacyDTO dto) {
        Pharmacy pharmacy = pharmacyService.findById(dto.getPharmacyId());
        Dermatologist dermatologist = dermatologistRepository.findById(dto.getDermatologistId()).get();

        try{
            dermatologist.getPharmacies().add(pharmacy);
            dermatologistRepository.save(dermatologist);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
