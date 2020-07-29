package eu.els.sie.firestorm.backend.api;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.els.sie.firestorm.backend.model.ContactMessage;
import eu.els.sie.firestorm.backend.model.dto.ContactMessageDTO;
import eu.els.sie.firestorm.backend.repository.ContactMessageMapper;
import eu.els.sie.firestorm.backend.repository.exception.BussinesException;
import eu.els.sie.firestorm.backend.service.ContactMessageService;
import eu.els.sie.firestorm.backend.service.WSUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/contactmessages")
public class ContactMessageAPI extends AbstractAPI {

	private final ContactMessageService contactMessageService;
	private final ContactMessageMapper contactMessageMapper;
	private final WSUserService wsUserService;
	private static final String CONTENT_DISPOSITION = "Content-Disposition";
	private static final String CONTACT_MESSAGE_OWNER_FORMAT = "\n\n==================== Message de %s du %s ======================\n\n";

	@GetMapping("/{wsUser}")
	public ResponseEntity<List<ContactMessage>> findAll(@PathVariable String wsUser) throws BussinesException {
		if (wsUserService.isValid(wsUser)) {
			return ResponseEntity.ok(contactMessageService.findAll());
		}
		throw new BussinesException(INVALI_USER_CODE, String.format(INVALID_USER_MESSAGE, wsUser));
	}

	@PostMapping("/{wsUser}")
	public ResponseEntity<ContactMessageDTO> create(@PathVariable String wsUser,
			@RequestBody ContactMessageDTO contactMessageDTO) throws BussinesException {
		if (wsUserService.isValid(wsUser)) {
			contactMessageService.save(contactMessageMapper.toContactMessage(contactMessageDTO));
			return ResponseEntity.status(HttpStatus.CREATED).body(contactMessageDTO);
		} else {
			throw new BussinesException(INVALI_USER_CODE, String.format(INVALID_USER_MESSAGE, wsUser));
		}
	}

	@GetMapping("/{wsUser}/{id}")
	public ResponseEntity<ContactMessageDTO> findById(@PathVariable String wsUser, @PathVariable Long id)
			throws BussinesException {
		if (wsUserService.isValid(wsUser)) {
			Optional<ContactMessage> contactMessage = contactMessageService.findById(id);
			return ResponseEntity.ok(contactMessageMapper.toContactMessageDTO(contactMessage.orElse(null)));
		} else {
			throw new BussinesException(INVALI_USER_CODE, String.format(INVALID_USER_MESSAGE, wsUser));
		}
	}

	@PutMapping("/{wsUser}/{id}")
	public ResponseEntity<ContactMessageDTO> update(@PathVariable String wsUser, @PathVariable Long id,
			@RequestBody ContactMessageDTO contactMessageDTO) throws BussinesException {
		if (wsUserService.isValid(wsUser)) {
			ContactMessage contactMessage = contactMessageMapper.toContactMessage(contactMessageDTO);
			contactMessage.setId(id);
			contactMessageService.save(contactMessage);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(contactMessageDTO);
		} else {
			throw new BussinesException(INVALI_USER_CODE, String.format(INVALID_USER_MESSAGE, wsUser));
		}
	}

	@DeleteMapping("/{wsUser}/{id}")
	public ResponseEntity delete(@PathVariable String wsUser, @PathVariable Long id) throws BussinesException {
		if (wsUserService.isValid(wsUser)) {
			contactMessageService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} else {
			throw new BussinesException(INVALI_USER_CODE, String.format(INVALID_USER_MESSAGE, wsUser));
		}
	}

	@GetMapping(path = "/downloadcomments", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> getCommentsFile() {

		List<ContactMessage> allContactMessages = contactMessageService.findAll();
		StringBuilder exportedContent = new StringBuilder();

		for (ContactMessage contactMessage : allContactMessages) {
			exportedContent.append(
					String.format(CONTACT_MESSAGE_OWNER_FORMAT, contactMessage.getOwnerLogin(), new Date().toString()));
			exportedContent.append(contactMessage.getMessage());
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setAccessControlExposeHeaders(Collections.singletonList(CONTENT_DISPOSITION));
		String fileName = "firestorm_messages.txt";
		headers.set(CONTENT_DISPOSITION, "attachment; filename=" + fileName);

		return new ResponseEntity<>(exportedContent.toString(), headers, HttpStatus.OK);
	}

	@GetMapping(path = "/storemessage/message/{message}/fuser/{fuser}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> storeComment(@PathVariable String message, @PathVariable String fuser) {

		ContactMessageDTO contactMessageDTO = new ContactMessageDTO();
		contactMessageDTO.setMessage(message);
		contactMessageDTO.setOwnerLogin(fuser);
		try {
			contactMessageService.save(contactMessageMapper.toContactMessage(contactMessageDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
