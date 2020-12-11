package com.isaproject.isaproject.psw.model;

import javax.persistence.Id;

public class MedicineName {
    @Id
    private String name;
    public MedicineName(String name) {
        this.name = name;
    }
    public MedicineName() {}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
