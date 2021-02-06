package com.isaproject.isaproject.DTO;

public class ExaminationForBack {
    private Integer examinationId;
    private String information;

    public ExaminationForBack() {

    }

    public ExaminationForBack(Integer examinationId, String information) {
        this.examinationId = examinationId;
        this.information = information;
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
