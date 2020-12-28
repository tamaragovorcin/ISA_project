package com.isaproject.isaproject.psw.service;

import com.isaproject.isaproject.psw.model.MedicineInPharmacy;
import com.isaproject.isaproject.psw.model.UrgentOrder;
import com.isaproject.isaproject.psw.repository.MedicinesPharmacyRepository;
import com.isaproject.isaproject.psw.repository.UrgentOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UrgentOrderService implements IUrgentOrderService{
    @Autowired
    private UrgentOrderRepository repository;

    @Override
    public void SaveUrgentOrder(String medicine) {
        System.out.println("DOSAOOOOOOO METODa          " + medicine);
        String []parts = medicine.split(":");
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        List<UrgentOrder> ordersAll = getAll();
        int max = 0;
        for (UrgentOrder nextOrder: ordersAll) {
            if(nextOrder.getId()>max) {
                max = nextOrder.getId();
            }
        }
        max = max +1;

        UrgentOrder order = new UrgentOrder(max, parts[0].trim(),Integer.parseInt(parts[1]), parts[2].trim(),formatter.format(date));

        add(order);
    }

    @Override
    public boolean add(UrgentOrder order) {
        try{
            repository.save(order);
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
    public boolean update(UrgentOrder order) {
        return false;
    }

    @Override
    public UrgentOrder get(String id) {
        return null;
    }

    @Override
    public List<UrgentOrder> getAll() {
        return (List<UrgentOrder>) repository.findAll();
    }

    @Override
    public List<UrgentOrder> getAllByPharmacyApi(String api) {
        List<UrgentOrder> orders = getAll();
        List<UrgentOrder> ordersApi = new ArrayList<UrgentOrder>();
        for (UrgentOrder orderFound: orders) {
            if(orderFound.getPharmacyApi().equals(api)) {
                ordersApi.add(orderFound);
            }
        }
        return ordersApi;
    }
}
