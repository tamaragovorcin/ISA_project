package com.isaproject.isaproject.DTO;

public class ConsultingForBackDTO {
    private Integer consultingId;
    private String information;
    private double duration;

    public ConsultingForBackDTO(Integer consultingId, String information, double duration) {
        this.consultingId = consultingId;
        this.information = information;
        this.duration = duration;
    }

    public ConsultingForBackDTO() { }

    public Integer getConsultingId() {
        return consultingId;
    }

    public void setConsultingId(Integer consultingId) {
        this.consultingId = consultingId;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
