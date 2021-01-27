package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.MedicationReservationDTO;
import com.isaproject.isaproject.DTO.MedicationsInOrderDTO;
import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Model.Orders.MedicationInOrder;

import java.util.List;

public interface IMedicationInOrderService {
    MedicationInOrder findById(Integer id);
    List<MedicationInOrder> findAll ();
    MedicationInOrder save(MedicationsInOrderDTO medicationDTO);
    void delete(MedicationInOrder medication);
}
