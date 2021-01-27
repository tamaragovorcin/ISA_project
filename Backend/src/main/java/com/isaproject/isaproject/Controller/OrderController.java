package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.MedicationDTO;
import com.isaproject.isaproject.DTO.OrderDTO;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Orders.Order;
import com.isaproject.isaproject.Service.Implementations.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {
    @Autowired
    OrderService orderService;


    @PostMapping("/add")
    ResponseEntity<Order> register(@RequestBody OrderDTO orderDTO)
    {
        System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");

        Order order = orderService.save(orderDTO);
        System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");

        return order == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(order);
    }

    @GetMapping("")
    ResponseEntity<List<Order>> getAll()
    {
        List<Order> orders = orderService.findAll();
        System.out.println(orders.size());
        return orders == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(orders);
    }


}
