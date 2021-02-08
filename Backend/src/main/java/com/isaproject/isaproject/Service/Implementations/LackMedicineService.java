package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.LackMedicineDTO;
import com.isaproject.isaproject.Model.HelpModel.LoyaltyProgram;
import com.isaproject.isaproject.Model.Medicine.LackMedicine;
import com.isaproject.isaproject.Repository.LackMedicineRepository;
import com.isaproject.isaproject.Repository.LoyaltyProgramRepository;
import com.isaproject.isaproject.Service.IServices.ILackMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LackMedicineService implements ILackMedicineService {

    @Autowired
    LackMedicineRepository lackMedicineRepository;


    @Override
    public LackMedicine findById(Integer id) {
        return lackMedicineRepository.findById(id).get();
    }

    @Override
    public List<LackMedicine> findAll() {
        return lackMedicineRepository.findAll();
    }

    @Override
    public LackMedicine save(LackMedicineDTO lackMedicineDTO) {
        LackMedicine lackMedicine = new LackMedicine();
        lackMedicine.setNameMedicine(lackMedicine.getNameMedicine());
        lackMedicine.setNamePharmacy(lackMedicine.getNamePharmacy());
        return this.lackMedicineRepository.save(lackMedicine);
    }

    @Override
    public void delete(LackMedicine mark) {

    }

    @Override
    public LackMedicine update(LackMedicineDTO markDTO) {
        return null;
    }
}
