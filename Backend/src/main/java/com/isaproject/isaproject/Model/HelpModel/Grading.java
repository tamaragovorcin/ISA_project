package com.isaproject.isaproject.Model.HelpModel;

import com.isaproject.isaproject.Model.Entity;

public class Grading extends Entity {
    private int patientId;
    private String type;
    private int gradedId;
    private int grade;

    public Grading() {
        super();
    }
    public Grading(int id, int patientId, String type, int gradedId, int grade) {
        super(id);
        this.patientId = patientId;
        this.type = type;
        this.gradedId = gradedId;
        this.grade = grade;
    }

    public Grading(int patientId, String type, int gradedId, int grade) {
        this.patientId = patientId;
        this.type = type;
        this.gradedId = gradedId;
        this.grade = grade;
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
