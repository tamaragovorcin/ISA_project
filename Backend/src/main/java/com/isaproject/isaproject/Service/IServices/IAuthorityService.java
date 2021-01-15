package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.Model.Users.Authority;

import java.util.List;


public interface IAuthorityService {
    Authority findById(Integer id);
    Authority findByname(String name);

}