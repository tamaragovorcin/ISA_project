package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.ActionsDTO;
import com.isaproject.isaproject.Model.Pharmacy.Actions;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Repository.ActionsRepository;
import com.isaproject.isaproject.Service.IServices.IActionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ActionsService implements IActionsService {
    @Autowired
    ActionsRepository actionsRepository;
    @Autowired
    PharmacyService pharmacyService;

    @Override
    public Actions findById(Integer id) {
        return actionsRepository.findById(id).get();
    }

    @Override
    public List<Actions> findAll() {
        return actionsRepository.findAll();
    }

    @Override
    public Actions save(ActionsDTO action) {
        Pharmacy pharmacy = pharmacyService.findById(action.getPharmacy());
        Actions actions = new Actions(pharmacy, action.getDescription(), action.getExpiryDate());
        return actionsRepository.save(actions);
    }

}
