package com.isaproject.isaproject.psw.service;
import com.isaproject.isaproject.psw.model.MedicineInPharmacy;

import java.util.List;

public interface IMedicinesPharmacyService {
    boolean add(MedicineInPharmacy medicine);
    boolean remove(String id);
    boolean update(MedicineInPharmacy medicine);
    MedicineInPharmacy get(String id);
    List<MedicineInPharmacy> getAll();
}
