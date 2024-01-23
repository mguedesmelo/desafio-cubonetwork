package com.desafio.cubonetwork.dto;

import java.math.BigDecimal;

public record ParticipationResponseDto(String firstName, String lastName, BigDecimal maxParticipation, 
		BigDecimal totalParticipation, BigDecimal percentage) {

}
