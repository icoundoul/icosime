package eu.els.sie.firestorm.backend.security;

public class SecurityConstants {

	public static final String SECRET = "firestorm";
	public static final int EXPIRATION_TIME = 864_000_000; // 10 jours
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
}
