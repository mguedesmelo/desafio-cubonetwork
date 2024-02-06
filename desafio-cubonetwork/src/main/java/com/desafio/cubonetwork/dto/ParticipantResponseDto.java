package com.desafio.cubonetwork.dto;

import java.math.BigDecimal;

public record ParticipantResponseDto(Long id, String firstName, String lastName, 
		BigDecimal maxParticipation, BigDecimal totalParticipation) {
	// Empty
}
