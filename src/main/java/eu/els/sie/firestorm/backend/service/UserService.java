package eu.els.sie.firestorm.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import eu.els.sie.firestorm.backend.model.AppUser;
import eu.els.sie.firestorm.backend.repository.UserRespository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class UserService {
    private final UserRespository userRespository;

    public List<AppUser> findAll() {
        return userRespository.findAll();
    }

    public Optional<AppUser> findById(Long id) {
        return userRespository.findById(id);
    }

    public AppUser save(AppUser stock) {
        return userRespository.save(stock);
    }

    public void deleteById(Long id) {
        userRespository.deleteById(id);
    }
}
