package com.boscofest.fest16;

import java.io.Serializable;

public class Event implements Serializable {
	private static final long serialVersionUID = 1L;
	int id; String name, description, rules;
	int isOnstage;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public int getIsOnstage() {
		return isOnstage;
	}
	public void setIsOnstage(int isOnstage) {
		this.isOnstage = isOnstage;
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
	public String getRules() {
		return rules;
	}
	public void setRules(String rules) {
		this.rules = rules;
	}
}
