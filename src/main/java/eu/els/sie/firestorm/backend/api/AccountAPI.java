package eu.els.sie.firestorm.backend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.els.sie.firestorm.backend.model.AppRole;
import eu.els.sie.firestorm.backend.model.AppUser;
import eu.els.sie.firestorm.backend.model.RegisterForm;
import eu.els.sie.firestorm.backend.model.dto.UserDTO;
import eu.els.sie.firestorm.backend.repository.UserMapper;
import eu.els.sie.firestorm.backend.repository.exception.BussinesException;
import eu.els.sie.firestorm.backend.service.AccountService;
import eu.els.sie.firestorm.backend.service.UserService;
import eu.els.sie.firestorm.backend.service.WSUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/account")
public class AccountAPI extends AbstractAPI {
	@Autowired
	private AccountService accountService;
	private final UserService userService; 
	private final WSUserService wsUserService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserMapper userMapper;
	
	@PostMapping("/register/{wsUser}")
	public ResponseEntity<UserDTO> register(@PathVariable String wsUser, @RequestBody RegisterForm userForm)
			throws BussinesException {
		if (wsUserService.isValid(wsUser)) {
			if (!userForm.getPassword().equals(userForm.getRePassword())) {
				throw new RuntimeException("You must confirm your password");
			}
			
			AppUser user = accountService.findUserByUserName(userForm.getLogin());
			
			if (user!= null) {
				throw new RuntimeException(String.format("This user %s already exists", userForm.getLogin()));
			}

			UserDTO userDTO = new UserDTO();
			userDTO.setLogin(userForm.getLogin());
			userDTO.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
			userDTO.setFirstname(userForm.getFirstname());
			userDTO.setLastname(userForm.getLastname());
			userDTO.setEmail(userForm.getEmail());
			
			
			userService.save(userMapper.toUser(userDTO));

			return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
		} else {
			throw new BussinesException(INVALI_USER_CODE, String.format(INVALID_USER_MESSAGE, wsUser));
		}
	}}

