package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Users.Dermatologist;

import java.util.List;

public interface IDermatologistService {
    Dermatologist findById(Integer id);
    Dermatologist findByEmail(String email);
    List<Dermatologist> findAll ();
    Dermatologist save(PersonUserDTO userRequest);
    Dermatologist update(Dermatologist dermatologist0);
}
