package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Users.PharmacyAdmin;
import com.isaproject.isaproject.Repository.AuthorityRepository;
import com.isaproject.isaproject.Repository.DermatologistRepository;
import com.isaproject.isaproject.Repository.PharmacyAdminRepository;
import com.isaproject.isaproject.Service.IServices.IPharmacyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public PharmacyAdmin save(PersonUserDTO userRequest) {
        return null;
    }
}
