package com.isaproject.isaproject.Model.HelpModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Complaint {


    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "patientId", nullable = false)
    private int patientId;


    @Column(name = "subject", nullable = false)
    private String subject;//na sta se zali


    @Column(name = "subjectId", nullable = false)
    private int subjectId;


    @Column(name = "answered", nullable = false)
    private boolean answered;


    @Column(name = "massage", nullable = false)
    private String massage;


    @Column(name = "answer", nullable = false)
    private String answer;

    public Complaint() {
    }

    public Complaint(Integer id, int patientId, String subject, int subjectId, boolean answered, String massage, String answer) {
        this.id = id;
        this.patientId = patientId;
        this.subject = subject;
        this.subjectId = subjectId;
        this.answered = answered;
        this.massage = massage;
        this.answer = answer;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
