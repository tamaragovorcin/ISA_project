package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Users.Patient;

public class SendRequestExaminationDTO {
    private Integer patientId;
    private Integer examinationTermId;

    public SendRequestExaminationDTO(Integer patientId, Integer examinationTermId) {
        this.patientId = patientId;
        this.examinationTermId = examinationTermId;
    }
    public SendRequestExaminationDTO() {

    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getExaminationTermId() {
        return examinationTermId;
    }

    public void setExaminationTermId(Integer examinationTermId) {
        this.examinationTermId = examinationTermId;
    }
}
