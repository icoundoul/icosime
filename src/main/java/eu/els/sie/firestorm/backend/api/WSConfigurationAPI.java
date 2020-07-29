package eu.els.sie.firestorm.backend.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.els.sie.firestorm.backend.model.dto.WSConfigurationDTO;
import eu.els.sie.firestorm.backend.repository.exception.BussinesException;
import eu.els.sie.firestorm.backend.service.WSConfigurationService;
import eu.els.sie.firestorm.backend.service.WSUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/configuration")
public class WSConfigurationAPI extends AbstractAPI {
	private final WSConfigurationService configurationService;
	private final WSUserService wsUserService;

	@GetMapping("/{wsUser}")
	public ResponseEntity<WSConfigurationDTO> getConfiguration(@PathVariable String wsUser) throws BussinesException {
		if (wsUserService.isValid(wsUser)) {
			return ResponseEntity.ok(configurationService.getConfiguration());
		} else {
			throw new BussinesException(INVALI_USER_CODE, String.format(INVALID_USER_MESSAGE, wsUser));
		}
	}
	
}
