package com.isaproject.isaproject.psw.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="medicines")
public class Medicine {
	@Id
	private int id;
	private String name;
	private String description;
	private String unwantedReactions;
	public Medicine(int id, String name, String description, String unwantedReactions) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.unwantedReactions = unwantedReactions;
	}
	public Medicine() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUnwantedReactions() {
		return unwantedReactions;
	}
	public void setUnwantedReactions(String unwantedReactions) {
		this.unwantedReactions = unwantedReactions;
	}


}
