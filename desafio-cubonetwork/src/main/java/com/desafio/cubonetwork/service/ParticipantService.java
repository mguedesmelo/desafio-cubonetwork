package com.desafio.cubonetwork.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cubonetwork.dto.ParticipantRequestDto;
import com.desafio.cubonetwork.dto.ParticipationRequestDto;
import com.desafio.cubonetwork.dto.ParticipationResponseDto;
import com.desafio.cubonetwork.model.Participant;
import com.desafio.cubonetwork.model.ParticipantMapper;
import com.desafio.cubonetwork.repository.ParticipantRepository;

@Service
public class ParticipantService {
	@Autowired
	private ParticipantRepository participantRepository;
	@Autowired
	private ParticipantMapper participantMapper;

	public List<ParticipationResponseDto> findAll() {
		List<Participant> participants = this.participantRepository.findAll();

		return participants.stream().map(participant -> {
			List<BigDecimal> listParticipations = participant.getParticipations().stream().map(part -> {
				return part.getParticipation();
			}).collect(Collectors.toList());

			BigDecimal totalParticipation = listParticipations.stream().reduce(
					 BigDecimal.ZERO, BigDecimal::add);
			
			return new ParticipationResponseDto(participant.getFirstName(), participant.getLastName(),
					participant.getTotalParticipation(), totalParticipation);
		}).collect(Collectors.toList());
	}

	public ParticipantRequestDto save(Participant participant) {
		Participant toReturn = this.participantRepository.save(participant);
		return this.participantMapper.map(toReturn);
	}

	public ParticipantRequestDto save(ParticipantRequestDto participantRequestDto) {
		return this.save(this.participantMapper.toModel(participantRequestDto));
	}

	public ParticipantRequestDto save(ParticipationRequestDto participationRequestDto) {
		Participant participant = this.participantRepository.findById(
				participationRequestDto.idParticipant()).orElseThrow();
		participant.addParticipation(participationRequestDto.participation());
		return this.save(participant);
	}

	public void delete(ParticipantRequestDto participantRequestDto) {
		this.participantRepository.deleteById(participantRequestDto.id());
	}
}
