package com.isaproject.isaproject.DTO;

public class SupplierMedicationReviewDTO {
    private Integer id;
    private int quantity;
    private int reservedQuantity;
    private String name;
    private long code;
    private String form;
    private String type;

    public SupplierMedicationReviewDTO() {
    }

    public SupplierMedicationReviewDTO(Integer id, int quantity, int reservedQuantity, String name, long code, String form, String type) {
        this.id = id;
        this.quantity = quantity;
        this.reservedQuantity = reservedQuantity;
        this.name = name;
        this.code = code;
        this.form = form;
        this.type = type;
    }

    public int getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(int reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
