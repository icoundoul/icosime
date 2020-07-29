package eu.els.sie.firestorm.backend.repository;

import java.util.List;

import org.mapstruct.Mapper;

import eu.els.sie.firestorm.backend.model.WSUser;
import eu.els.sie.firestorm.backend.model.dto.WSUserDTO;

@Mapper
public interface WSUserMapper {
	
    WSUserDTO toWSUserDTO(WSUser wsUser);

    List<WSUserDTO> toWSUserDTOs(List<WSUser> wsUsers);

    WSUser toWSUser(WSUserDTO wsUserDTO);
}
