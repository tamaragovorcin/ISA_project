package com.isaproject.isaproject.psw.controller;

import com.isaproject.isaproject.psw.model.Medicine;
import com.isaproject.isaproject.psw.model.Pharmacy;
import com.isaproject.isaproject.psw.model.UrgentOrder;
import com.isaproject.isaproject.psw.service.IMedicinesPharmacyService;
import com.isaproject.isaproject.psw.service.IPharmacyService;
import com.isaproject.isaproject.psw.service.IUrgentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class MedicineOrdersController {
    @Autowired
    private IUrgentOrderService serviceUrgentOrders;

    @CrossOrigin(origins = "*", allowedHeaders = "*")


    @GetMapping("/urgent") List<UrgentOrder> getAllUrgentOrders()
    {
        return serviceUrgentOrders.getAll();
    }

    @RequestMapping(value = "/urgent/http", method = RequestMethod.POST)
    public ResponseEntity<String> urgentOrderHttp(@RequestBody String medicine) throws IOException, ServletException {
        System.out.println("POGODIO METODU          " + medicine);
        serviceUrgentOrders.SaveUrgentOrder(medicine);

        return null;
    }

    @GetMapping("/{api}")
    List<UrgentOrder>  getOrderByAPIKey(@PathVariable String api)
    {
        return serviceUrgentOrders.getAllByPharmacyApi(api);
    }


}
