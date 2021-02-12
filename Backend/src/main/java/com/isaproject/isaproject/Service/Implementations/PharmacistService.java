
package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.AddressDTO;
import com.isaproject.isaproject.DTO.PersonUserDTO;

import com.isaproject.isaproject.DTO.PharmacistDTO;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Schedule.WorkingHoursPharmacist;
import com.isaproject.isaproject.Model.Users.*;
import com.isaproject.isaproject.Repository.*;
import com.isaproject.isaproject.Service.IServices.IPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private PharmacyRepository pharmacyRepository;
    @Autowired
    private WorkingHoursPharmacistRepository workingHoursPharmacistRepository;
    @Autowired
    private ConsultingService consultingService;

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
        Pharmacy pharmacy = pharmacyRepository.getOne(userRequest.getPharmacyID());
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
        pharmacist.setPharmacy(pharmacy);
        return pharmacistRepository.save(pharmacist);
    }


    @Override
    public String delete(Pharmacist userRequest) {
        List<ConfirmationToken> confirmationTokens = confirmationTokenRepository.findAll();
        ConfirmationToken confirmationToken = new ConfirmationToken();


        for(Consulting consulting : consultingService.findAll()){
            if(consulting.getPharmacist().getId() == userRequest.getId() && !consulting.getCancelled() && consulting.getDate().isAfter(LocalDate.now())){
                throw  new IllegalStateException("Pharmacist can not be removed, he/she has scheduled appointments.");
            }
        }
        for(ConfirmationToken confirmationToken1 : confirmationTokens){

            if (confirmationToken1.getPersonUser().getId()==userRequest.getId()){
                confirmationTokenRepository.delete(confirmationToken1);
            }

        }
        for(WorkingHoursPharmacist workingHoursPharmacist : workingHoursPharmacistRepository.findAll()){
            if(workingHoursPharmacist.getPharmacist().getId() == userRequest.getId()){
                workingHoursPharmacistRepository.delete(workingHoursPharmacist);
            }
        }
        pharmacistRepository.delete(userRequest);
        return  "Pharmacist is successfully deleted";
    }
@Override
    public Pharmacist update(PharmacistDTO dto){
        Pharmacist admin = findByEmail(dto.getEmail());
        Integer id = admin.getId();
        Pharmacist per = pharmacistRepository.getOne(id);
        per.setName(dto.getFirstname());
        per.setPhoneNumber(dto.getPhonenumber());
        per.setSurname(dto.getSurname());
        AddressDTO addressDTO = dto.getAddress();
        Address address = new Address(addressDTO.getTown(),addressDTO.getStreet(),addressDTO.getNumber(),addressDTO.getPostalCode(),addressDTO.getCountry());
        per.setAddress(address);
        return this.pharmacistRepository.save(per);
    }

    @Override
    public Pharmacist updateMark(Pharmacist pharmacist) {
        return pharmacistRepository.save(pharmacist);
    }

    @Override
    public List<Pharmacist> findByPharmacy(String name) {
        return pharmacistRepository.findByPharmacy(pharmacyRepository.findByPharmacyName(name));
    }
    @Override
    public List<Pharmacist> findByMark(int markMin, int markMax) {
        List<Pharmacist> pharmacists = new ArrayList<>();
        for(Pharmacist pharmacist : pharmacistRepository.findAll()){
            if(pharmacist.getMarkPharmacist() <= markMax && pharmacist.getMarkPharmacist() >= markMin){
                pharmacists.add(pharmacist);
            }
        }
        return pharmacists;
    }
    @Override
    public List<Pharmacist> findByName(String name, String surname) {
        List<Pharmacist> pharmacists = new ArrayList<>();
        for(Pharmacist pharmacist : pharmacistRepository.findAll()){
            if(pharmacist.getSurname().toLowerCase().contains(surname.toLowerCase()) && pharmacist.getName().toLowerCase().contains(name.toLowerCase())){
                pharmacists.add(pharmacist);
            }
        }
        return pharmacists;
    }
}
