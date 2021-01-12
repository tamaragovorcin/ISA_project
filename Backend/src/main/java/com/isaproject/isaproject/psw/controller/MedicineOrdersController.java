package com.isaproject.isaproject.psw.controller;

import com.isaproject.isaproject.psw.model.UrgentOrder;
import com.isaproject.isaproject.psw.service.IUrgentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MedicineOrdersController {
    @Autowired
    private IUrgentOrderService serviceUrgentOrders;

    @GetMapping("/order/urgent")
    List<UrgentOrder> getAllUrgentOrders()
    {
        return serviceUrgentOrders.getAll();
    }

    @PostMapping("/order/urgent/http")
    public ResponseEntity<String> urgentOrderHttp(@RequestBody String medicine) {
        System.out.println("POGODIO METODU          " + medicine);
        serviceUrgentOrders.SaveUrgentOrder(medicine);

        return null;
    }

    @GetMapping("/order/{api}")
    List<UrgentOrder>  getOrderByAPIKey(@PathVariable String api)
    {
        System.out.println("POGODIO METODU ZA NABAVKE         ");

        return serviceUrgentOrders.getAllByPharmacyApi(api);
    }

    @RequestMapping(value = "/tender", method = RequestMethod.POST)
    public ResponseEntity<String> tenderOrderHttp(@RequestBody String tender){
        System.out.println("POGODIO METODU ZA TENDER          ");

        return null;
    }




}
