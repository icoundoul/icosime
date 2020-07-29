package eu.els.sie.firestorm.backend.security;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.els.sie.firestorm.backend.model.AppUser;
import eu.els.sie.firestorm.backend.service.AccountService;
import eu.els.sie.firestorm.backend.service.BeanUtil;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter implements AbstractClaims{

	private AuthenticationManager authenticationManager;
	private HashMap<String, String> userDatails = new HashMap<String, String>();


	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		AppUser appUser = null;

		try {
			appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		System.out.println("**********************");

		System.out.println("username:" + appUser.getLogin());
		System.out.println("password:" + appUser.getPassword());

		AccountService accountService = BeanUtil.getBean(AccountService.class);
		
		AppUser fullAppUser = accountService.findUserByUserName(appUser.getLogin());
		
		userDatails.put(userId,fullAppUser.getId().toString());
		userDatails.put(firstname,fullAppUser.getFirstname());
		userDatails.put(lastname,fullAppUser.getLastname());
		
		return authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(appUser.getLogin(), appUser.getPassword()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User springUser = (User) authResult.getPrincipal();
		// creation du token & ajout dans la reponse
		String token = JWTUtils.createToken(springUser, userDatails); 
		response.addHeader(SecurityConstants.HEADER_STRING, token);

		// super.successfulAuthentication(request, response, chain, authResult);
	}

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

}
