package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Users.SystemAdmin;

import java.util.List;

public interface ISystemAdminService {
    SystemAdmin findById(Integer id);
    SystemAdmin findByEmail(String email);
    List<SystemAdmin> findAll ();
    SystemAdmin save(PersonUserDTO userRequest);
}
