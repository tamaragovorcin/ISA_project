package com.isaproject.isaproject.DTO;
import java.time.LocalDate;

public class MedicationPriceFrontDTO {
    private String name;
    private Long code;
    private String manufacturer;
    private String form;
    private String type;
    private double price;
    private LocalDate date;

    public MedicationPriceFrontDTO(String name, Long code, String manufacturer, String form, String type, double price, LocalDate date) {
        this.name = name;
        this.code = code;
        this.manufacturer = manufacturer;
        this.form = form;
        this.type = type;
        this.price = price;
        this.date = date;
    }

    public MedicationPriceFrontDTO() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}