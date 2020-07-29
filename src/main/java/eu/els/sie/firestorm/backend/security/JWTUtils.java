package eu.els.sie.firestorm.backend.security;

import java.util.Date;
import java.util.HashMap;

import org.springframework.security.core.userdetails.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils implements AbstractClaims{

	
	public static String createToken(User springUser, HashMap<String, String> userDetails) {
		String jwtToken = Jwts.builder().setSubject(springUser.getUsername())
				.setExpiration(new Date((System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME)))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
				.claim("roles", springUser.getAuthorities())
				.claim(userId, userDetails.get(userId))
				.claim(firstname, userDetails.get(firstname))
				.claim(lastname, userDetails.get(lastname))
				.compact();
		
		jwtToken = SecurityConstants.TOKEN_PREFIX + jwtToken;

		return jwtToken;
	}
}
