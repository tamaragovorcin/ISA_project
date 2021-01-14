package com.isaproject.isaproject.Model.Users;

import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Address {

    @Id
    @SequenceGenerator(name = "mySeqGenV2", sequenceName = "mySeqV2", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenV2")
    @Column(name = "address_id", nullable = false)
    private Integer id;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PersonUser> personUsers = new HashSet<PersonUser>();


    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Pharmacy> pharmacies = new HashSet<Pharmacy>();

    @Column(name = "town", nullable = true)
    private String town;

    @Column(name = "street", nullable = true)
    private String street;

    @Column(name = "number", nullable = true)
    private int number;

    @Column(name = "postalCode", nullable = true)
    private int postalCode;

    @Column(name = "country", nullable = true)
    private String country;

    public Address(Integer id, Set<PersonUser> personUsers, String town, String street, int number, int postalCode, String country) {
        this.id = id;
        this.personUsers = personUsers;
        this.town = town;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Address(String town, String street, int number, int postalCode, String country) {

        this.town = town;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Address() {}

    public Set<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(Set<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<PersonUser> getPersonUsers() {
        return personUsers;
    }

    public void setPersonUsers(Set<PersonUser> personUsers) {
        this.personUsers = personUsers;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
