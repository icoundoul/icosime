package eu.els.sie.firestorm.backend.model.dto;

public class WSConfigurationDTO {
	
	private String version;
	private String artifactid;
	private String envName;
	private String allowedOrigins;
	private String dbname;
	private String dbNameHost;
	
		
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}	

	public String getArtifactid() {
		return artifactid;
	}
	public void setArtifactid(String artifactid) {
		this.artifactid = artifactid;
	}
	public String getEnvName() {
		return envName;
	}
	public void setEnvName(String envName) {
		this.envName = envName;
	}
	public String getAllowedOrigins() {
		return allowedOrigins;
	}
	public void setAllowedOrigins(String allowedOrigins) {
		this.allowedOrigins = allowedOrigins;
	}

	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getDbNameHost() {
		return dbNameHost;
	}
	public void setDbNameHost(String dbNameHost) {
		this.dbNameHost = dbNameHost;
	}
	
}
