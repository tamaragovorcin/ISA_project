package com.isaproject.isaproject.DTO;

public class PharmacyDTO {

    private String pharmacyName;

    private AddressDTO address;
    private double consultingPrice;
    private String description;

    public PharmacyDTO(String pharmacyName, AddressDTO address) {
        this.pharmacyName = pharmacyName;
        this.address = address;
    }

    public PharmacyDTO() {}

    public PharmacyDTO(String pharmacyName, AddressDTO address, double consultingPrice, String description) {
        this.pharmacyName = pharmacyName;
        this.address = address;
        this.consultingPrice = consultingPrice;
        this.description = description;
    }
    public PharmacyDTO(String pharmacyName, AddressDTO address, double consultingPrice) {
        this.pharmacyName = pharmacyName;
        this.address = address;
        this.consultingPrice = consultingPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getConsultingPrice() {
        return consultingPrice;
    }

    public void setConsultingPrice(double consultingPrice) {
        this.consultingPrice = consultingPrice;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
