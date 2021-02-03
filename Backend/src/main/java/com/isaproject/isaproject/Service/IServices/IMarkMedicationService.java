package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.Model.Users.Mark;
import com.isaproject.isaproject.Model.Users.MarkMedication;

import java.util.List;

public interface IMarkMedicationService {

    MarkMedication findById(Integer id);
    List<MarkMedication> findAll ();
    MarkMedication save(MarkMedication markDTO);
    void delete(MarkMedication mark);
    MarkMedication update(MarkMedication markDTO);
}
