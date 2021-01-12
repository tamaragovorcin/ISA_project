package com.isaproject.isaproject.Model.Users;


import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Examinations.EPrescription;
import com.isaproject.isaproject.Model.Examinations.Examination;
import com.isaproject.isaproject.Model.Examinations.Prescription;
import com.isaproject.isaproject.Model.HelpModel.Complaint;
import com.isaproject.isaproject.Model.HelpModel.Grading;
import com.isaproject.isaproject.Model.HelpModel.PatientsMedicationAlergy;
import com.isaproject.isaproject.Model.HelpModel.Subscription;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("Patient")
public class Patient extends PersonUser implements Serializable {

   /* @ManyToMany
    @JoinTable(name = "consulting", joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "consulting_id", referencedColumnName = "id"))
    private Set<Consulting> consultings = new HashSet<Consulting>();*/

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Consulting> consulting = new HashSet<Consulting>();

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Examination> examinations = new HashSet<Examination>();

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Complaint> complaints = new HashSet<Complaint>();

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Grading> gradings = new HashSet<Grading>();

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EPrescription> ePrescriptions = new HashSet<EPrescription>();

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Prescription> prescriptions = new HashSet<Prescription>();

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Subscription> subscriptions = new HashSet<Subscription>();

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PatientsMedicationAlergy> patientsMedicationAlergy = new HashSet<PatientsMedicationAlergy>();

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

    public Patient() {}

    public Patient(Integer id, String name, String surname, String email, String password, String phoneNumber, Boolean firstLogged, Set<Consulting> consulting, Set<Examination> examinations, Set<Complaint> complaints, int penalties, int points, String loyaltyCategory, double discount) {
        super(id, name, surname, email, password, phoneNumber, firstLogged);
        this.consulting = consulting;
        this.examinations = examinations;
        this.complaints = complaints;
        this.penalties = penalties;
        this.points = points;
        this.loyaltyCategory = loyaltyCategory;
        this.discount = discount;
    }

    public Set<PatientsMedicationAlergy> getPatientsMedicationAlergy() {
        return patientsMedicationAlergy;
    }

    public void setPatientsMedicationAlergy(Set<PatientsMedicationAlergy> patientsMedicationAlergy) {
        this.patientsMedicationAlergy = patientsMedicationAlergy;
    }

    public Set<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public Set<EPrescription> getePrescriptions() {
        return ePrescriptions;
    }

    public void setePrescriptions(Set<EPrescription> ePrescriptions) {
        this.ePrescriptions = ePrescriptions;
    }

    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public Set<Grading> getGradings() {
        return gradings;
    }

    public void setGradings(Set<Grading> gradings) {
        this.gradings = gradings;
    }

    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public Set<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(Set<Complaint> complaints) {
        this.complaints = complaints;
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
