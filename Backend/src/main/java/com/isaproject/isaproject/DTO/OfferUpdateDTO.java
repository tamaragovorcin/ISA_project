package com.isaproject.isaproject.DTO;

import java.time.LocalDate;

public class OfferUpdateDTO {
    private Integer offerId;
    private LocalDate dateOfDelivery;
    private double summaryPrice;

    public OfferUpdateDTO() {
    }

    public OfferUpdateDTO(Integer offerId, LocalDate dateOfDelivery, double summaryPrice) {
        this.offerId = offerId;
        this.dateOfDelivery = dateOfDelivery;
        this.summaryPrice = summaryPrice;
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
