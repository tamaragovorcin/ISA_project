package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.MedicationDTO;
import com.isaproject.isaproject.DTO.MedicationReservationDTO;
import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Model.Medicine.Medication;

import java.util.List;

public interface IMedicationReservationService {
    MedicationReservation findById(Integer id);
    List<MedicationReservation> findAll ();
    MedicationReservation save(MedicationReservationDTO medicationDTO);
    void delete(MedicationReservation medication);
}
