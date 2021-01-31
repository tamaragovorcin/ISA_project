package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.AddressDTO;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Users.*;
import com.isaproject.isaproject.Repository.AuthorityRepository;
import com.isaproject.isaproject.Repository.PatientRepository;
import com.isaproject.isaproject.Service.IServices.IAuthorityService;
import com.isaproject.isaproject.Service.IServices.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    private AuthorityService authService;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Patient findById(Integer id) {
        System.out.println("OVO JE "+id);
        return patientRepository.findById(id).get();
    }

    @Override
    public Patient findByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    public String getEmail(Integer id) {
        return patientRepository.getOne(id).getEmail();
    }
    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient save(PersonUserDTO userRequest) {
        Patient patient =  new Patient();
        patient.setName(userRequest.getFirstname());
        patient.setSurname(userRequest.getSurname());
        AddressDTO addressDTO = userRequest.getAddress();
        Address address = new Address(addressDTO.getTown(),addressDTO.getStreet(),addressDTO.getNumber(),addressDTO.getPostalCode(),addressDTO.getCountry());
        patient.setAddress(address);
        patient.setPenalties(0);
        patient.setPoints(0);
        patient.setLoyaltyCategory("REGULAR");
        patient.setEmail(userRequest.getEmail());
        patient.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        patient.setFirstLogged(true);
        patient.setEnabled(true);
        patient.setPhoneNumber(userRequest.getPhonenumber());
        Authority authorityPatient = authService.findByname("ROLE_PATIENT");
        List<Authority> auth = new ArrayList<Authority>();
        if(authorityPatient==null) {
            authorityRepository.save(new Authority("ROLE_PATIENT"));
            auth.add(authService.findByname("ROLE_PATIENT"));
        }
        else {
            auth.add(authorityPatient);
        }
        patient.setAuthorities(auth);

        return patientRepository.save(patient);
    }

    @Override
    public Patient update(PersonUserDTO userRequest) {



        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLL"+ user.getId());
        Patient patient= findById(user.getId());

        List<Authority> auth = new ArrayList<Authority>();
        Authority authoritySupplier = authService.findByname("ROLE_PATIENT");

        if(authoritySupplier==null) {
            authorityRepository.save(new Authority("ROLE_PATIENT"));
            auth.add(authService.findByname("ROLE_PATIENT"));
        }
        else {
            auth.add(authoritySupplier);
        }


        patient.setAuthorities(auth);

        patient.setEnabled(true);
        patient.setName(userRequest.getFirstname());
        patient.setSurname(userRequest.getSurname());
        AddressDTO addressDTO = userRequest.getAddress();

        Address address = new Address(addressDTO.getTown(),addressDTO.getStreet(),addressDTO.getNumber(),addressDTO.getPostalCode(),addressDTO.getCountry());
        patient.setAddress(address);
        patient.setPhoneNumber(userRequest.getPhonenumber());
        patient.setPenalties(0);
        patient.setPoints(0);
        patient.setLoyaltyCategory("REGULAR");
        patient.setEmail(userRequest.getEmail());
        patient.setPassword(patient.getPassword());
        patient.setFirstLogged(false);



        return patientRepository.save(patient);
    }

    @Override
    public void delete(Patient patient) {

        patientRepository.delete(patient);


    }
}
