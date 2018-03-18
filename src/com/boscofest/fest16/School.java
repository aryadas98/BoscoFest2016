package com.boscofest.fest16;

import java.io.Serializable;

public class School implements Serializable {
	private static final long serialVersionUID = 1L;
	
	int id; String name, motto;
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
	public String getMotto() {
		return motto;
	}
	public void setMotto(String motto) {
		this.motto = motto;
	}
}
