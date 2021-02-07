package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.MedicationPriceDTO;
import com.isaproject.isaproject.DTO.MedicationReservationDTO;
import com.isaproject.isaproject.Model.HelpModel.MedicationPrice;
import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;

import java.util.List;

public interface IMedicationPriceService {
    MedicationPrice findById(Integer id);
    List<MedicationPrice> findAll ();
    MedicationPrice save(MedicationPriceDTO medicationDTO);
    void delete(MedicationPrice medication);
    MedicationPrice update(MedicationPrice medicationPrice);
}
