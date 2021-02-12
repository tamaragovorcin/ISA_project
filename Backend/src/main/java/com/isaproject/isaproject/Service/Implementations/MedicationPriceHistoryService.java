package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.MedicationPriceHistoryDTO;
import com.isaproject.isaproject.Model.HelpModel.MedicationPriceHistory;
import com.isaproject.isaproject.Repository.MedicationPriceHistoryRepository;
import com.isaproject.isaproject.Service.IServices.IMedicationPriceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationPriceHistoryService implements IMedicationPriceHistoryService {
    @Autowired
    MedicationPriceHistoryRepository medicationPriceHistoryRepository;


    @Override
    public MedicationPriceHistory findById(Integer id) {
        return medicationPriceHistoryRepository.getOne(id);
    }

    @Override
    public List<MedicationPriceHistory> findAll() {
        return medicationPriceHistoryRepository.findAll();
    }

    @Override
    public MedicationPriceHistory save(MedicationPriceHistoryDTO medicationDTO) {
        MedicationPriceHistory medicationPriceHistory = new MedicationPriceHistory();
        medicationPriceHistory.setMedication_id(medicationDTO.getMedication());
        medicationPriceHistory.setPharmacy_id(medicationDTO.getPharmacy());
        medicationPriceHistory.setPrice(medicationDTO.getPrice());
        medicationPriceHistory.setStartDate(medicationDTO.getStartDate());
        medicationPriceHistory.setEndDate(medicationDTO.getEndDate());
        return medicationPriceHistoryRepository.save(medicationPriceHistory);

    }

    @Override
    public void delete(MedicationPriceHistory medication) {
        medicationPriceHistoryRepository.delete(medication);
    }

    @Override
    public MedicationPriceHistory update(MedicationPriceHistory medicationPrice) {
        return null;
    }
}
