package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Users.Patient;

public class NewExaminationDTO {
    private Integer patient;

    private Boolean cancelled;

    private Boolean showedUp;

    private Integer examinationId;

    private String information;

    public NewExaminationDTO() {
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Boolean getShowedUp() {
        return showedUp;
    }

    public void setShowedUp(Boolean showedUp) {
        this.showedUp = showedUp;
    }

    public Integer getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
