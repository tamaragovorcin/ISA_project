package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.DermatologistDTO;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Users.Dermatologist;

import java.util.List;

public interface IDermatologistService {
    Dermatologist findById(Integer id);
    Dermatologist findByEmail(String email);
    List<Dermatologist> findAll ();
    void delete(Dermatologist admin);
    Dermatologist save(PersonUserDTO userRequest);
}
