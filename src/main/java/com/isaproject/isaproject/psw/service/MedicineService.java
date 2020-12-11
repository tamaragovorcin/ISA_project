package com.isaproject.isaproject.psw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isaproject.isaproject.psw.model.Medicine;
import com.isaproject.isaproject.psw.model.Pharmacy;
import com.isaproject.isaproject.psw.repository.MedicineRepository;
import com.isaproject.isaproject.psw.repository.PharmacyRepository;

@Service
public class MedicineService implements IMedicineService{

	@Autowired
	    private MedicineRepository repository;
	 
	@Override
	public boolean add(Medicine medicine) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Medicine medicine) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Medicine get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Medicine> getAll() {
		return (List<Medicine>) repository.findAll();
	}

}
