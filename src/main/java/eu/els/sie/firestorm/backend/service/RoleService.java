package eu.els.sie.firestorm.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import eu.els.sie.firestorm.backend.model.AppRole;
import eu.els.sie.firestorm.backend.repository.RoleRespository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRespository roleRespository;

    public List<AppRole> findAll() {
        return roleRespository.findAll();
    }

    public Optional<AppRole> findById(String role) {
        return roleRespository.findById(role);
    }

    public AppRole save(AppRole stock) {
        return roleRespository.save(stock);
    }

    public void deleteById(String role) {
        roleRespository.deleteById(role);
    }
}
