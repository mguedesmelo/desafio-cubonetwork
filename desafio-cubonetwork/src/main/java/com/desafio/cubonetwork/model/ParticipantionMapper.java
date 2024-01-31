package com.desafio.cubonetwork.model;

import org.springframework.stereotype.Component;

import com.desafio.cubonetwork.dto.ParticipationRequestDto;

/**
 * Class to map the Participation entity to the ParticipationDto and vice-versa.
 * ModelMapper currently does not support record types.
 */
@Component
public class ParticipantionMapper extends BaseMapper {
	public ParticipationRequestDto map(Participation participation) {
		if (participation == null) {
			return null;
		}
		return new ParticipationRequestDto(participation.getId(), 
				participation.getParticipant().getId(), 
				participation.getParticipation());
	}

	public Participation toModel(ParticipationRequestDto participationDto) {
		Participation toReturn = new Participation();
		// FIXME Remove id from request?
		toReturn.setId(participationDto.id());
		toReturn.setParticipation(participationDto.participation());

		return toReturn;
	}
}
