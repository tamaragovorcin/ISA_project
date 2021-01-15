package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;

import java.util.List;

public interface IPatientService {
    Patient findById(Integer id);
    Patient findByEmail(String email);
    List<Patient> findAll ();
    Patient save(PersonUserDTO userRequest);

}

