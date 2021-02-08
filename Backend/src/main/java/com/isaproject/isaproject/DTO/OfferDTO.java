package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Users.Supplier;
import java.time.LocalDate;

public class OfferDTO {
    private Integer supplier;
    private Integer orderId;
    private LocalDate dateOfDelivery;
    private double summaryPrice;

    public OfferDTO() {}

    public OfferDTO(Integer supplier, Integer orderId, LocalDate dateOfDelivery, double summaryPrice) {
        this.supplier = supplier;
        this.orderId = orderId;
        this.dateOfDelivery = dateOfDelivery;
        this.summaryPrice = summaryPrice;
    }

    public Integer getSupplier() {
        return supplier;
    }

    public void setSupplier(Integer supplier) {
        this.supplier = supplier;
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
}
