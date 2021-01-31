package com.isaproject.isaproject.Model.Schedule;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.Pharmacist;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HolidaySchedulePharmacist {
    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @JsonBackReference(value="holiday-pharmacist")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacist_id", referencedColumnName = "id", nullable = false, unique = false)
    private Pharmacist pharmacist;



    @Column(name = "startDate", nullable = true)
    private LocalDate startDate;


    @Column(name = "endDate", nullable = true)
    private LocalDate endDate;


    @Column(name = "approved", nullable = true)
    private String approved;//APPROVED or REFUSED or WAITING_FOR_RESPONSE


    @Column(name = "massage", nullable = true)
    private String massage;

    @Column(name = "type", nullable = true)
    private String type;//HOLIDAY or ABSENCE


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HolidaySchedulePharmacist(Integer id, Pharmacist pharmacist, LocalDate startDate, LocalDate endDate, String approved, String massage, String type) {
        this.id = id;
        this.pharmacist = pharmacist;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approved = approved;
        this.massage = massage;
        this.type = type;
    }


    public HolidaySchedulePharmacist() {
        super();
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
