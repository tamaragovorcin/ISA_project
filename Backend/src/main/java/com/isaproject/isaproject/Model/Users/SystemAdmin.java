package com.isaproject.isaproject.Model.Users;
import com.isaproject.isaproject.Model.Orders.Order;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@DiscriminatorValue("SystemAdmin")
public class SystemAdmin extends PersonUser{

    private Boolean mainAdmin;

    public SystemAdmin() {
    }

    public SystemAdmin(Integer id, String name, String surname, String email, String password, String phoneNumber, Boolean firstLogged, boolean enabled, Timestamp lastPasswordResetDate, List<Authority> authorities, Address address, Boolean mainAdmin) {
        super(id, name, surname, email, password, phoneNumber, firstLogged, enabled, lastPasswordResetDate, authorities, address);
        this.mainAdmin = mainAdmin;
    }

    public Boolean getMainAdmin() {
        return mainAdmin;
    }

    public void setMainAdmin(Boolean mainAdmin) {
        this.mainAdmin = mainAdmin;
    }
}
