package eu.els.sie.firestorm.backend.api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.els.sie.firestorm.backend.model.AppRole;
import eu.els.sie.firestorm.backend.model.dto.RoleDTO;
import eu.els.sie.firestorm.backend.repository.RoleMapper;
import eu.els.sie.firestorm.backend.repository.exception.BussinesException;
import eu.els.sie.firestorm.backend.service.RoleService;
import eu.els.sie.firestorm.backend.service.WSUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/roles")
public class RoleAPI extends AbstractAPI {
	private final RoleService roleService;
	private final RoleMapper roleMapper;
	private final WSUserService wsUserService;

	@GetMapping("/{wsUser}")
	public ResponseEntity<List<RoleDTO>> findAll(@PathVariable String wsUser) throws BussinesException {
		if (wsUserService.isValid(wsUser)) {
			return ResponseEntity.ok(roleMapper.toRoleDTOs(roleService.findAll()));
		} else {
			throw new BussinesException(INVALI_USER_CODE, String.format(INVALID_USER_MESSAGE, wsUser));
		}
	}

	@GetMapping("/{wsUser}/{role}")
	public ResponseEntity<RoleDTO> findById(@PathVariable String wsUser, @PathVariable String role)
			throws BussinesException {
		if (wsUserService.isValid(wsUser)) {
			Optional<AppRole> r = roleService.findById(role);

			return ResponseEntity.ok(roleMapper.toRoleDTO(r.orElse(null)));
		} else {
			throw new BussinesException(INVALI_USER_CODE, String.format(INVALID_USER_MESSAGE, wsUser));
		}
	}
}
