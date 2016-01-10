package com.bxtel.user.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import dinamica.generator.Model;

@Model
@Entity
public class SecurityData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private String path;
	@NotNull
	private String rolelist;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getRolelist() {
		return rolelist;
	}
	public void setRolelist(String rolelist) {
		this.rolelist = rolelist;
	}
	
}