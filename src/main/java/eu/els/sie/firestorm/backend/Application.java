package eu.els.sie.firestorm.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import eu.els.sie.firestorm.backend.model.AppRole;
import eu.els.sie.firestorm.backend.model.AppUser;
import eu.els.sie.firestorm.backend.model.WSUser;
import eu.els.sie.firestorm.backend.service.AccountService;
import eu.els.sie.firestorm.backend.service.RoleService;
import eu.els.sie.firestorm.backend.service.WSUserService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${allowedOrigins}")
	public String allowedOrigins;

	@Value("${defaultUser}")
	public String defaultUser;

	@Value("${defaultUserPwd}")
	public String defaultUserPwd;

	@Value("${defaultWSUser}")
	public String defaultWSUser;

	@Autowired
	private AccountService accountService;

	@Autowired
	private WSUserService wsUserService;
	
	@Autowired
	private RoleService roleService;
	
	
	static final public String ADMIN = "ADMIN";
	static final public String WEBMASTER = "WEBMASTER";
	static final public String USER = "USER";
	static final public String WSTEST = "WSTEST";


	
	/*
	 * @Bean public Docket swagger() { return new
	 * Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any
	 * ()) .paths(PathSelectors.any()).build(); }
	 */

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		
		//default WS user
		WSUser wsUser = null;		
		if (!wsUserService.isValid(WSTEST)) {
			wsUser = new WSUser();
			wsUser.setName(WSTEST);
			wsUser.setIsActif(1);
			wsUserService.save(wsUser);
		}
		
		// default roles
		AppRole defaultRole = null;
		if (!roleService.findById(ADMIN).isPresent()) {
			 defaultRole = new AppRole();
			 defaultRole.setRole(ADMIN);
			 defaultRole.setDescription("role d'administration sur tous les contenus"); 
			roleService.save(defaultRole);
		}
		
		if (!roleService.findById(WEBMASTER).isPresent()) {
			 defaultRole = new AppRole();
			 defaultRole.setRole(WEBMASTER);
			 defaultRole.setDescription("role webmaster avec moins de droits que l'admin"); 
			roleService.save(defaultRole);
		}
		
		if (!roleService.findById(USER).isPresent()) {
			 defaultRole = new AppRole();
			 defaultRole.setRole(USER);
			 defaultRole.setDescription("role donnant l'accès juste à la frot FO"); 
			roleService.save(defaultRole);
		}

		//default admin user
		AppUser u1 = accountService.findUserByUserName(defaultUser);
		if (u1 == null) {
			u1 = new AppUser();
			u1.setFirstname("Firestorm");
			u1.setLastname("DUPOND");
			u1.setEmail("firestorm@els.fr");
			u1.setLogin(defaultUser);
			u1.setPassword(defaultUserPwd);
			accountService.saveUser(u1);
			accountService.addRoleToUser(u1.getLogin(), ADMIN);
		}
	}
}
