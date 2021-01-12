package com.isaproject.isaproject.Model.Medicine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Medication  {
    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "code", nullable = false)
    private long code;


    @Column(name = "form", nullable = false)
    private String form;


    @Column(name = "type", nullable = false)
    private String type;


    @Column(name = "issuanceRegime", nullable = false)
    private String issuanceRegime;//rezim upotrebe


    @Column(name = "mark", nullable = false)
    private double mark;


    @Column(name = "loyaltyPoints", nullable = false)
    private double loyaltyPoints;




    public Medication() {
    }

    public Medication(Integer id, String name, long code, String form, String type, String issuanceRegime, double mark, double loyaltyPoints) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.form = form;
        this.type = type;
        this.issuanceRegime = issuanceRegime;
        this.mark = mark;
        this.loyaltyPoints = loyaltyPoints;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
