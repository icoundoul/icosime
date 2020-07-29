package eu.els.sie.firestorm.backend.model.dto;

import lombok.Data;

@Data
public class WSUserDTO {

	private int id;
	private String name;
	private int isActif;

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

	public int getIsActif() {
		return isActif;
	}

	public void setIsActif(int isActif) {
		this.isActif = isActif;
	}

}
