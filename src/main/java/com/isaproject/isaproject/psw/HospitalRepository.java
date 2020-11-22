package com.isaproject.isaproject.psw;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface HospitalRepository extends CrudRepository<Hospital, String>{

}
