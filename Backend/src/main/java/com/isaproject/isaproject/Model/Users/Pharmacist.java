package com.isaproject.isaproject.Model.Users;
import com.isaproject.isaproject.Model.Examinations.Consulting;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//ovom anotacijom se navodi vrednost diskriminatorske kolone koja vazi za
//objekte ove klase

@Entity
@DiscriminatorValue("pharmacist")
public class Pharmacist extends  PersonUser implements Serializable {

    @OneToMany(mappedBy = "pharmacist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Consulting> consulting = new HashSet<Consulting>();


    @Column(name = "markPharmacist", nullable = true)
    private double markPharmacist;

    public Set<Consulting> getConsulting() {
        return consulting;
    }

    public void setConsulting(Set<Consulting> consulting) {
        this.consulting = consulting;
    }


    public Pharmacist(Set<Consulting> consulting,  double markPharmacist) {
        this.consulting = consulting;
        this.markPharmacist = markPharmacist;
    }

    public Pharmacist() {
    }

    public double getMarkPharmacist() {
        return markPharmacist;
    }

    public void setMarkPharmacist(double mark) {
        this.markPharmacist = mark;
    }
}

