package com.desafio.cubonetwork.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cubonetwork.dto.ParticipantRequestDto;
import com.desafio.cubonetwork.dto.ParticipantResponseDto;
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

	private ParticipationResponseDto toParticipationDto(Participant participant) {
		if (participant == null) {
			return null;
		}

		BigDecimal totalParticipation = participant.getTotalParticipation();
				
		BigDecimal percentage = participant.getMaxParticipation()
				.multiply(totalParticipation)
				.divide(new BigDecimal(100), 2, RoundingMode.UNNECESSARY);

		return new ParticipationResponseDto(participant.getFirstName(), participant.getLastName(),
				participant.getMaxParticipation(), totalParticipation, percentage);
	}

	public List<ParticipationResponseDto> findAll() {
		List<Participant> participants = this.participantRepository.findAll();

		return participants.stream().map(participant -> {
			return toParticipationDto(participant);
		}).collect(Collectors.toList());
	}

	public ParticipationResponseDto find(Long id) {
		Participant participant = this.participantRepository.findById(id).orElse(null);

		return toParticipationDto(participant);
	}

	public ParticipantResponseDto findParticipant(Long id) {
		return this.participantMapper.map(this.participantRepository.findById(id).orElse(null));
	}

	public ParticipantResponseDto save(Participant participant) {
		Participant toReturn = this.participantRepository.save(participant);
		return this.participantMapper.map(toReturn);
	}

	public ParticipantResponseDto save(ParticipantRequestDto participantRequestDto) {
		return this.save(this.participantMapper.toModel(participantRequestDto));
	}

	public ParticipantResponseDto save(ParticipationRequestDto participationRequestDto) {
		Participant participant = this.participantRepository.findById(
				participationRequestDto.idParticipant()).orElseThrow();
		participant.addParticipation(participationRequestDto.participation());
		return this.save(participant);
	}

	public void delete(ParticipantRequestDto participantRequestDto) {
		// FIXME Remover id do request??
		this.participantRepository.deleteById(participantRequestDto.id());
	}
}
