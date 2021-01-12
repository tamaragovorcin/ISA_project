package com.isaproject.isaproject.Model.Users;


import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Examinations.Examination;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("patient")
public class Patient extends PersonUser implements Serializable {

   /* @ManyToMany
    @JoinTable(name = "consulting", joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "consulting_id", referencedColumnName = "id"))
    private Set<Consulting> consultings = new HashSet<Consulting>();*/

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Consulting> consulting = new HashSet<Consulting>();

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Examination> examinations = new HashSet<Examination>();


    @Column(name = "penalties", nullable = true)
    private int penalties;

    @Column(name = "points", nullable = true)
    private int points;

    @Column(name = "loyaltyCategory", nullable = true)
    private String loyaltyCategory;

    @Column(name = "discount", nullable = true)
    private double discount;

    public Set<Consulting> getConsulting() {
        return consulting;
    }

    public void setConsulting(Set<Consulting> consulting) {
        this.consulting = consulting;
    }

    public Patient() {
    }


    public Patient(Set<Consulting> consulting, int penalties, int points, String loyaltyCategory, double discount) {
        this.consulting = consulting;
        this.penalties = penalties;
        this.points = points;
        this.loyaltyCategory = loyaltyCategory;
        this.discount = discount;
    }

    public Set<Examination> getExaminations() {
        return examinations;
    }

    public void setExaminations(Set<Examination> examinations) {
        this.examinations = examinations;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getLoyaltyCategory() {
        return loyaltyCategory;
    }

    public void setLoyaltyCategory(String loyaltyCategory) {
        this.loyaltyCategory = loyaltyCategory;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
