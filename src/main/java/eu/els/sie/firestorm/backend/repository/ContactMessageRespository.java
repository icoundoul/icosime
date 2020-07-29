package eu.els.sie.firestorm.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.els.sie.firestorm.backend.model.ContactMessage;

public interface ContactMessageRespository extends JpaRepository<ContactMessage, Long> {
}
