package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.MedicationsInOrderDTO;
import com.isaproject.isaproject.DTO.OfferUpdateDTO;
import com.isaproject.isaproject.DTO.OrderDTO;
import com.isaproject.isaproject.DTO.OrderUpdateDTO;
import com.isaproject.isaproject.Model.Orders.MedicationInOrder;
import com.isaproject.isaproject.Model.Orders.Offer;
import com.isaproject.isaproject.Model.Orders.Order;
import com.isaproject.isaproject.Repository.MedicationInOrderRepository;
import com.isaproject.isaproject.Repository.OrderRepository;
import com.isaproject.isaproject.Service.IServices.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        order.setStatus("WAITING_OFFERS");
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
    @Override
    public Order update(OrderUpdateDTO order) {
        Order or = findById(order.getOrderId());
        for(MedicationInOrder medication : or.getMedicationInOrders()){
            medicationInOrderRepository.delete(medication);
        }
        or.setDate(order.getDate());
        MedicationInOrder medicationInOrder = new MedicationInOrder();


        for(MedicationsInOrderDTO medDto : order.getMedicationsInOrderDTO()){
            MedicationInOrder medicationInOrder1 = new MedicationInOrder(medDto.getMedicine(),medDto.getQuantity());
            medicationInOrder1.setOrder(or);
            medicationInOrderRepository.save(medicationInOrder1);
        }

        return orderRepository.save(or);
    }
}
