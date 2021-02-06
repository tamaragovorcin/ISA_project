package com.isaproject.isaproject.DTO;
import java.time.LocalDate;
import java.time.LocalTime;

public class ConsultingsFrontDTO {

    private Integer id;

    private LocalDate date;

    private LocalTime time;

    private double duration;

    private String name;

    private String surname;

    private Boolean finished;

    public LocalDate getDate() {
        return date;
    }

    public ConsultingsFrontDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
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
}
