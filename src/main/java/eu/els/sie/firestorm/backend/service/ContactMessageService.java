package eu.els.sie.firestorm.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import eu.els.sie.firestorm.backend.model.ContactMessage;
import eu.els.sie.firestorm.backend.repository.ContactMessageRespository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class ContactMessageService {
	
    private final ContactMessageRespository contactMessageRespository;

    public List<ContactMessage> findAll() {
        return contactMessageRespository.findAll();
    }

    public Optional<ContactMessage> findById(Long id) {
        return contactMessageRespository.findById(id);
    }

    public ContactMessage save(ContactMessage stock) {
        return contactMessageRespository.save(stock);
    }

    public void deleteById(Long id) {
        contactMessageRespository.deleteById(id);
    }  
}
