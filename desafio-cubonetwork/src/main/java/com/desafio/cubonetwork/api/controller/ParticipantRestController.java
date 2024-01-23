package com.desafio.cubonetwork.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cubonetwork.dto.ParticipantRequestDto;
import com.desafio.cubonetwork.dto.ParticipationRequestDto;
import com.desafio.cubonetwork.dto.ParticipationResponseDto;
import com.desafio.cubonetwork.model.Participant;
import com.desafio.cubonetwork.service.ParticipantService;

import jakarta.validation.Valid;

@RestController
public class ParticipantRestController extends BaseRestController<Participant> {
	@Autowired
	private ParticipantService participantService;

	@GetMapping("/api/participant")
	public List<ParticipationResponseDto> findAll() {
		return this.participantService.findAll();
	}

	@PostMapping("/api/participant")
	public ResponseEntity<ParticipantRequestDto> save(@RequestBody @Valid ParticipantRequestDto participantRequestDto) {
		ParticipantRequestDto toReturn = this.participantService.save(participantRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(toReturn);
	}

	@PostMapping("/api/participation")
	public ResponseEntity<ParticipantRequestDto> save(@RequestBody @Valid ParticipationRequestDto participationRequestDto) {
		ParticipantRequestDto toReturn = this.participantService.save(participationRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(toReturn);
	}
}
