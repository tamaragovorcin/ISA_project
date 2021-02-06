package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.DermatologistDTO;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.DTO.PharmacistDTO;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.Pharmacist;

import java.util.List;

public interface IDermatologistService {
    Dermatologist findById(Integer id);
    Dermatologist findByEmail(String email);
    List<Dermatologist> findByPharmacy(String name);
    List<Dermatologist> findByMark(int markMin, int markMax);
    List<Dermatologist> findByName(String name, String surname);
    List<Dermatologist> findAll ();
    Dermatologist save(DermatologistDTO userRequest);
    void delete(Pharmacist userRequest);
    Dermatologist update(Dermatologist userRequest);
    Dermatologist updateMark(Dermatologist dermatologist);
}

