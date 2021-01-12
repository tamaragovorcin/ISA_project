package com.isaproject.isaproject.Model.Users;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//ovom anotacijom se navodi vrednost diskriminatorske kolone koja vazi za
//objekte ove klase

@Entity
@DiscriminatorValue("SystemAdmin")
public class SystemAdmin extends PersonUser{



    public SystemAdmin() {
    }
}
