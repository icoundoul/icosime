package eu.els.sie.firestorm.backend.service;

import eu.els.sie.firestorm.backend.model.AppRole;
import eu.els.sie.firestorm.backend.model.AppUser;

public interface AccountService {

	public AppUser saveUser(AppUser user);

	public AppRole saveRole(AppRole role);

	public void addRoleToUser(String username, String roleName);
	
	public AppUser findUserByUserName(String userName);

}
