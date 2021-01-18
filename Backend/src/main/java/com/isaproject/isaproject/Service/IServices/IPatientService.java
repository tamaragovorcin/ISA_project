package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Users.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientService {
    Patient findById(Integer id);
    Patient findByEmail(String email);
    List<Patient> findAll ();
    Patient save(PersonUserDTO userRequest);

}

