package com.isaproject.isaproject.psw.repository;

import org.springframework.stereotype.Repository;

import com.isaproject.isaproject.psw.model.Hospital;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface HospitalRepository extends CrudRepository<Hospital, String>{

}
