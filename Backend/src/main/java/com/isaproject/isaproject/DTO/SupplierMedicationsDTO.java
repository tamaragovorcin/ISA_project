package com.isaproject.isaproject.DTO;

public class SupplierMedicationsDTO {
    private int quantity;
    private String name;
    private long code;

    public SupplierMedicationsDTO() { }

    public SupplierMedicationsDTO(int quantity, String name, long code) {
        this.quantity = quantity;
        this.name = name;
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }
}