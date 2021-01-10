package com.isaproject.isaproject.psw.service;

import com.isaproject.isaproject.psw.model.Pharmacy;
import com.isaproject.isaproject.psw.model.UrgentOrder;

import java.util.List;

public interface IUrgentOrderService {
    boolean add(UrgentOrder order);
    void SaveUrgentOrder(String medicine);
    boolean remove(String id);
    boolean update(UrgentOrder order);
    UrgentOrder get(String id);
    List<UrgentOrder> getAll();

    List<UrgentOrder> getAllByPharmacyApi(String api);
}
