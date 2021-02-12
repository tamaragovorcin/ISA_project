package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
