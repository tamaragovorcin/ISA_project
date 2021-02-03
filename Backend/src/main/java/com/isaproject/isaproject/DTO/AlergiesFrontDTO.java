package com.isaproject.isaproject.DTO;

public class AlergiesFrontDTO {

    private Integer id;

    private Integer patient_id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AlergiesFrontDTO() {
    }
}
