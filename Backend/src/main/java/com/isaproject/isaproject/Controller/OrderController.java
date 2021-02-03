package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Orders.MedicationInOrder;
import com.isaproject.isaproject.Model.Orders.Offer;
import com.isaproject.isaproject.Model.Orders.Order;
import com.isaproject.isaproject.Service.Implementations.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

        import com.isaproject.isaproject.Model.Users.PersonUser;
        import com.isaproject.isaproject.Model.Users.Supplier;
        import com.isaproject.isaproject.Service.Implementations.SupplierService;

        import org.springframework.security.access.prepost.PreAuthorize;
        import org.springframework.security.core.Authentication;
        import org.springframework.security.core.context.SecurityContextHolder;

        import java.time.LocalDate;

import java.util.Set;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    SupplierService supplierService;


    @PostMapping("/add")
    ResponseEntity<Order> register(@RequestBody OrderDTO orderDTO)
    {
        Order order = orderService.save(orderDTO);
        return order == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(order);
    }
    @PostMapping("/update")
    ResponseEntity<Order> updateOrder(@RequestBody OrderUpdateDTO orderDTO)
    {
        Order order = orderService.update(orderDTO);
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

    @GetMapping("active")
    @PreAuthorize("hasRole('SUPPLIER')")
    ResponseEntity<List<OrderReviewDTO>> getAllActiveForSupplier()
    {
        List<Order> orders = orderService.findAll();
        List<OrderReviewDTO> ordersDto = new ArrayList<>();
        for (Order order: orders) {
            System.out.println("**************************************");
            if(order.getDate().isAfter(LocalDate.now()))
            {
                if(supplierHasMadeOffer(order)) {
                    ordersDto.add(new OrderReviewDTO(order.getId(), order.getDate(), order.getStatus(), getMedicationsInOrder(order.getMedicationInOrders()),
                            order.getPharmacyAdmin().getPharmacy().getPharmacyName()));
                }
            }
        }
        return ordersDto == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(ordersDto);
    }

    private boolean supplierHasMadeOffer(Order order) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        Supplier supplier = supplierService.findById(user.getId());

        Set<Offer> offers = order.getOffer();
        for (Offer offer:offers) {
            Supplier offerSupplier = offer.getSupplier();
            if(offerSupplier.getId()==supplier.getId()) {
                return false;
            }
        }
        return true;
    }

    private List<MedicationsInOrderReviewDTO> getMedicationsInOrder(Set<MedicationInOrder> medicationInOrders) {
        List<MedicationsInOrderReviewDTO> medications = new ArrayList<>();
        for (MedicationInOrder medicationInOrder: medicationInOrders) {
            medications.add(new MedicationsInOrderReviewDTO(medicationInOrder.getId(), medicationInOrder.getMedicine().getName(),
                    medicationInOrder.getMedicine().getCode(),medicationInOrder.getMedicine().getForm(),
                    medicationInOrder.getMedicine().getType(),medicationInOrder.getQuantity()));
        }
        return medications;
    }


}