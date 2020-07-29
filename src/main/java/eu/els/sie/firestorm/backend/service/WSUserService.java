package eu.els.sie.firestorm.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import eu.els.sie.firestorm.backend.model.WSUser;
import eu.els.sie.firestorm.backend.model.dto.WSUserDTO;
import eu.els.sie.firestorm.backend.repository.WSUserMapper;
import eu.els.sie.firestorm.backend.repository.WSUserRespository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WSUserService {
	private final WSUserRespository wsUserRespository;
	private final WSUserMapper wsUserMapper;

	public List<WSUser> findAll() {
		return wsUserRespository.findAll();
	}

	public Optional<WSUser> findById(Long id) {
		return wsUserRespository.findById(id);
	}

	public WSUser save(WSUser stock) {
		return wsUserRespository.save(stock);
	}

	public void deleteById(Long id) {
		wsUserRespository.deleteById(id);
	}

	public boolean isValid(String wsUser) {

		List<WSUserDTO> wsUsers = wsUserMapper.toWSUserDTOs(findAll());

		List<WSUserDTO> filteredWsUsers = wsUsers.stream().filter(wsUserDTO -> (wsUserDTO.getName().equals(wsUser) && wsUserDTO.getIsActif()==1))
				.collect(Collectors.toList());

		return !CollectionUtils.isEmpty(filteredWsUsers);
	}
}
