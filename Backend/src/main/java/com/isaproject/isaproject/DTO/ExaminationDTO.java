package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Users.Patient;

public class ExaminationDTO {

    private Integer patient;

    private Boolean cancelled;

    private Boolean showedUp;

    private Integer examinationId;

    private String information;

    public ExaminationDTO() {  }

    public ExaminationDTO(Patient patient, Boolean cancelled, Boolean showedUp, Integer examinationId, String information) {

        this.cancelled = cancelled;
        this.showedUp = showedUp;
        this.examinationId = examinationId;
        this.information = information;
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
