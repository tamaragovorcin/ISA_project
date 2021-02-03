package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.PrescriptionDTO;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Examinations.Prescription;

import java.util.List;

public interface IPrescriptionService {
    Prescription findById(Integer id);
    List<Prescription> findAll ();
    Prescription save(PrescriptionDTO prescriptionDTO);
    Prescription update(Prescription prescription);

}
