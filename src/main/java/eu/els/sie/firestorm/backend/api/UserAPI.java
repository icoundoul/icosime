package eu.els.sie.firestorm.backend.api;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.els.sie.firestorm.backend.model.AppRole;
import eu.els.sie.firestorm.backend.model.AppUser;
import eu.els.sie.firestorm.backend.model.dto.UserDTO;
import eu.els.sie.firestorm.backend.repository.CryptoUtils;
import eu.els.sie.firestorm.backend.repository.UserMapper;
import eu.els.sie.firestorm.backend.repository.exception.BussinesException;
import eu.els.sie.firestorm.backend.service.RoleService;
import eu.els.sie.firestorm.backend.service.UserService;
import eu.els.sie.firestorm.backend.service.WSUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/users")
public class UserAPI extends AbstractAPI {
	private final UserService userService;
	private final RoleService roleService;
	private final UserMapper userMapper;
	private final WSUserService wsUserService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/{wsUser}")
	public ResponseEntity<List<AppUser>> findAll(@PathVariable String wsUser) throws BussinesException {
		if (wsUserService.isValid(wsUser)) {
			//return ResponseEntity.ok(userMapper.toUserDTOs(userService.findAll()));
			return ResponseEntity.ok(userService.findAll());
			
		} else {
			throw new BussinesException(INVALI_USER_CODE, String.format(INVALID_USER_MESSAGE, wsUser));
		}
	}

	@PostMapping("/{wsUser}")
	public ResponseEntity<UserDTO> create(@PathVariable String wsUser, @RequestBody UserDTO userDTO)
			throws BussinesException {
		if (wsUserService.isValid(wsUser)) {

			if (StringUtils.isEmpty(userDTO.getPassword())) {
				userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getLogin()));
			} else {
				userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
			}
			userService.save(userMapper.toUser(userDTO));

			return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
		} else {
			throw new BussinesException(INVALI_USER_CODE, String.format(INVALID_USER_MESSAGE, wsUser));
		}
	}

	@GetMapping("/{wsUser}/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String wsUser, @PathVariable Long id)
			throws BussinesException {
		if (wsUserService.isValid(wsUser)) {
			Optional<AppUser> user = userService.findById(id);

			return ResponseEntity.ok(userMapper.toUserDTO(user.orElse(null)));
		} else {
			throw new BussinesException(INVALI_USER_CODE, String.format(INVALID_USER_MESSAGE, wsUser));
		}
	}

	@PutMapping("/{wsUser}/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable String wsUser, @PathVariable Long id,
			@RequestBody UserDTO userDTO) throws BussinesException {
		if (wsUserService.isValid(wsUser)) {
			Optional<AppUser>  appUserToUpdate = userService.findById(id);
			
			String oldPwd = appUserToUpdate.get().getPassword();
			AppUser user = userMapper.toUser(userDTO);
			user.setId(id);

			if (!oldPwd.equals(user.getPassword())) { // pwd soumis encryted and changed, update
				user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			}			

			userService.save(user);

			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDTO);
		} else {
			throw new BussinesException(INVALI_USER_CODE, String.format(INVALID_USER_MESSAGE, wsUser));
		}
	}

	@PutMapping("/{wsUser}/{idUser}/{role}")
	public ResponseEntity<UserDTO> addRoleToUser(@PathVariable String wsUser, @PathVariable Long idUser,
			@PathVariable String role) throws BussinesException {
		if (wsUserService.isValid(wsUser)) {
			Optional<AppUser> user = userService.findById(idUser);

			Optional<AppRole> r = roleService.findById(role);
			if (!r.isPresent()) {
				throw new BussinesException(INVALID_ROLE_CODE, String.format(INVALID_ROLE_MESSAGE, wsUser));
			} 
			if (!contains(user.get().getRoles(), r.get())) {
				user.get().getRoles().add(r.orElse(null));
			}

			userService.save(user.orElse(null));
			UserDTO userDTO = userMapper.toUserDTO(user.get());

			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDTO);
		} else {
			throw new BussinesException(INVALI_USER_CODE, String.format(INVALID_USER_MESSAGE, wsUser));
		}
	}


	@DeleteMapping("/{wsUser}/{id}")
	public ResponseEntity delete(@PathVariable String wsUser, @PathVariable Long id) throws BussinesException {
		if (wsUserService.isValid(wsUser)) {
			userService.deleteById(id);

			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} else {
			throw new BussinesException(INVALI_USER_CODE, String.format(INVALID_USER_MESSAGE, wsUser));
		}
	}

	private boolean contains(Collection<AppRole> roles, AppRole role) {

		boolean result = roles.stream().anyMatch(r -> r.getRole().equals(role.getRole()));
		return result;
	}
}