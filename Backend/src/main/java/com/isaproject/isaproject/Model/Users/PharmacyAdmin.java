package com.isaproject.isaproject.Model.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isaproject.isaproject.Model.Orders.Order;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("PharmacyAdmin")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PharmacyAdmin extends PersonUser{

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_PharmacyAdmin", referencedColumnName = "id", nullable = true, unique = false)
    private Pharmacy pharmacy;

    @JsonManagedReference(value="pharmacyAdmin-order")
    @OneToMany(mappedBy = "pharmacyAdmin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Order> order = new HashSet<Order>();

    public PharmacyAdmin() {}

    public Set<Order> getOrders() {
        return order;
    }

    public void setOrders(Set<Order> orders) {
        this.order = orders;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}