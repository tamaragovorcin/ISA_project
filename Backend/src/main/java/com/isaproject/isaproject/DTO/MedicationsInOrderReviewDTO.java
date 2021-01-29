package com.isaproject.isaproject.DTO;


public class MedicationsInOrderReviewDTO {
    private int id;
    private String name;
    private long code;
    private String form;
    private String type;
    private int quantity;

    public MedicationsInOrderReviewDTO() {
    }

    public MedicationsInOrderReviewDTO(int id, String name, long code, String form, String type, int quantity) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.form = form;
        this.type = type;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
