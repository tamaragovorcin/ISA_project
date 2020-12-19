package com.isaproject.isaproject.psw.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="hospitals")
public class Hospital {
	@Id
	private String apiKey;
	private String name;
	
	public Hospital() {}
	
	public Hospital(String name, String apiKey) {
		this.name = name;
		this.apiKey = apiKey;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
