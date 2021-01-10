package com.isaproject.isaproject.psw.service;

import com.isaproject.isaproject.psw.model.Medicine;
import com.isaproject.isaproject.psw.model.MedicineInPharmacy;
import com.isaproject.isaproject.psw.repository.MedicineRepository;
import com.isaproject.isaproject.psw.repository.MedicinesPharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicinesPharamcyService implements IMedicinesPharmacyService{
    @Autowired
        private MedicinesPharmacyRepository repository;

    @Override
    public boolean add(MedicineInPharmacy medicine) {
        try{

            repository.save(medicine);
            return true;
        }
        catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean remove(String id) {
        return false;
    }

    @Override
    public boolean update(MedicineInPharmacy medicine) {
        try{

            //
            return true;
        }
        catch (Exception e) {
            return false;
        }


    }

    @Override
    public MedicineInPharmacy get(String id) {
        return null;
    }

    @Override
    public List<MedicineInPharmacy> getAll() {
        return (List<MedicineInPharmacy>) repository.findAll();
    }
}
