package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.AddressDTO;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.DTO.SupplierMedicationsDTO;
import com.isaproject.isaproject.Model.Users.*;
import com.isaproject.isaproject.Repository.AuthorityRepository;
import com.isaproject.isaproject.Repository.SupplierRepository;
import com.isaproject.isaproject.Service.IServices.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService implements ISupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    private AuthorityService authService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Supplier findById(Integer id) {
        return supplierRepository.findById(id).get();
    }

    @Override
    public Supplier findByEmail(String email) {
        return supplierRepository.findByEmail(email);
    }

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier save(PersonUserDTO userRequest) {
        Supplier supplier =  new Supplier();
        supplier.setName(userRequest.getFirstname());
        supplier.setSurname(userRequest.getSurname());
        AddressDTO addressDTO = userRequest.getAddress();
        Address address = new Address(addressDTO.getTown(),addressDTO.getStreet(),addressDTO.getNumber(),addressDTO.getPostalCode(),addressDTO.getCountry());
        supplier.setAddress(address);
        supplier.setEmail(userRequest.getEmail());
        supplier.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        supplier.setFirstLogged(true);
        supplier.setPhoneNumber(userRequest.getPhonenumber());
        Authority authoritySupplier = authService.findByname("ROLE_SUPPLIER");
        List<Authority> auth = new ArrayList<Authority>();
        if(authoritySupplier==null) {
            authorityRepository.save(new Authority("ROLE_SUPPLIER"));
            auth.add(authService.findByname("ROLE_SUPPLIER"));
        }
        else {
            auth.add(authoritySupplier);
        }
        supplier.setAuthorities(auth);
        supplier.setEnabled(true);
        return this.supplierRepository.save(supplier);
    }

    public Supplier update(Supplier userRequest) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();

        Supplier supplier= findById(user.getId());
        supplier.setAddress(userRequest.getAddress());
        List<Authority> auth = new ArrayList<Authority>();
        Authority authoritySupplier = authService.findByname("ROLE_SUPPLIER");

        if(authoritySupplier==null) {
            authorityRepository.save(new Authority("ROLE_SUPPLIER"));
            auth.add(authService.findByname("ROLE_SUPPLIER"));
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
        return this.supplierRepository.save(supplier);
    }
}
