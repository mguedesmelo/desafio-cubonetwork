package com.desafio.cubonetwork.model;

import org.springframework.stereotype.Component;

import com.desafio.cubonetwork.dto.ParticipantRequestDto;

/**
 * Class to map the Participant entity to the ParticipantDto and vice-versa.
 * ModelMapper currently does not support record types.
 */
@Component
public class ParticipantMapper extends BaseMapper {
	public ParticipantRequestDto map(Participant participant) {
		if (participant == null) {
			return null;
		}
		return new ParticipantRequestDto(participant.getId(), participant.getFirstName(), 
				participant.getLastName(), participant.getMaxParticipation());
	}

	public Participant toModel(ParticipantRequestDto participantDto) {
		Participant toReturn = new Participant();
		toReturn.setId(participantDto.id());
		toReturn.setFirstName(participantDto.firstName());
		toReturn.setLastName(participantDto.lastName());
		toReturn.setMaxParticipation(participantDto.maxParticipation());

		return toReturn;
	}
}
