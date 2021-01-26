package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.MedicationDTO;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Users.Patient;

import java.util.List;

public interface IMedicationService {
    Medication findById(Integer id);
    List<Medication> findAll ();
    Medication save(MedicationDTO medicationDTO);
    void delete(Medication medication);
    Medication findByName(String name);
}
