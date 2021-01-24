package com.isaproject.isaproject.Service.IServices;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;

import java.util.List;


public interface IPersonUserService {
    PersonUser findById(Integer id);
    PersonUser findByEmail(String email);
    List<PersonUser> findAll ();
    PersonUser save(PersonUserDTO userRequest);
    void delete(PersonUser userRequest);
    PersonUser update(PersonUserDTO userRequest, Integer id);
}
