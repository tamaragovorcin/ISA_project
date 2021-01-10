package com.isaproject.isaproject.Model.Examinations;

import com.isaproject.isaproject.Model.Entity;



public class Examination extends Entity {
    private int patientsId;
    private int examinationId;
    private Boolean cancelled;
    private Boolean showedUp;
    private String information;



    public Examination() {
        super();
    }

    public Examination(int id, int patientsId, int examinationId, Boolean cancelled, Boolean showedUp, String information) {
        super(id);
        this.patientsId = patientsId;
        this.examinationId = examinationId;
        this.cancelled = cancelled;
        this.showedUp = showedUp;
        this.information = information;
    }

    public Examination(int patientsId, int examinationId, Boolean cancelled, Boolean showedUp, String information) {
        this.patientsId = patientsId;
        this.examinationId = examinationId;
        this.cancelled = cancelled;
        this.showedUp = showedUp;
        this.information = information;
    }

    public int getPatientsId() {
        return patientsId;
    }

    public void setPatientsId(int patientsId) {
        this.patientsId = patientsId;
    }

    public int getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(int examinationId) {
        this.examinationId = examinationId;
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
