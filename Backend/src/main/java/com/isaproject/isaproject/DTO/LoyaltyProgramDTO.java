package com.isaproject.isaproject.DTO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class LoyaltyProgramDTO {

    private double consultingPoints;
    private double examinationPoints;
    private double regularLimit;
    private double silverLimit;
    private double goldLimit;
    private double regularDiscount;
    private double silverDiscount;
    private double goldenDiscount;

    public LoyaltyProgramDTO() {
    }

    public LoyaltyProgramDTO(double consultingPoints, double examinationPoints, double regularLimit, double silverLimit, double goldLimit, double regularDiscount, double silverDiscount, double goldenDiscount) {
        this.consultingPoints = consultingPoints;
        this.examinationPoints = examinationPoints;
        this.regularLimit = regularLimit;
        this.silverLimit = silverLimit;
        this.goldLimit = goldLimit;
        this.regularDiscount = regularDiscount;
        this.silverDiscount = silverDiscount;
        this.goldenDiscount = goldenDiscount;
    }

    public double getConsultingPoints() {
        return consultingPoints;
    }

    public void setConsultingPoints(double consultingPoints) {
        this.consultingPoints = consultingPoints;
    }

    public double getExaminationPoints() {
        return examinationPoints;
    }

    public void setExaminationPoints(double examinationPoints) {
        this.examinationPoints = examinationPoints;
    }

    public double getRegularLimit() {
        return regularLimit;
    }

    public void setRegularLimit(double regularLimit) {
        this.regularLimit = regularLimit;
    }

    public double getSilverLimit() {
        return silverLimit;
    }

    public void setSilverLimit(double silverLimit) {
        this.silverLimit = silverLimit;
    }

    public double getGoldLimit() {
        return goldLimit;
    }

    public void setGoldLimit(double goldLimit) {
        this.goldLimit = goldLimit;
    }

    public double getRegularDiscount() {
        return regularDiscount;
    }

    public void setRegularDiscount(double regularDiscount) {
        this.regularDiscount = regularDiscount;
    }

    public double getSilverDiscount() {
        return silverDiscount;
    }

    public void setSilverDiscount(double silverDiscount) {
        this.silverDiscount = silverDiscount;
    }

    public double getGoldenDiscount() {
        return goldenDiscount;
    }

    public void setGoldenDiscount(double goldenDiscount) {
        this.goldenDiscount = goldenDiscount;
    }
}
