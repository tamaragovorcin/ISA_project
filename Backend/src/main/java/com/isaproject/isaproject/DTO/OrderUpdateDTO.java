package com.isaproject.isaproject.DTO;

import java.time.LocalDate;
import java.util.Set;

public class OrderUpdateDTO {
    private Integer orderId;
    private LocalDate date;
    private String status;
    private Set<MedicationsInOrderDTO> medicationsInOrderDTO;

    public OrderUpdateDTO() {
    }

    public OrderUpdateDTO(Integer orderId, LocalDate date, String status, Set<MedicationsInOrderDTO> medicationsInOrderDTO) {
        this.orderId = orderId;
        this.date = date;
        this.status = status;
        this.medicationsInOrderDTO = medicationsInOrderDTO;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<MedicationsInOrderDTO> getMedicationsInOrderDTO() {
        return medicationsInOrderDTO;
    }

    public void setMedicationsInOrderDTO(Set<MedicationsInOrderDTO> medicationsInOrderDTO) {
        this.medicationsInOrderDTO = medicationsInOrderDTO;
    }
}
