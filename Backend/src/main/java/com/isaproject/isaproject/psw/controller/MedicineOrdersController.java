package com.isaproject.isaproject.psw.controller;

import com.isaproject.isaproject.psw.model.UrgentOrder;
import com.isaproject.isaproject.psw.service.IUrgentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MedicineOrdersController {
    @Autowired
    private IUrgentOrderService serviceUrgentOrders;

    @CrossOrigin(origins = "*", allowedHeaders = "*")

    @GetMapping("/order/urgent")
    public List<UrgentOrder> getAllUrgentOrders()
    {
        System.out.println("POGODIO GETALL");
        return serviceUrgentOrders.getAll();
    }

    @PostMapping("/order/urgent/http")
    public ResponseEntity<String> urgentOrderHttp(@RequestBody String medicine) {
        System.out.println("POGODIO METODU          " + medicine);
        serviceUrgentOrders.SaveUrgentOrder(medicine);

        return null;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/order/{api}")
    public List<UrgentOrder>  getOrderByAPIKey(@PathVariable String api)
    {
        System.out.println("Metoda za dobavljanje nabavki");

        return serviceUrgentOrders.getAllByPharmacyApi(api);
    }


    @RequestMapping(value = "order/tender", method = RequestMethod.POST)
    public ResponseEntity<String> tenderOrderHttp(@RequestBody String tender){
        System.out.println("POGODIO METODU ZA TENDER          ");

        return null;
    }




}
