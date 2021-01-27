package com.isaproject.isaproject.Model.Orders;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MedicationInOrder {

    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;


    @JsonBackReference(value = "medication-medicationInOrder")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "medication_id", referencedColumnName = "id", nullable = true, unique = false)
    private Medication medication;


    @Column(name = "quantity", nullable = true)
    private int quantity;



    @JsonBackReference(value="order-medicationInOrder")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false, unique = false)
    private Order order;


    public MedicationInOrder() {
    }

    public MedicationInOrder(Integer id, Medication medicine, int quantity, Order order) {
        this.id = id;
        this.medication = medicine;
        this.quantity = quantity;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Medication getMedicine() {
        return medication;
    }

    public void setMedicine(Medication medicine) {
        this.medication = medicine;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
