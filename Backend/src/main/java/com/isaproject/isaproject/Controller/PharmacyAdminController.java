package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.HelpModel.MedicationPrice;
import com.isaproject.isaproject.Model.Orders.MedicationInOrder;
import com.isaproject.isaproject.Model.Orders.Order;
import com.isaproject.isaproject.Model.Pharmacy.Actions;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.*;
import com.isaproject.isaproject.Service.Implementations.MedicationPriceService;
import com.isaproject.isaproject.Service.Implementations.OrderService;
import com.isaproject.isaproject.Service.Implementations.PharmacyAdminService;
import com.isaproject.isaproject.Service.Implementations.SupplierService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/pharmacyAdmin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PharmacyAdminController {
    @Autowired
    PharmacyAdminService pharmacyAdminService;
    @Autowired
    MedicationPriceService medicationPriceService;
    @Autowired
    OrderService orderService;

    @PostMapping("/register")
   // @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<String> addUser(@RequestBody PharmacyAdminDTO userRequest) {
        System.out.println(userRequest.getPharmacy().getPharmacyName());

        PersonUser existUser = pharmacyAdminService.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getEmail(), "Email already exists");
        }
        PersonUser user = pharmacyAdminService.save(userRequest);
        return new ResponseEntity<>("Supplier is successfully registred!", HttpStatus.CREATED);
    }

    @GetMapping("/account")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<PharmacyAdmin> getMyAccount()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        return pharmacyAdmin == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyAdmin);
    }
    @PostMapping("/update")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<PharmacyAdmin> update(@RequestBody PharmacyAdminDTO person)
    {
        PharmacyAdmin per = pharmacyAdminService.findByEmail(person.getEmail());
        Integer id = per.getId();
        pharmacyAdminService.delete(per);
        PharmacyAdmin patient = pharmacyAdminService.save(person);
        return patient == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patient);
    }
    @GetMapping("/myPharmacy")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<Pharmacy> getMyPharmacy()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        return pharmacyAdmin.getPharmacy() == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyAdmin.getPharmacy());
    }
    @GetMapping("/dermatologists")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<Set<Dermatologist>> getOurDermatologists()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        return pharmacyAdmin.getPharmacy().getDermatologists() == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyAdmin.getPharmacy().getDermatologists());
    }
    @GetMapping("/actions")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<Set<Actions>> getActions()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        return pharmacyAdmin.getPharmacy().getActions() == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyAdmin.getPharmacy().getActions());
    }
    @GetMapping("/pharmacists")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<Set<Pharmacist>> getPharmacists()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        return pharmacyAdmin.getPharmacy().getPharmacists() == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyAdmin.getPharmacy().getPharmacists());
    }
    @GetMapping("/medication")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<List<MedicationPrice>> getMedication()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();

        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());

        return medicationPriceService.findByPharmacy(pharmacyAdmin.getPharmacy().getId()) == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medicationPriceService.findByPharmacy(pharmacyAdmin.getPharmacy().getId()));
    }
    /*@GetMapping("/orders")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<List<Order>> getOrders()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();

        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        List<Order> orders  = new ArrayList<Order>();
        for(Order or : orderService.findAll()){
            if(or.getPharmacyAdmin().getPharmacy().getId() == pharmacyAdmin.getPharmacy().getId()){
                orders.add(or);
            }
        }
        return orders == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(orders);
    }
    @GetMapping("/medicationInOrder")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<List<MedicationsInOrderReviewDTO>> getMedicationInOrder()
    {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("------------------------------------------------------------------");
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        System.out.println("-----------------------------------------------------------------------");
        List<MedicationsInOrderReviewDTO> orders = new ArrayList<MedicationsInOrderReviewDTO>();
        for(Order order : orderService.findAll()){
            for(MedicationInOrder medicationInOrder : order.getMedicationInOrders())
            if(order.getPharmacyAdmin().getPharmacy().getId() == pharmacyAdmin.getPharmacy().getId()){
                MedicationsInOrderReviewDTO medicationsInOrderDTO = new MedicationsInOrderReviewDTO();
                medicationsInOrderDTO.setName(medicationInOrder.getMedicine().getName());
                medicationsInOrderDTO.setCode(medicationInOrder.getMedicine().getCode());
                medicationsInOrderDTO.setForm(medicationInOrder.getMedicine().getForm());
                medicationsInOrderDTO.setManufacturer(medicationInOrder.getMedicine().getSpecification().getManufacturer());
                medicationsInOrderDTO.setQuantity(medicationInOrder.getQuantity());
                medicationsInOrderDTO.setDate(order.getDate());
                medicationsInOrderDTO.setQuantity(medicationInOrder.getQuantity());
                medicationsInOrderDTO.setOrderId(medicationInOrder.getOrder().getId());
                orders.add(medicationsInOrderDTO);
                System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");

                System.out.println(order.getPharmacyAdmin().getName());
            }
        }
        return orders == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(orders);
    }*/
    @GetMapping("orders")
    ResponseEntity<List<OrderReviewDTO>> getAllOrders()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        List<Order> orders = orderService.findAll();
        List<OrderReviewDTO> ordersDto = new ArrayList<>();
        for (Order order: orders) {
            if((order.getPharmacyAdmin().getPharmacy().getId() == pharmacyAdmin.getPharmacy().getId())&& !order.getStatus().equals("CLOSED")) {
                ordersDto.add(new OrderReviewDTO(order.getId(), order.getDate(), order.getStatus(), getMedicationsInOrder(order.getMedicationInOrders()),
                        order.getPharmacyAdmin().getPharmacy().getPharmacyName()));
            }
        }
        return ordersDto == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(ordersDto);
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