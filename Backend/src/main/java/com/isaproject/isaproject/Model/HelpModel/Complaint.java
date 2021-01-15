package com.isaproject.isaproject.Model.HelpModel;

import com.isaproject.isaproject.Model.Users.Patient;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Complaint {


    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false, unique = false)
    private Patient patient;


    @Column(name = "subject", nullable = true)
    private String subject;//na sta se zali


    @Column(name = "subjectId", nullable = true)
    private int subjectId;


    @Column(name = "answered", nullable = true)
    private boolean answered;


    @Column(name = "massage", nullable = true)
    private String massage;


    @Column(name = "answer", nullable = true)
    private String answer;

    public Complaint() {
    }

    public Complaint(Integer id, Set<Patient> patients, String subject, int subjectId, boolean answered, String massage, String answer) {
        this.id = id;
        this.subject = subject;
        this.subjectId = subjectId;
        this.answered = answered;
        this.massage = massage;
        this.answer = answer;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
