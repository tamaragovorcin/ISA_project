package com.isaproject.isaproject.Service.Implementations;

import java.util.ArrayList;
import java.util.List;

import com.isaproject.isaproject.Model.Users.Authority;
import com.isaproject.isaproject.Repository.AuthorityRepository;
import com.isaproject.isaproject.Service.IServices.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService implements IAuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<Authority> findById(Integer id) {
        Authority auth = this.authorityRepository.getOne(id);
        List<Authority> auths = new ArrayList<>();
        auths.add(auth);
        return auths;
    }

    @Override
    public List<Authority> findByname(String name) {
        Authority auth = this.authorityRepository.findByName(name);
        List<Authority> auths = new ArrayList<>();
        auths.add(auth);
        return auths;
    }


}