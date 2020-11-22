package com.isaproject.isaproject.psw;

import java.util.List;

public interface IHospitalService {
	boolean add(Hospital hospital);
	boolean remove(String id);
	boolean update(Hospital hospital);
	Hospital get(String id);
	List<Hospital> getAll();
}
