package eu.els.sie.firestorm.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.els.sie.firestorm.backend.model.AppUser;

public interface UserRespository extends JpaRepository<AppUser, Long> {
	
	 AppUser findByLogin(String login);
}
