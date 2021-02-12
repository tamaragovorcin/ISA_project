package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.LackMedicineDTO;
import com.isaproject.isaproject.Model.Medicine.LackMedicine;
import com.isaproject.isaproject.Model.Users.Mark;

import java.util.List;

public interface ILackMedicineService {

    LackMedicine findById(Integer id);
    List<LackMedicine> findAll ();
    LackMedicine save(LackMedicineDTO lackMedicineDTO);
    void delete(LackMedicine mark);
    LackMedicine update(LackMedicineDTO markDTO);
}
