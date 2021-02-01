package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.Model.Users.MarkPharmacist;
import com.isaproject.isaproject.Repository.MarkPharmacistRepository;
import com.isaproject.isaproject.Service.IServices.IMarkPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkPharmacistService implements IMarkPharmacistService {
    @Autowired
    MarkPharmacistRepository markRepository;

    @Override
    public MarkPharmacist findById(Integer id) {
        return null;
    }

    @Override
    public List<MarkPharmacist> findAll() {
        return markRepository.findAll();
    }

    @Override
    public MarkPharmacist save(MarkPharmacist markDTO) {
        return markRepository.save(markDTO);
    }

    @Override
    public void delete(MarkPharmacist mark) {

    }

    @Override
    public MarkPharmacist update(MarkPharmacist markDTO) {
        return null;
    }
}
