package com.isaproject.isaproject.Model.Schedule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WednesdaySchedule {
    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;


    @Column(name = "startTime", nullable = true)
    private LocalTime startTime;

    @Column(name = "endTime", nullable = true)
    private LocalTime endTime;

    @JsonManagedReference(value="schedule-wednesday")
    @OneToMany(mappedBy = "wednesdaySchedule", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<WorkingHoursPharmacist> workingHoursPharmacists = new HashSet<WorkingHoursPharmacist>();

    @JsonManagedReference(value="schedule-wednesday1")
    @OneToMany(mappedBy = "wednesdaySchedule", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<WorkingHoursDermatologist> workingHoursDermatologists = new HashSet<WorkingHoursDermatologist>();

    public WednesdaySchedule() {
    }

    public WednesdaySchedule(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Set<WorkingHoursPharmacist> getWorkingHoursPharmacists() {
        return workingHoursPharmacists;
    }

    public void setWorkingHoursPharmacists(Set<WorkingHoursPharmacist> workingHoursPharmacists) {
        this.workingHoursPharmacists = workingHoursPharmacists;
    }

    public Set<WorkingHoursDermatologist> getWorkingHoursDermatologists() {
        return workingHoursDermatologists;
    }

    public void setWorkingHoursDermatologists(Set<WorkingHoursDermatologist> workingHoursDermatologists) {
        this.workingHoursDermatologists = workingHoursDermatologists;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
