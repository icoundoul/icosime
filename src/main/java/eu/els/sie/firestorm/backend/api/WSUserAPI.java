package eu.els.sie.firestorm.backend.api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.els.sie.firestorm.backend.model.WSUser;
import eu.els.sie.firestorm.backend.model.dto.WSUserDTO;
import eu.els.sie.firestorm.backend.repository.WSUserMapper;
import eu.els.sie.firestorm.backend.repository.exception.BussinesException;
import eu.els.sie.firestorm.backend.service.WSUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/wsusers")
public class WSUserAPI extends AbstractAPI {
	private final WSUserService wsUserService;
	private final WSUserMapper wsUserMapper;

	@GetMapping("/{wsUser}")
	public ResponseEntity<List<WSUserDTO>> findAll(@PathVariable String wsUser) throws BussinesException {
		if (wsUserService.isValid(wsUser)) {
			return ResponseEntity.ok(wsUserMapper.toWSUserDTOs(wsUserService.findAll()));
		} else {
			throw new BussinesException(INVALI_USER_CODE, String.format(INVALID_USER_MESSAGE, wsUser));
		}
	}

	@PostMapping("/{wsUser}")
	public ResponseEntity<WSUserDTO> create(@PathVariable String wsUser, @RequestBody WSUserDTO wsUserDTO)
			throws BussinesException {
		if (wsUserService.isValid(wsUser)) {
			wsUserService.save(wsUserMapper.toWSUser(wsUserDTO));

			return ResponseEntity.status(HttpStatus.CREATED).body(wsUserDTO);
		} else {
			throw new BussinesException(INVALI_USER_CODE, String.format(INVALID_USER_MESSAGE, wsUser));
		}
	}

	@GetMapping("/{wsUser}/{id}")
	public ResponseEntity<WSUserDTO> findById(@PathVariable String wsUser, @PathVariable Long id)
			throws BussinesException {
		if (wsUserService.isValid(wsUser)) {
			Optional<WSUser> user = wsUserService.findById(id);

			return ResponseEntity.ok(wsUserMapper.toWSUserDTO(user.orElse(null)));
		} else {
			throw new BussinesException(INVALI_USER_CODE, String.format(INVALID_USER_MESSAGE, wsUser));
		}
	}

	@PutMapping("/{wsUser}/{id}")
	public ResponseEntity<WSUserDTO> update(@PathVariable String wsUser, @PathVariable Long id,
			@RequestBody WSUserDTO wsUserDTO) throws BussinesException {
		if (wsUserService.isValid(wsUser)) {
			WSUser wsUserToSave = wsUserMapper.toWSUser(wsUserDTO);
			wsUserToSave.setId(id);
			wsUserService.save(wsUserToSave);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(wsUserDTO);
		} else {
			throw new BussinesException(INVALI_USER_CODE, String.format(INVALID_USER_MESSAGE, wsUser));
		}
	}

	@DeleteMapping("/{wsUser}/{id}")
	public ResponseEntity delete(@PathVariable String wsUser, @PathVariable Long id) throws BussinesException {
		if (wsUserService.isValid(wsUser)) {
			wsUserService.deleteById(id);

			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} else {
			throw new BussinesException(INVALI_USER_CODE, String.format(INVALID_USER_MESSAGE, wsUser));
		}
	}
}
