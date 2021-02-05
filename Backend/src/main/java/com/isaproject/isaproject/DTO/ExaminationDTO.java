package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Users.Patient;

public class ExaminationDTO {

    private Patient patient;

    private Boolean cancelled;

    private Boolean showedUp;

    private Integer examinationId;

    private String information;

    public ExaminationDTO() {  }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
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

    @Override
    public String toString() {
        return "ExaminationDTO{" +
                "patient=" + patient +
                ", cancelled=" + cancelled +
                ", showedUp=" + showedUp +
                ", examinationId=" + examinationId +
                ", information='" + information + '\'' +
                '}';
    }
}
