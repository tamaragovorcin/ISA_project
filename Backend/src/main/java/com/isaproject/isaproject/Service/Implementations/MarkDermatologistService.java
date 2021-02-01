package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.Model.Users.MarkDermatologist;
import com.isaproject.isaproject.Repository.MarkDermatologistRepository;
import com.isaproject.isaproject.Repository.MarkMedicationRepository;
import com.isaproject.isaproject.Service.IServices.IMarkDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MarkDermatologistService implements IMarkDermatologistService {
    @Autowired
    MarkDermatologistRepository markRepository;

    @Override
    public MarkDermatologist findById(Integer id) {
        return null;
    }

    @Override
    public List<MarkDermatologist> findAll() {
        return markRepository.findAll();
    }

    @Override
    public MarkDermatologist save(MarkDermatologist markDTO) {
        return markRepository.save(markDTO);
    }

    @Override
    public void delete(MarkDermatologist mark) {

    }

    @Override
    public MarkDermatologist update(MarkDermatologist markDTO) {
        return null;
    }
}
