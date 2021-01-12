package com.isaproject.isaproject.Model.HelpModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Grading  {


    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "patientId", nullable = false)
    private int patientId;


    @Column(name = "type", nullable = false)
    private String type;


    @Column(name = "gradedId", nullable = false)
    private int gradedId;


    @Column(name = "grade", nullable = false)
    private int grade;

    public Grading() {
    }

    public Grading(Integer id, int patientId, String type, int gradedId, int grade) {
        this.id = id;
        this.patientId = patientId;
        this.type = type;
        this.gradedId = gradedId;
        this.grade = grade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGradedId() {
        return gradedId;
    }

    public void setGradedId(int gradedId) {
        this.gradedId = gradedId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
