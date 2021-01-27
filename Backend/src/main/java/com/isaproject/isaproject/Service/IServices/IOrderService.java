package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.MedicationsInOrderDTO;
import com.isaproject.isaproject.DTO.OrderDTO;
import com.isaproject.isaproject.Model.Orders.MedicationInOrder;
import com.isaproject.isaproject.Model.Orders.Order;

import java.util.List;

public interface IOrderService {
    Order findById(Integer id);
    List<Order> findAll ();
    Order save(OrderDTO orderDTO);
    void delete(Order order);
}
