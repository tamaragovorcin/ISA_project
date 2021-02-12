package com.isaproject.isaproject.DTO;

public class InquireDTO {
    private  String name;
    private Long code;

    public InquireDTO(String name, Long code) {
        this.name = name;
        this.code = code;
    }

    public InquireDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
