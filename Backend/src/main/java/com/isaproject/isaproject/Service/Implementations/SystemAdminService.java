package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.AddressDTO;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Users.Address;
import com.isaproject.isaproject.Model.Users.Authority;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.SystemAdmin;
import com.isaproject.isaproject.Repository.AuthorityRepository;
import com.isaproject.isaproject.Repository.DermatologistRepository;
import com.isaproject.isaproject.Repository.SystemAdminRepository;
import com.isaproject.isaproject.Service.IServices.ISystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemAdminService implements ISystemAdminService {
    @Autowired
    SystemAdminRepository systemAdminRepository;
    @Autowired
    private AuthorityService authService;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SystemAdmin findById(Integer id) {
        return systemAdminRepository.findById(id).get();
    }

    @Override
    public SystemAdmin findByEmail(String email) {
        return systemAdminRepository.findByEmail(email);
    }

    @Override
    public List<SystemAdmin> findAll() {
        return systemAdminRepository.findAll();
    }

    @Override
    public SystemAdmin save(PersonUserDTO userRequest) {
        SystemAdmin systemAdmin =  new SystemAdmin();
        systemAdmin.setName(userRequest.getFirstname());
        systemAdmin.setSurname(userRequest.getSurname());
        AddressDTO addressDTO = userRequest.getAddress();
        Address address = new Address(addressDTO.getTown(),addressDTO.getStreet(),addressDTO.getNumber(),addressDTO.getPostalCode(),addressDTO.getCountry());
        systemAdmin.setAddress(address);
        systemAdmin.setEmail(userRequest.getEmail());
        systemAdmin.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        systemAdmin.setFirstLogged(true);
        systemAdmin.setPhoneNumber(userRequest.getPhonenumber());
        Authority authoritySystemAdmin = authService.findByname("ROLE_SYSTEM_ADMIN");
        List<Authority> auth = new ArrayList<Authority>();
        if(authoritySystemAdmin==null) {
            authorityRepository.save(new Authority("ROLE_SYSTEM_ADMIN"));
            auth.add(authService.findByname("ROLE_SYSTEM_ADMIN"));
        }
        else {
            auth.add(authoritySystemAdmin);
        }
        systemAdmin.setAuthorities(auth);
        systemAdmin.setEnabled(true);
        systemAdmin.setMainAdmin(true);
        return systemAdminRepository.save(systemAdmin);
    }
}
