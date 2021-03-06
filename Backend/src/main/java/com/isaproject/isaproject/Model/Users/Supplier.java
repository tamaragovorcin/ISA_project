package com.isaproject.isaproject.Model.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isaproject.isaproject.Model.Medicine.SupplierMedications;
import com.isaproject.isaproject.Model.Orders.MedicationInOrder;
import com.isaproject.isaproject.Model.Orders.Offer;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("Supplier")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Supplier extends PersonUser{

    @JsonManagedReference(value="supplier-createsOffer")
    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Offer> offer = new HashSet<Offer>();


    @JsonManagedReference(value="medications-supplier")
    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<SupplierMedications> supplierMedications= new HashSet<SupplierMedications>();

    public Supplier() {}

    public Supplier(Integer id, String name, String surname, String email, String password, String phoneNumber, Boolean firstLogged, boolean enabled,
                    Timestamp lastPasswordResetDate, List<Authority> authorities, Address address, Set<Offer> offer, Set<SupplierMedications> supplierMedications) {
        super(id, name, surname, email, password, phoneNumber, firstLogged, enabled, lastPasswordResetDate, authorities, address);
        this.offer = offer;
        this.supplierMedications = supplierMedications;
    }

    public Set<Offer> getOffer() {
        return offer;
    }

    public void setOffer(Set<Offer> offer) {
        this.offer = offer;
    }

    public Set<SupplierMedications> getSupplierMedications() {
        return supplierMedications;
    }

    public void setSupplierMedications(Set<SupplierMedications> supplierMedications) {
        this.supplierMedications = supplierMedications;
    }
}
