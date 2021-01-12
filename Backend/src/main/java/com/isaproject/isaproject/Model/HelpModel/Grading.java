package com.isaproject.isaproject.Model.HelpModel;

import com.isaproject.isaproject.Model.Users.Patient;

import javax.persistence.*;

@Entity
public class Grading  {

    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false, unique = false)
    private Patient patient;


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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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
