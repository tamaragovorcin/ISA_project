package com.isaproject.isaproject.psw.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="pharmacies")
public class Pharmacy {
	@Id
	private String apiKey;
	private String name;
	private String town;
	
	public Pharmacy() {}
	
	public Pharmacy(String apiKey,String name, String town) {
		this.apiKey = apiKey;
		this.name = name;
		this.town = town;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
