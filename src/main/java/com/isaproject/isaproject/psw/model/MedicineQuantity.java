package com.isaproject.isaproject.psw.model;

public class MedicineQuantity {

    private String name;
    private int quantity;
    private String availableQunatity;


    public MedicineQuantity() {}


    public MedicineQuantity(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.availableQunatity ="";
    }
    public String getAvailableQunatity() {
        return availableQunatity;
    }

    public void setAvailableQunatity(String availableQunatity) {
        this.availableQunatity = availableQunatity;
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
}
