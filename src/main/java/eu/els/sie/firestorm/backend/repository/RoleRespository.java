package eu.els.sie.firestorm.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.els.sie.firestorm.backend.model.AppRole;

public interface RoleRespository extends JpaRepository<AppRole, String> {

	AppRole findByRole(String role);
}
