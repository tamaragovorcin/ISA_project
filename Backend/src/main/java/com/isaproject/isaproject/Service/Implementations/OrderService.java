package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.MedicationsInOrderDTO;
import com.isaproject.isaproject.DTO.OrderDTO;
import com.isaproject.isaproject.Model.Orders.MedicationInOrder;
import com.isaproject.isaproject.Model.Orders.Order;
import com.isaproject.isaproject.Repository.MedicationInOrderRepository;
import com.isaproject.isaproject.Repository.OrderRepository;
import com.isaproject.isaproject.Service.IServices.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MedicationInOrderRepository medicationInOrderRepository;

    @Override
    public Order findById(Integer id) {
        return orderRepository.getOne(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(OrderDTO orderDTO) {
        Order order =  new Order();
        order.setDate(orderDTO.getDate());
        order.setStatus("CREATED");
        order.setPharmacyAdmin(orderDTO.getPharmacyAdmin());
        MedicationInOrder medicationInOrder = new MedicationInOrder();

        Order order1 = orderRepository.save(order);
        for(MedicationsInOrderDTO medDto : orderDTO.getMedicationsInOrderDTO()){
            MedicationInOrder medicationInOrder1 = new MedicationInOrder(medDto.getMedicine(),medDto.getQuantity());
            medicationInOrder1.setOrder(order1);
            medicationInOrderRepository.save(medicationInOrder1);
        }
        return order1;
    }

    @Override
    public void delete(Order order) {

    }
}
