package eu.els.sie.firestorm.backend.repository;

import org.mapstruct.Mapper;

import eu.els.sie.firestorm.backend.model.ContactMessage;
import eu.els.sie.firestorm.backend.model.dto.ContactMessageDTO;

import java.util.List;

@Mapper
public interface ContactMessageMapper {
    ContactMessageDTO toContactMessageDTO(ContactMessage contactMessage);

    List<ContactMessageDTO> toContactMessageDTOs(List<ContactMessage> contactMessages);

    ContactMessage toContactMessage(ContactMessageDTO contactMessageDTO);
}
