package com.isaproject.isaproject.Model.Users;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//ovom anotacijom se navodi vrednost diskriminatorske kolone koja vazi za
//objekte ove klase

@Entity
@DiscriminatorValue("PharmacyAdmin")
public class PharmacyAdmin extends PersonUser{
    @Column(name = "pharmacyId", nullable = true)
    private int pharmacyId;


    public PharmacyAdmin() {
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }
}
