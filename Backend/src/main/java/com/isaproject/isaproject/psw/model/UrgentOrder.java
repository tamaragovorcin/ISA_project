package com.isaproject.isaproject.psw.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="urgentOrders")
public class UrgentOrder {

    @Id
    private int id;
    private String name;
    private int quantity;
    private String pharmacyApi;
    private String dateOrder;

    public UrgentOrder() {
        super();
    }
    public UrgentOrder(int id, String name, int quantity, String pharmacyApi, String date) {
        super();
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.pharmacyApi = pharmacyApi;
        this.dateOrder = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPharmacyApi() {
        return pharmacyApi;
    }

    public void setPharmacyApi(String pharmacyApi) {
        this.pharmacyApi = pharmacyApi;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }
}