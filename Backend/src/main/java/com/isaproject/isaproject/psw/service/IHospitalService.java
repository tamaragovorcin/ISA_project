package com.isaproject.isaproject.psw.service;

import java.util.List;

import com.isaproject.isaproject.psw.model.Hospital;

public interface IHospitalService {
	boolean add(Hospital hospital);
	boolean remove(String id);
	boolean update(Hospital hospital);
	Hospital get(String id);
	List<Hospital> getAll();
}
