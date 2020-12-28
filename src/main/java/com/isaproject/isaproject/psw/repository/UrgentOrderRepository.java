package com.isaproject.isaproject.psw.repository;

import com.isaproject.isaproject.psw.model.Pharmacy;
import com.isaproject.isaproject.psw.model.UrgentOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrgentOrderRepository  extends CrudRepository<UrgentOrder, String> {
}
