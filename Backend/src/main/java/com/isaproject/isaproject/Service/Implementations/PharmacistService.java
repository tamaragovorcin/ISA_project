package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.AddressDTO;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Users.*;
import com.isaproject.isaproject.Repository.AuthorityRepository;
import com.isaproject.isaproject.Repository.ConfirmationTokenRepository;
import com.isaproject.isaproject.Repository.DermatologistRepository;
import com.isaproject.isaproject.Repository.PharmacistRepository;
import com.isaproject.isaproject.Service.IServices.IPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacistService implements IPharmacistService {
    @Autowired
    PharmacistRepository pharmacistRepository;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private AuthorityService authService;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Pharmacist findById(Integer id) {
        return pharmacistRepository.findById(id).get();
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
    public Pharmacist save(PersonUserDTO userRequest) {
        Pharmacist pharmacist =  new Pharmacist();
        pharmacist.setName(userRequest.getFirstname());
        pharmacist.setSurname(userRequest.getSurname());
        AddressDTO addressDTO = userRequest.getAddress();
        Address address = new Address(addressDTO.getTown(),addressDTO.getStreet(),addressDTO.getNumber(),addressDTO.getPostalCode(),addressDTO.getCountry());
        pharmacist.setAddress(address);
        pharmacist.setMarkPharmacist(0);
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
        return pharmacistRepository.save(pharmacist);
    }
    @Override

    public void delete(Pharmacist userRequest) {
        List<ConfirmationToken> confirmationTokens = confirmationTokenRepository.findAll();
        ConfirmationToken confirmationToken = new ConfirmationToken();

        for(ConfirmationToken confirmationToken1 : confirmationTokens){

            if (confirmationToken1.getPersonUser().getId()==userRequest.getId()){
                confirmationTokenRepository.delete(confirmationToken1);
            }

        }

        pharmacistRepository.delete(userRequest);
    }

    public Pharmacist update(Pharmacist userRequest) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();

        Pharmacist supplier= findById(user.getId());
        supplier.setAddress(userRequest.getAddress());
        List<Authority> auth = new ArrayList<Authority>();
        Authority authoritySupplier = authService.findByname("ROLE_PHARMACIST");

        if(authoritySupplier==null) {
            authorityRepository.save(new Authority("ROLE_PHARMACIST"));
            auth.add(authService.findByname("ROLE_PHARMACIST"));
        }
        else {
            auth.add(authoritySupplier);
        }
        supplier.setAuthorities(auth);        supplier.setEmail(userRequest.getEmail());
        supplier.setEnabled(true);
        supplier.setFirstLogged(userRequest.getFirstLogged());
        supplier.setName(userRequest.getName());
        supplier.setSurname(userRequest.getSurname());
        supplier.setPhoneNumber(userRequest.getPhoneNumber());
        supplier.setPassword(supplier.getPassword());
        supplier.setLastPasswordResetDate(userRequest.getLastPasswordResetDate());
        return this.pharmacistRepository.save(supplier);
    }



}
