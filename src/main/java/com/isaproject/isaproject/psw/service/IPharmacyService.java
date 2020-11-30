package com.isaproject.isaproject.psw.service;

import java.util.List;

import com.isaproject.isaproject.psw.model.Pharmacy;

public interface IPharmacyService {
	boolean add(Pharmacy pharmacy);
	boolean remove(String id);
	boolean update(Pharmacy pharmacy);
	Pharmacy get(String id);
	List<Pharmacy> getAll();
}
