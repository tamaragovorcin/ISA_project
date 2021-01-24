package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.Supplier;

import java.util.List;

public interface ISupplierService {
    Supplier findById(Integer id);
    Supplier findByEmail(String email);
    List<Supplier> findAll ();
    Supplier save(PersonUserDTO userRequest);
}
