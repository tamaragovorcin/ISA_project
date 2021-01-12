package com.isaproject.isaproject.Model.Users;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("SystemAdmin")
public class SystemAdmin extends PersonUser{

    public SystemAdmin() {}
}
