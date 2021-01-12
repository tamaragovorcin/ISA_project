package com.isaproject.isaproject.Model.Pharmacy;

import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.Users.Address;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="pharmacy_table")
public class Pharmacy {

    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "pharmacyName", nullable = true)
    private String pharmacyName;


    @Column(name = "mark", nullable = true)
    private double mark;

    @Column(name = "consultingPrice", nullable = true)
    private double consultingPrice;

    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ExaminationSchedule> examinationSchedules = new HashSet<ExaminationSchedule>();


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false, unique = false)
    private Address address;

    public Pharmacy() {
    }


    public Set<ExaminationSchedule> getExaminationSchedules() {
        return examinationSchedules;
    }

    public void setExaminationSchedules(Set<ExaminationSchedule> examinationSchedules) {
        this.examinationSchedules = examinationSchedules;
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

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }


    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public double getConsultingPrice() {
        return consultingPrice;
    }

    public void setConsultingPrice(double consultingPrice) {
        this.consultingPrice = consultingPrice;
    }
}
