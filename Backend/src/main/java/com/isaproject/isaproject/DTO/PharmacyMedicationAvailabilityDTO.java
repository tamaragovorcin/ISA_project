package com.isaproject.isaproject.DTO;

public class PharmacyMedicationAvailabilityDTO {
    private Integer pharmacyId;
    private double sumPrice;
    private double mark;
    private AddressDTO address;
    private String pharmacyName;

    public PharmacyMedicationAvailabilityDTO() {
    }

    public PharmacyMedicationAvailabilityDTO(Integer pharmacyId, double sumPrice, double mark, AddressDTO address, String pharmacyName) {
        this.pharmacyId = pharmacyId;
        this.sumPrice = sumPrice;
        this.mark = mark;
        this.address = address;
        this.pharmacyName = pharmacyName;
    }

    public Integer getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Integer pharmacyId) {
        pharmacyId = pharmacyId;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }
}
