package com.isaproject.isaproject.DTO;

import java.time.LocalDate;
import java.util.List;

public class SupplierOffersInfoDTO {
    private Integer offerId;
    private Integer orderId;
    private LocalDate dateOfDelivery;
    private double summaryPrice;
    private LocalDate date;
    private List<MedicationsInOrderReviewDTO> medications;
    private String pharmacyName;
    private boolean changable;

    public SupplierOffersInfoDTO() {
    }

    public SupplierOffersInfoDTO(Integer offerId, Integer orderId, LocalDate dateOfDelivery, double summaryPrice, LocalDate date, List<MedicationsInOrderReviewDTO> medications, String pharmacyName, boolean changable) {
        this.offerId = offerId;
        this.orderId = orderId;
        this.dateOfDelivery = dateOfDelivery;
        this.summaryPrice = summaryPrice;
        this.date = date;
        this.medications = medications;
        this.pharmacyName = pharmacyName;
        this.changable = changable;
    }

    public boolean isChangable() {
        return changable;
    }

    public void setChangable(boolean changable) {
        this.changable = changable;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(LocalDate dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public double getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(double summaryPrice) {
        this.summaryPrice = summaryPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<MedicationsInOrderReviewDTO> getMedications() {
        return medications;
    }

    public void setMedications(List<MedicationsInOrderReviewDTO> medications) {
        this.medications = medications;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }
}
