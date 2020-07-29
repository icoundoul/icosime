package eu.els.sie.firestorm.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.els.sie.firestorm.backend.model.WSUser;

public interface WSUserRespository extends JpaRepository<WSUser, Long> {
}
