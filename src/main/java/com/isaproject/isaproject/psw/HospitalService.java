package com.isaproject.isaproject.psw;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalService implements IHospitalService{

	@Autowired
	private HospitalRepository repository;
	
	@Override
	public boolean add(Hospital hospital) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Hospital hospital) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Hospital get(String id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Hospital> getAll() {
		return (List<Hospital>) repository.findAll();
	}

}
