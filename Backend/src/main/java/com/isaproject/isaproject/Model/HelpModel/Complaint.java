package com.isaproject.isaproject.Model.HelpModel;

import com.isaproject.isaproject.Model.Entity;

public class Complaint extends Entity {
    private int patientId;
    private String subject;//na sta se zali
    private int subjectId;
    private boolean answered;
    private String massage;
    private String answer;

    public Complaint() {
        super();
    }
    public Complaint(int id, int patientId, String subject, int subjectId, boolean answered, String massage, String answer) {
        super(id);
        this.patientId = patientId;
        this.subject = subject;
        this.subjectId = subjectId;
        this.answered = answered;
        this.massage = massage;
        this.answer = answer;
    }

    public Complaint(int patientId, String subject, int subjectId, boolean answered, String massage, String answer) {
        this.patientId = patientId;
        this.subject = subject;
        this.subjectId = subjectId;
        this.answered = answered;
        this.massage = massage;
        this.answer = answer;
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
