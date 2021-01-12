package com.isaproject.isaproject.Model.Users;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;

import javax.persistence.*;

@Entity
@DiscriminatorValue("PharmacyAdmin")
public class PharmacyAdmin extends PersonUser{

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_PharmacyAdmin", referencedColumnName = "id", nullable = true, unique = false)
    private Pharmacy pharmacy;


    public PharmacyAdmin() {}

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}
