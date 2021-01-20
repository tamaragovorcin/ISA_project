package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.AddressDTO;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Users.Address;
import com.isaproject.isaproject.Model.Users.Authority;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.Supplier;
import com.isaproject.isaproject.Repository.AuthorityRepository;
import com.isaproject.isaproject.Repository.SupplierRepository;
import com.isaproject.isaproject.Service.IServices.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return supplierRepository.save(supplier);
    }
}
