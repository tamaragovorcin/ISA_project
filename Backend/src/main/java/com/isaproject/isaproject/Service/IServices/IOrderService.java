package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.OrderDTO;
import com.isaproject.isaproject.DTO.OrderUpdateDTO;
import com.isaproject.isaproject.Model.Orders.Order;

import java.util.List;

public interface IOrderService {
    Order findById(Integer id);
    List<Order> findAll ();
    Order save(OrderDTO orderDTO);
    Boolean delete(Order order);
    Boolean update(OrderUpdateDTO orderDTO);
}
