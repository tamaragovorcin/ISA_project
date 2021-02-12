package com.isaproject.isaproject.DTO;

public class PharmacyGeoDTO {
    private String pharmacyName;
    private AddressWithGeoCordsDTO address;
    private double consultingPrice;
    private String description;

    public PharmacyGeoDTO(String pharmacyName, AddressWithGeoCordsDTO address, double consultingPrice, String description) {
        this.pharmacyName = pharmacyName;
        this.address = address;
        this.consultingPrice = consultingPrice;
        this.description = description;
    }

    public PharmacyGeoDTO() {
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public AddressWithGeoCordsDTO getAddress() {
        return address;
    }

    public void setAddress(AddressWithGeoCordsDTO address) {
        this.address = address;
    }

    public double getConsultingPrice() {
        return consultingPrice;
    }

    public void setConsultingPrice(double consultingPrice) {
        this.consultingPrice = consultingPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
