package com.isaproject.isaproject.Model.Orders;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isaproject.isaproject.Model.Examinations.EPrescription;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.PharmacyAdmin;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order  {

    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;


    @JsonBackReference(value = "pharmacyAdmin-order")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacyAdmin_id", referencedColumnName = "id", nullable = true, unique = false)
    private PharmacyAdmin pharmacyAdmin;


    @JsonManagedReference(value="order-medicationInOrder")
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicationInOrder> medicationInOrders = new HashSet<MedicationInOrder>();

    @Column(name = "date", nullable = true)
    private LocalDate date;


    @Column(name = "status", nullable = true)
    private String status;


    @JsonManagedReference(value="offer-offerForOrder")
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Offer> offer = new HashSet<Offer>();

    public Order() {
    }

    public Order(Integer id, PharmacyAdmin pharmacyAdmin, Set<MedicationInOrder> medicationInOrders, LocalDate date, String status, Set<Offer> offer) {
        this.id = id;
        this.pharmacyAdmin = pharmacyAdmin;
        this.medicationInOrders = medicationInOrders;
        this.date = date;
        this.status = status;
        this.offer = offer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public PharmacyAdmin getPharmacyAdmin() {
        return pharmacyAdmin;
    }

    public void setPharmacyAdmin(PharmacyAdmin pharmacyAdmin) {
        this.pharmacyAdmin = pharmacyAdmin;
    }

    public Set<MedicationInOrder> getMedicationInOrders() {
        return medicationInOrders;
    }

    public void setMedicationInOrders(Set<MedicationInOrder> medicationInOrders) {
        this.medicationInOrders = medicationInOrders;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Offer> getOffer() {
        return offer;
    }

    public void setOffer(Set<Offer> offer) {
        this.offer = offer;
    }
}