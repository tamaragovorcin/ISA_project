package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import java.time.LocalDate;

public class HolidaySchedulePharmacistDTO {
    private Integer pharmacist;
    private LocalDate startDate;
    private LocalDate endDate;
    private String type;
    private String approved;

    public HolidaySchedulePharmacistDTO(Integer pharmacist, LocalDate startDate, LocalDate endDate, String type, String approved) {
        this.pharmacist = pharmacist;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.approved = approved;
    }
    public HolidaySchedulePharmacistDTO() { }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public Integer getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Integer pharmacist) {
        this.pharmacist = pharmacist;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
