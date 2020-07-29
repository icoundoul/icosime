package eu.els.sie.firestorm.backend.repository;

import org.mapstruct.Mapper;

import eu.els.sie.firestorm.backend.model.AppRole;
import eu.els.sie.firestorm.backend.model.dto.RoleDTO;

import java.util.List;

@Mapper
public interface RoleMapper {
    RoleDTO toRoleDTO(AppRole role);

    List<RoleDTO> toRoleDTOs(List<AppRole> roles);

    AppRole toRole(RoleDTO roleDTO);
}
