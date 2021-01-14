package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.Model.Users.Authority;

import java.util.List;


public interface IAuthorityService {
    List<Authority> findById(Integer id);
    List<Authority> findByname(String name);
}