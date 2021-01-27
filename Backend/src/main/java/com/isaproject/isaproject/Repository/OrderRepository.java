package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
