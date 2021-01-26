package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.AddressDTO;
import com.isaproject.isaproject.DTO.PharmacistDTO;
import com.isaproject.isaproject.Model.Users.Address;
import com.isaproject.isaproject.Model.Users.Authority;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import com.isaproject.isaproject.Model.Users.PharmacyAdmin;
import com.isaproject.isaproject.Repository.AuthorityRepository;
import com.isaproject.isaproject.Repository.PharmacistRepository;
import com.isaproject.isaproject.Service.IServices.IPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacistService implements IPharmacistService {
    @Autowired
    PharmacistRepository pharmacistRepository;
    @Autowired
    private AuthorityService authService;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Pharmacist findById(Integer id) {
        return pharmacistRepository.getOne(id);
    }

    @Override
    public Pharmacist findByEmail(String email) {
        return pharmacistRepository.findByEmail(email);
    }

    @Override
    public List<Pharmacist> findAll() {
        return pharmacistRepository.findAll();
    }

    @Override
    public Pharmacist save(PharmacistDTO userRequest) {
        Pharmacist pharmacist =  new Pharmacist();
        pharmacist.setName(userRequest.getFirstname());
        pharmacist.setSurname(userRequest.getSurname());
        AddressDTO addressDTO = userRequest.getAddress();
        Address address = new Address(addressDTO.getTown(),addressDTO.getStreet(),addressDTO.getNumber(),addressDTO.getPostalCode(),addressDTO.getCountry());
        pharmacist.setAddress(address);
        pharmacist.setEmail(userRequest.getEmail());
        pharmacist.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        pharmacist.setFirstLogged(true);
        pharmacist.setPhoneNumber(userRequest.getPhonenumber());
        Authority authorityPharmacist = authService.findByname("ROLE_PHARMACIST");
        List<Authority> auth = new ArrayList<Authority>();
        if(authorityPharmacist==null) {
            authorityRepository.save(new Authority("ROLE_PHARMACIST"));
            auth.add(authService.findByname("ROLE_PHARMACIST"));
        }
        else {
            auth.add(authorityPharmacist);
        }
        pharmacist.setAuthorities(auth);
        pharmacist.setEnabled(true);
        pharmacist.setPharmacy(userRequest.getPharmacy());
        return pharmacistRepository.save(pharmacist);
    }

    @Override
    public void delete(Pharmacist pharmacist) {
        pharmacistRepository.delete(pharmacist);
    }
}
