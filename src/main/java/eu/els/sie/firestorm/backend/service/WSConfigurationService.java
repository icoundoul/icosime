package eu.els.sie.firestorm.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import eu.els.sie.firestorm.backend.model.dto.WSConfigurationDTO;
import eu.els.sie.firestorm.backend.model.dto.WSVersionDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class WSConfigurationService {

	@Value("${build.version}")
	private String version;

	@Value("${envName}")
	private String envName;

	@Value("${allowedOrigins}")
	private String allowedOrigins;

	@Value("${dbName}")
	private String dbName;

	@Value("${dbNameHost}")
	private String dbNameHost;

	@Value("${application.name}")
	private String artifactid;

	private String getVersion() {
		return version;
	}

	public String getEnvName() {
		return envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	private String getAllowedOrigins() {
		return allowedOrigins;
	}

	private String getDbName() {

		return dbName;
	}

	public String getDbNameHost() {
		return dbNameHost;
	}

	public String getArtifactid() {
		return artifactid;
	}

	public void setArtifactid(String artifactid) {
		this.artifactid = artifactid;
	}

	public void setDbNameHost(String dbNameHost) {
		this.dbNameHost = dbNameHost;
	}

	public WSConfigurationDTO getConfiguration() {

		WSConfigurationDTO configurationDTO = new WSConfigurationDTO();

		configurationDTO.setAllowedOrigins(getAllowedOrigins());
		configurationDTO.setDbname(getDbName());
		configurationDTO.setVersion(getVersion());
		configurationDTO.setDbNameHost(getDbNameHost());
		configurationDTO.setArtifactid(getArtifactid());
		configurationDTO.setEnvName(getEnvName());

		return configurationDTO;
	}

	public WSVersionDTO getWSVersion() {

		WSVersionDTO wsVersion = new WSVersionDTO();
		wsVersion.setVersion(getVersion());

		return wsVersion;
	}

}
