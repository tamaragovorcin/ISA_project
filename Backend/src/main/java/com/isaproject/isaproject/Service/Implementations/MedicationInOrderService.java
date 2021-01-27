package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.MedicationsInOrderDTO;
import com.isaproject.isaproject.Model.Orders.MedicationInOrder;
import com.isaproject.isaproject.Repository.MedicationInOrderRepository;
import com.isaproject.isaproject.Service.IServices.IMedicationInOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MedicationInOrderService implements IMedicationInOrderService {
    @Autowired
    MedicationInOrderRepository medicationInOrderRepository;

    @Override
    public MedicationInOrder findById(Integer id) {
        return medicationInOrderRepository.getOne(id);
    }

    @Override
    public List<MedicationInOrder> findAll() {
        return medicationInOrderRepository.findAll();
    }

    @Override
    public MedicationInOrder save(MedicationsInOrderDTO medicationDTO) {
        MedicationInOrder medicationInOrder = new MedicationInOrder();
        medicationInOrder.setMedicine(medicationDTO.getMedicine());
        medicationInOrder.setQuantity(medicationDTO.getQuantity());
        return  medicationInOrderRepository.save(medicationInOrder);
    }

    @Override
    public void delete(MedicationInOrder medication) {

    }
}
