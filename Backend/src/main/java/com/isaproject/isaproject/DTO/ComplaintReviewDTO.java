package com.isaproject.isaproject.DTO;

public class ComplaintReviewDTO {
    private Integer id;
    private PersonUserDTO patient;
    private String subject;
    private PersonUserDTO dermatologist;
    private PersonUserDTO pharmacist;
    private PharmacyDTO pharmacyName;
    private boolean answered;
    private String massage;
    private String answer;

    public ComplaintReviewDTO() {
    }

    public ComplaintReviewDTO(Integer id, PersonUserDTO patient, String subject, PersonUserDTO dermatologist, PersonUserDTO pharmacist, PharmacyDTO pharmacyName, boolean answered, String massage, String answer) {
        this.id = id;
        this.patient = patient;
        this.subject = subject;
        this.dermatologist = dermatologist;
        this.pharmacist = pharmacist;
        this.pharmacyName = pharmacyName;
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

    public PersonUserDTO getPatient() {
        return patient;
    }

    public void setPatient(PersonUserDTO patient) {
        this.patient = patient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public PersonUserDTO getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(PersonUserDTO dermatologist) {
        this.dermatologist = dermatologist;
    }

    public PersonUserDTO getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(PersonUserDTO pharmacist) {
        this.pharmacist = pharmacist;
    }

    public PharmacyDTO getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(PharmacyDTO pharmacyName) {
        this.pharmacyName = pharmacyName;
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
