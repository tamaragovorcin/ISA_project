package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Users.Patient;

public class ComplaintDTO {

    private String subject;
    private UserBasicInfoDTO dermatologist;
    private UserBasicInfoDTO pharmacist;
    private PharmacyNameDTO pharmacyName;
    private boolean answered;
    private String massage;
    private String answer;

    public ComplaintDTO() {
    }

    public ComplaintDTO(String subject, UserBasicInfoDTO dermatologist, UserBasicInfoDTO pharmacist, PharmacyNameDTO pharmacyName, boolean answered, String massage, String answer) {
        this.subject = subject;
        this.dermatologist = dermatologist;
        this.pharmacist = pharmacist;
        this.pharmacyName = pharmacyName;
        this.answered = answered;
        this.massage = massage;
        this.answer = answer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public UserBasicInfoDTO getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(UserBasicInfoDTO dermatologist) {
        this.dermatologist = dermatologist;
    }

    public UserBasicInfoDTO getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(UserBasicInfoDTO pharmacist) {
        this.pharmacist = pharmacist;
    }

    public PharmacyNameDTO getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(PharmacyNameDTO pharmacyName) {
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