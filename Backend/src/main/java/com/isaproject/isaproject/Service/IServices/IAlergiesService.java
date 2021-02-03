package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.AlergiesDTO;
import com.isaproject.isaproject.DTO.MedicationReservationDTO;
import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Model.HelpModel.PatientsMedicationAlergy;

import java.util.List;

public interface IAlergiesService {
    PatientsMedicationAlergy findById(Integer id);
    List<PatientsMedicationAlergy> findAll ();
    PatientsMedicationAlergy save(AlergiesDTO alergiesDTO);
    void delete(PatientsMedicationAlergy medication);
}
