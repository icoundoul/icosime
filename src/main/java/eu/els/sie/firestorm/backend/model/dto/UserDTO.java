package eu.els.sie.firestorm.backend.model.dto;

import java.util.ArrayList;
import java.util.Collection;

import eu.els.sie.firestorm.backend.model.AppRole;
import lombok.Data;

@Data
public class UserDTO {

	private Long id;
	private String login;
	private String password;
	private String email;
	private String firstname;
	private String lastname;
	private boolean isActived;
	private Collection<AppRole> roles = new ArrayList<AppRole>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firtsname) {
		this.firstname = firtsname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActived() {
		return isActived;
	}

	public void setActived(boolean isActived) {
		this.isActived = isActived;
	}

	public Collection<AppRole> getRoles() {
		return roles;
	}

	public void setRoles(Collection<AppRole> roles) {
		this.roles = roles;
	}

	
}
