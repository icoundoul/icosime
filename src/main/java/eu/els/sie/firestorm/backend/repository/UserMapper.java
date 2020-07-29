package eu.els.sie.firestorm.backend.repository;

import org.mapstruct.Mapper;

import eu.els.sie.firestorm.backend.model.AppUser;
import eu.els.sie.firestorm.backend.model.dto.UserDTO;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDTO toUserDTO(AppUser user);

    List<UserDTO> toUserDTOs(List<AppUser> users);

    AppUser toUser(UserDTO userDTO);
}
