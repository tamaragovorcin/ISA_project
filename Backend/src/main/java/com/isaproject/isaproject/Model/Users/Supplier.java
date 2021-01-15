package com.isaproject.isaproject.Model.Users;
import com.isaproject.isaproject.Model.Orders.Offer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//ovom anotacijom se navodi vrednost diskriminatorske kolone koja vazi za
//objekte ove klase

@Entity
@DiscriminatorValue("Supplier")
public class Supplier extends PersonUser{

    @ManyToMany
    @JoinTable(name = "offer_table", joinColumns = @JoinColumn(name = "offer_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "supplier_id", referencedColumnName = "id"))
    private Set<Offer> offer = new HashSet<Offer>();

    public Supplier() {}


}
