package eu.els.sie.firestorm.backend.api;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eu.els.sie.firestorm.backend.model.AppUser;
import eu.els.sie.firestorm.backend.model.dto.JwtAuthenticationRequestDTO;
import eu.els.sie.firestorm.backend.model.dto.JwtAuthenticationResponseDTO;
import eu.els.sie.firestorm.backend.repository.exception.BussinesException;
import eu.els.sie.firestorm.backend.security.AbstractClaims;
import eu.els.sie.firestorm.backend.security.JWTUtils;
import eu.els.sie.firestorm.backend.service.AccountService;
import eu.els.sie.firestorm.backend.service.UserService;
import eu.els.sie.firestorm.backend.service.WSUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/auth")
public class AuthentificationAPI extends AbstractAPI implements AbstractClaims{
	private final WSUserService wsUserService;
	
	 @Autowired
	 private AuthenticationManager authenticationManager;
	 
	 @Autowired
	 private AccountService accountService;



	  @RequestMapping(value = "/{wsUser}/token", method = RequestMethod.POST)
	    public ResponseEntity<?> createAuthenticationToken( @PathVariable String wsUser, @RequestBody JwtAuthenticationRequestDTO authenticationRequest) throws AuthenticationException, BussinesException {
		  if (wsUserService.isValid(wsUser)) {
	        // Perform the security
	        final Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        authenticationRequest.getUsername(),
	                        authenticationRequest.getPassword()
	                )
	        );

	        User springUser = (User) authentication.getPrincipal();
	        AppUser appUser = accountService.findUserByUserName(springUser.getUsername());
	        
	        HashMap<String, String> userDetails =  new HashMap<>();
	        
	        userDetails.put(userId,appUser.getId().toString());
			userDetails.put(firstname,appUser.getFirstname());
			userDetails.put(lastname,appUser.getLastname());
	        
	        return ResponseEntity.ok(new JwtAuthenticationResponseDTO(JWTUtils.createToken(springUser,userDetails)));
	  } else {
			throw new BussinesException(INVALI_USER_CODE, String.format(INVALID_USER_MESSAGE, wsUser));
		}
	  }
}
