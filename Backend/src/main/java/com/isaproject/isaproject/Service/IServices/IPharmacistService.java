
package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Users.Pharmacist;

import java.util.List;

public interface IPharmacistService {
    Pharmacist findById(Integer id);
    Pharmacist findByEmail(String email);
    List<Pharmacist> findAll ();
    Pharmacist save(PersonUserDTO userRequest);
    void delete(Pharmacist userRequest);
    Pharmacist update(Pharmacist userRequest);
}
