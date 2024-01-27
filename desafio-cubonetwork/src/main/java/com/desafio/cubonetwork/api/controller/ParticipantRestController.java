package com.desafio.cubonetwork.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cubonetwork.dto.ParticipantRequestDto;
import com.desafio.cubonetwork.dto.ParticipantResponseDto;
import com.desafio.cubonetwork.dto.ParticipationRequestDto;
import com.desafio.cubonetwork.dto.ParticipationResponseDto;
import com.desafio.cubonetwork.model.Participant;
import com.desafio.cubonetwork.service.ParticipantService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
public class ParticipantRestController extends BaseRestController<Participant> {
	@Autowired
	private ParticipantService participantService;

	@GetMapping("/api/participation")
	public ResponseEntity<List<ParticipationResponseDto>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(this.participantService.findAll());
	}

	@GetMapping("/api/participation/{id}")
	public ResponseEntity<ParticipationResponseDto> find(@PathVariable("id") @NotNull Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.participantService.find(id));
	}

	@GetMapping("/api/participant/{id}")
	public ResponseEntity<ParticipantResponseDto> findParticipant(@PathVariable("id") @NotNull Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.participantService.findParticipant(id));
	}

	@PostMapping("/api/participant")
	public ResponseEntity<ParticipantResponseDto> save(@RequestBody @Valid ParticipantRequestDto participantRequestDto) {
		ParticipantResponseDto toReturn = this.participantService.save(participantRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(toReturn);
	}

	@PostMapping("/api/participation")
	public ResponseEntity<ParticipantResponseDto> save(@RequestBody @Valid ParticipationRequestDto participationRequestDto) {
		ParticipantResponseDto toReturn = this.participantService.save(participationRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(toReturn);
	}
}
