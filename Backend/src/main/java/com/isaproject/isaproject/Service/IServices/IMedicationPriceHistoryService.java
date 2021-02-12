package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.MedicationPriceDTO;
import com.isaproject.isaproject.DTO.MedicationPriceHistoryDTO;
import com.isaproject.isaproject.Model.HelpModel.MedicationPrice;
import com.isaproject.isaproject.Model.HelpModel.MedicationPriceHistory;

import java.util.List;

public interface IMedicationPriceHistoryService {
    MedicationPriceHistory findById(Integer id);
    List<MedicationPriceHistory> findAll ();
    MedicationPriceHistory save(MedicationPriceHistoryDTO medicationDTO);
    void delete(MedicationPriceHistory medication);
    MedicationPriceHistory update(MedicationPriceHistory medicationPrice);
}
