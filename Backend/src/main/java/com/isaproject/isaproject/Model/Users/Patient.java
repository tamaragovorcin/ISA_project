package com.isaproject.isaproject.Model.Users;


import com.fasterxml.jackson.annotation.*;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Examinations.EPrescription;
import com.isaproject.isaproject.Model.Examinations.Examination;
import com.isaproject.isaproject.Model.Examinations.Prescription;
import com.isaproject.isaproject.Model.HelpModel.*;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("Patient")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Patient extends PersonUser{

   /* @ManyToMany
    @JoinTable(name = "consulting", joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "consulting_id", referencedColumnName = "id"))
    private Set<Consulting> consultings = new HashSet<Consulting>();*/

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Consulting> consulting = new HashSet<Consulting>();

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Examination> examinations = new HashSet<Examination>();

    @JsonManagedReference
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Complaint> complaints = new HashSet<Complaint>();

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Grading> gradings = new HashSet<Grading>();

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EPrescription> ePrescriptions = new HashSet<EPrescription>();

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Prescription> prescriptions = new HashSet<Prescription>();



    @ManyToMany
    @JoinTable(name = "patients_subscriptions", joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"))
    private Set<Pharmacy> subscribedToPharmacies = new HashSet<Pharmacy>();

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PatientsMedicationAlergy> patientsMedicationAlergy = new HashSet<PatientsMedicationAlergy>();

    @JsonManagedReference(value="patient-medication")
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicationReservation> medicationReservations = new HashSet<MedicationReservation>();

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

    public Patient() {super();}



    public Patient(Integer id, String name, String surname, String email, String password, String phoneNumber, Boolean firstLogged, boolean enabled, Timestamp lastPasswordResetDate, List<Authority> authorities, Address address, Set<Consulting> consulting, Set<Examination> examinations, Set<Complaint> complaints, Set<Grading> gradings, Set<EPrescription> ePrescriptions, Set<Prescription> prescriptions, Set<PatientsMedicationAlergy> patientsMedicationAlergy, Set<MedicationReservation> medicationReservations, int penalties, int points, String loyaltyCategory, double discount) {
        super(id, name, surname, email, password, phoneNumber, firstLogged, enabled, lastPasswordResetDate, authorities, address);
        this.consulting = consulting;
        this.examinations = examinations;
        this.complaints = complaints;
        this.gradings = gradings;
        this.ePrescriptions = ePrescriptions;
        this.prescriptions = prescriptions;
        this.patientsMedicationAlergy = patientsMedicationAlergy;
        this.medicationReservations = medicationReservations;
        this.penalties = penalties;
        this.points = points;
        this.loyaltyCategory = loyaltyCategory;
        this.discount = discount;
    }

    public Set<MedicationReservation> getMedicationReservations() {
        return medicationReservations;
    }

    public void setMedicationReservations(Set<MedicationReservation> medicationReservations) {
        this.medicationReservations = medicationReservations;
    }

    public Set<PatientsMedicationAlergy> getPatientsMedicationAlergy() {
        return patientsMedicationAlergy;
    }

    public void setPatientsMedicationAlergy(Set<PatientsMedicationAlergy> patientsMedicationAlergy) {
        this.patientsMedicationAlergy = patientsMedicationAlergy;
    }

    public Set<Pharmacy> getSubscribedToPharmacies() {
        return subscribedToPharmacies;
    }

    public void setSubscribedToPharmacies(Set<Pharmacy> subscribedToPharmacies) {
        this.subscribedToPharmacies = subscribedToPharmacies;
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
