package com.isaproject.isaproject.Model.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// POJO koji implementira Spring Security GrantedAuthority kojim se mogu definisati role u aplikaciji
@Entity
@Table(name="AUTHORITY")
public class Authority implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name="name")
    String name;

    @ManyToMany(mappedBy = "authorities")
    private Set<PersonUser> personUsers = new HashSet<PersonUser>();

    @Override
    public String getAuthority() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Authority() {
    }

    public Authority(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getName() {
        return name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Set<PersonUser> getPersonUsers() {
        return personUsers;
    }

    public void setPersonUsers(Set<PersonUser> personUsers) {
        this.personUsers = personUsers;
    }

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}