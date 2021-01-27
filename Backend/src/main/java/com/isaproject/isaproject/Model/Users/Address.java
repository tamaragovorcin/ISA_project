package com.isaproject.isaproject.Model.Users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address implements Serializable {

    @Id
    @SequenceGenerator(name = "mySeqGenV2", sequenceName = "mySeqV2", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenV2")
    @Column(name = "address_id", nullable = false)
    private Integer id;

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

    public Address(Integer id,  String town, String street, int number, int postalCode, String country) {
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
