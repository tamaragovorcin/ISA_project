package com.isaproject.isaproject.Model.Medicine;

import com.isaproject.isaproject.Model.Entity;

public class Medication extends Entity {
    private String name;
    private long code;
    private String form;
    private String type;
    private String issuanceRegime;//rezim upotrebe
    private double mark;
    private double loyaltyPoints;


    public Medication(int id, String name, long code, String form, String type, String issuanceRegime, double mark, double loyaltyPoints) {
        super(id);
        this.name = name;
        this.code = code;
        this.form = form;
        this.type = type;
        this.issuanceRegime = issuanceRegime;
        this.mark = mark;
        this.loyaltyPoints = loyaltyPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
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

    public String getIssuanceRegime() {
        return issuanceRegime;
    }

    public void setIssuanceRegime(String issuanceRegime) {
        this.issuanceRegime = issuanceRegime;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(double loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public Medication() {
        super();
    }


    public Medication(String name, long code, String form, String type, String issuanceRegime, double mark, double loyaltyPoints) {
        this.name = name;
        this.code = code;
        this.form = form;
        this.type = type;
        this.issuanceRegime = issuanceRegime;
        this.mark = mark;
        this.loyaltyPoints = loyaltyPoints;
    }
}
