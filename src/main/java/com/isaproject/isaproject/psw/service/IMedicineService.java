package com.isaproject.isaproject.psw.service;

import java.util.List;

import com.isaproject.isaproject.psw.model.Medicine;
import com.isaproject.isaproject.psw.model.Pharmacy;

public interface IMedicineService {
	boolean add(Medicine medicine);
	boolean remove(String id);
	boolean update(Medicine medicine);
	Medicine get(String id);
	List<Medicine> getAll();
}
