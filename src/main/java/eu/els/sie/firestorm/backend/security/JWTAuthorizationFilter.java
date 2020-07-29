package eu.els.sie.firestorm.backend.security;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Authoriser les domaines
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "DELETE, POST, GET, PUT, OPTIONS");
		// Autorise les frontEnd à envoyer des headers
		response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, "
				+ "Access-Control-Request-Method, " 
				+ "Access-Control-Request-Headers," 
				+ "Authorization");
		// Authorise le sfrontEnd à lire les headers dans les réponses
		response.addHeader("Access-Control-Expose-Headers",
				"Access-Control-Allow-Origin, " 
		      + "Access-Control-Allow-Credentials, Authorization");

		String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
		
		if (request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {

			if (jwtToken == null || !jwtToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
				filterChain.doFilter(request, response);
				return;
			}

			Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET)
					.parseClaimsJws(jwtToken.replace(SecurityConstants.TOKEN_PREFIX, "")).getBody();

			String username = claims.getSubject();
			ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>) claims.get("roles");

			Collection<GrantedAuthority> authorities = new ArrayList<>();
			roles.forEach(r -> {
				authorities.add(new SimpleGrantedAuthority(r.get("authority")));
			});

			// Charger l'utilisateur authentifié dans le contexte spring
			UsernamePasswordAuthenticationToken athenticatedUser = new UsernamePasswordAuthenticationToken(username,
					null, authorities); // pas beosin du mot de passe donc null. En plus il n'est pas dans le tocken
			SecurityContextHolder.getContext().setAuthentication(athenticatedUser);
			filterChain.doFilter(request, response);
		}
	}

}
