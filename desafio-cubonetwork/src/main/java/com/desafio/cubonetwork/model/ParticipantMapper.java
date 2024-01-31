package com.desafio.cubonetwork.model;

import org.springframework.stereotype.Component;

import com.desafio.cubonetwork.dto.ParticipantRequestDto;
import com.desafio.cubonetwork.dto.ParticipantResponseDto;

/**
 * Class to map the Participant entity to the ParticipantDto and vice-versa.
 * ModelMapper currently does not support record types.
 */
@Component
public class ParticipantMapper extends BaseMapper {
	public ParticipantResponseDto map(Participant participant) {
		if (participant == null) {
			return null;
		}
		return new ParticipantResponseDto(participant.getId(), participant.getFirstName(), 
				participant.getLastName(), participant.getMaxParticipation(), 
				participant.getTotalParticipation());
	}

	public Participant toModel(ParticipantRequestDto participantDto) {
		Participant toReturn = new Participant();
		// FIXME Remove id from request?
		toReturn.setId(participantDto.id());
		toReturn.setFirstName(participantDto.firstName());
		toReturn.setLastName(participantDto.lastName());
		toReturn.setMaxParticipation(participantDto.maxParticipation());

		return toReturn;
	}
}
