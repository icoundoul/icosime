package eu.els.sie.firestorm.backend.model.dto;

import lombok.Data;

@Data
public class RoleDTO {

	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String description;

}
