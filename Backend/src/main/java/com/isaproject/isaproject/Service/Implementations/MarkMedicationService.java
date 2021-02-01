package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.Model.Users.Mark;
import com.isaproject.isaproject.Model.Users.MarkMedication;
import com.isaproject.isaproject.Repository.MarkMedicationRepository;
import com.isaproject.isaproject.Repository.MarkRepository;
import com.isaproject.isaproject.Service.IServices.IMarkMedicationService;
import com.isaproject.isaproject.Service.IServices.IMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkMedicationService implements IMarkMedicationService {

    @Autowired
    MarkMedicationRepository markRepository;
    @Override
    public MarkMedication findById(Integer id) {
        return null;
    }

    @Override
    public List<MarkMedication> findAll() {
        return markRepository.findAll();
    }

    @Override
    public MarkMedication save(MarkMedication markDTO) {
        return markRepository.save(markDTO);
    }

    @Override
    public void delete(MarkMedication mark) {

    }

    @Override
    public MarkMedication update(MarkMedication markDTO) {
        return null;
    }
}
