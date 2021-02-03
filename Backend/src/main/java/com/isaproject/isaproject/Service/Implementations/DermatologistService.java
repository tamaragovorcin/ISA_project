
package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.AddressDTO;
import com.isaproject.isaproject.DTO.DermatologistDTO;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.DTO.WorkingHoursDermatologistDTO;
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

    @Override
    public Dermatologist findById(Integer id) {
        return dermatologistRepository.findById(id).get();
    }

    @Override
    public Dermatologist findByEmail(String email) {
        return dermatologistRepository.findByEmail(email);
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
    public void delete(Pharmacist userRequest) {

    }

    @Override
    public Dermatologist update(Dermatologist userRequest) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();

        Dermatologist supplier= findById(user.getId());
        supplier.setAddress(userRequest.getAddress());
        List<Authority> auth = new ArrayList<Authority>();
        Authority authoritySupplier = authService.findByname("ROLE_DERMATOLOGISTT");

        if(authoritySupplier==null) {
            authorityRepository.save(new Authority("ROLE_DERMATOLOGISTT"));
            auth.add(authService.findByname("ROLE_DERMATOLOGISTT"));
        }
        else {
            auth.add(authoritySupplier);
        }
        supplier.setAuthorities(auth);
        supplier.setEmail(userRequest.getEmail());
        supplier.setEnabled(true);
        supplier.setFirstLogged(userRequest.getFirstLogged());
        supplier.setName(userRequest.getName());
        supplier.setSurname(userRequest.getSurname());
        supplier.setPhoneNumber(userRequest.getPhoneNumber());
        supplier.setPassword(supplier.getPassword());
        supplier.setLastPasswordResetDate(userRequest.getLastPasswordResetDate());
        return this.dermatologistRepository.save(supplier);

    }

    public Boolean addPharmacy(WorkingHoursDermatologistDTO dto) {
        System.out.println("pogodiooooooooooooooooooooooooooooooooooooo" +dto.getPharmacy().getPharmacyName());
        Dermatologist ph = findById(dto.getDermatologist().getId());
        try{
            ph.getPharmacies().add(dto.getPharmacy());
            dermatologistRepository.save(ph);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}