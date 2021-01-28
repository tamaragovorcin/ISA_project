package com.isaproject.isaproject.Model.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isaproject.isaproject.Model.Orders.Offer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//ovom anotacijom se navodi vrednost diskriminatorske kolone koja vazi za
//objekte ove klase

@Entity
@DiscriminatorValue("Supplier")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Supplier extends PersonUser{

    @JsonManagedReference(value="supplier-createsOffer")
    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Offer> offer = new HashSet<Offer>();

    public Supplier() {}

    public Set<Offer> getOffer() {
        return offer;
    }

    public void setOffer(Set<Offer> offer) {
        this.offer = offer;
    }
}
