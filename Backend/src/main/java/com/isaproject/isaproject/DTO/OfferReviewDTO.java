package com.isaproject.isaproject.DTO;
import java.time.LocalDate;

public class OfferReviewDTO {
    private String supplierName;
    private String supplierSurName;
    private Integer offerId;
    private LocalDate dateOfDelivery;
    private double summaryPrice;

    public OfferReviewDTO(String supplierName, String supplierSurName, Integer offerId, LocalDate dateOfDelivery, double summaryPrice) {
        this.supplierName = supplierName;
        this.supplierSurName = supplierSurName;
        this.offerId = offerId;
        this.dateOfDelivery = dateOfDelivery;
        this.summaryPrice = summaryPrice;
    }

    public OfferReviewDTO() {}

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierSurName() {
        return supplierSurName;
    }

    public void setSupplierSurName(String supplierSurName) {
        this.supplierSurName = supplierSurName;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
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
}
