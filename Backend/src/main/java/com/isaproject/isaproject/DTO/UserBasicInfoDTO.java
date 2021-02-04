package com.isaproject.isaproject.DTO;

public class UserBasicInfoDTO {
    private String fullName;
    private String email;
    private Integer userId;


    public UserBasicInfoDTO() {
    }

    public UserBasicInfoDTO(String fullName, String email, Integer userId) {
        this.fullName = fullName;
        this.email = email;
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
