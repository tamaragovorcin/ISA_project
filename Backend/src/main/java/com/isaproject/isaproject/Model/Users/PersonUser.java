package com.isaproject.isaproject.Model.Users;

import static javax.persistence.DiscriminatorType.STRING;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

import javax.persistence.*;

@Entity
@Table(name="person_user")
// ovom anotacijom se naglasava tip mapiranja "jedna tabela po hijerarhiji"
@Inheritance(strategy=SINGLE_TABLE)
// ovom anotacijom se navodi diskriminatorska kolona
@DiscriminatorColumn(name="type", discriminatorType=STRING)

public class PersonUser {
    @Id
    @SequenceGenerator(name = "mySeqGenV2", sequenceName = "mySeqV2", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenV2")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "firstname", nullable = true)
    private String name;

    @Column(name = "surname", nullable = true)
    private String surname;

    @Column(name = "email", unique = true, nullable = true)
    private String email;

    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "phoneNumber", nullable = true)
    private String phoneNumber;

    @Column(name = "firstLogged", nullable = true)
    private Boolean firstLogged;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false, unique = false)
    private Address address;

    public PersonUser(Integer id, String name, String surname, String email, String password, String phoneNumber, Boolean firstLogged) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.firstLogged = firstLogged;

    }

    public PersonUser() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getFirstLogged() {
        return firstLogged;
    }

    public void setFirstLogged(Boolean firstLogged) {
        this.firstLogged = firstLogged;
    }
}

