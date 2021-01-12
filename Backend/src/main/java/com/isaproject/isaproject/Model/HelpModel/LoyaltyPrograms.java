package com.isaproject.isaproject.Model.HelpModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LoyaltyPrograms {


    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "consultingPoints", nullable = true)
    private double consultingPoints;


    @Column(name = "examinationPoints", nullable = true)
    private double examinationPoints;


    @Column(name = "regularLimit", nullable = true)
    private double regularLimit;


    @Column(name = "silverLimit", nullable = true)
    private double silverLimit;


    @Column(name = "goldLimit", nullable = true)
    private double goldLimit;


    @Column(name = "regularDiscount", nullable = true)
    private double regularDiscount;


    @Column(name = "silverDiscount", nullable = true)
    private double silverDiscount;


    @Column(name = "goldenDiscount", nullable = true)
    private double goldenDiscount;

    public LoyaltyPrograms() {
    }

    public LoyaltyPrograms(Integer id, double consultingPoints, double examinationPoints, double regularLimit, double silverLimit, double goldLimit, double regularDiscount, double silverDiscount, double goldenDiscount) {
        this.id = id;
        this.consultingPoints = consultingPoints;
        this.examinationPoints = examinationPoints;
        this.regularLimit = regularLimit;
        this.silverLimit = silverLimit;
        this.goldLimit = goldLimit;
        this.regularDiscount = regularDiscount;
        this.silverDiscount = silverDiscount;
        this.goldenDiscount = goldenDiscount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
