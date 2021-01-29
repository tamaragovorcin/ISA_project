package com.isaproject.isaproject.Model.HelpModel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Orders.Order;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
public class MedicationPrice {


    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @JsonBackReference(value = "medication-medicationPrice")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "medication_id", referencedColumnName = "id", nullable = true, unique = false)
    private Medication medication;


    @Column(name = "price", nullable = true)
    private double price;


    @Column(name = "date", nullable = true)
    private LocalDate date;


    @JsonBackReference(value="pharmacy-medicationPrice")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id", referencedColumnName = "id", nullable = true, unique = false)
    private Pharmacy pharmacy;

    public MedicationPrice() {
    }

    public MedicationPrice(Integer id, Medication medication, double price, LocalDate date, Pharmacy pharmacy) {
        this.id = id;
        this.medication = medication;
        this.price = price;
        this.date = date;
        this.pharmacy = pharmacy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}
