package eu.els.sie.firestorm.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
	public void globalConfig(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(bCryptPasswordEncoder);
	
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		//http.formLogin();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/swagger-ui.html","/webjars/**","/swagger-resources/**","/v2/api-docs/**","/api/v1/auth/**","/login/**","/#login/**","/api/v1/account/register/**","/api/v1/version/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/users/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/configuration/**").hasAnyAuthority("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasAnyAuthority("ADMIN");
		//http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/v1/users/**").hasAnyAuthority("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/v1/wsusers/**").hasAnyAuthority("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/v1/wsusers/**").hasAnyAuthority("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	
	//Utile pourle getToken dans WS authenticationAPI 
	 @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	 @Override
	 public AuthenticationManager authenticationManagerBean() throws Exception {
	       return super.authenticationManagerBean();
	 }
}
