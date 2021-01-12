package com.isaproject.isaproject.Model.Schedule;

import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.Pharmacist;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class HolidayScheduleDermatologist {
    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "dermatologist_id", referencedColumnName = "id", nullable = false, unique = false)
    private Dermatologist dermatologist;


    @Column(name = "startDate", nullable = true)
    private LocalDate startDate;


    @Column(name = "endDate", nullable = true)
    private LocalDate endDate;


    @Column(name = "approved", nullable = true)
    private Boolean approved;


    @Column(name = "massage", nullable = true)
    private String massage;

    public HolidayScheduleDermatologist() {
        super();
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
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

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
