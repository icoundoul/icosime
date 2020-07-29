package eu.els.sie.firestorm.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.els.sie.firestorm.backend.model.AppRole;
import eu.els.sie.firestorm.backend.model.AppUser;
import eu.els.sie.firestorm.backend.repository.RoleRespository;
import eu.els.sie.firestorm.backend.repository.UserRespository;
import eu.els.sie.firestorm.backend.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRespository userRepository;

	@Autowired
	private RoleRespository roleRespository;

	@Override
	public AppUser saveUser(AppUser user) {
		String hashPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hashPassword);
		return userRepository.save(user);
	}

	@Override
	public AppRole saveRole(AppRole role) {
		return roleRespository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		AppUser user = userRepository.findByLogin(username);
		AppRole r = roleRespository.findByRole(roleName);
		user.getRoles().add(r);
		userRepository.save(user);
	}

	@Override
	public AppUser findUserByUserName(String userName) {
		return userRepository.findByLogin(userName);
	}

}
