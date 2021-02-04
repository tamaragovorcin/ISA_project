package com.isaproject.isaproject.DTO;

public class RefuseHolidayScheduleDTO {
    private  Integer requestId;
    private  String reason;

    public RefuseHolidayScheduleDTO(Integer requestId, String reason) {
        this.requestId = requestId;
        this.reason = reason;
    }

    public RefuseHolidayScheduleDTO() { }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
