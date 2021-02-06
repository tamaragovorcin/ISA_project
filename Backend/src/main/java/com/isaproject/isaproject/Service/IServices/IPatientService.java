package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.Pharmacist;

import java.util.List;
import java.util.Optional;

public interface IPatientService {
    Patient findById(Integer id);
    Patient findByEmail(String email);
    List<Patient> findAll ();
    List<Patient> findAllByName (String name);
    List<Patient> findByName(String name, String surname);
    Patient findByName(String name);
    Patient save(PersonUserDTO userRequest);
    void delete(Patient userRequest);
    Patient update(PersonUserDTO userRequest);

}

