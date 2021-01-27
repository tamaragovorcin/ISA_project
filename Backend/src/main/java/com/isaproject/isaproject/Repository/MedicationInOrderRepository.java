package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Orders.MedicationInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationInOrderRepository extends JpaRepository<MedicationInOrder, Integer> {
}
