package com.desafio.cubonetwork.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ParticipantRequestDto(
		Long id, 
		@NotNull String firstName, 
		@NotNull String lastName, 
		@NotNull @Positive BigDecimal maxParticipation) {

}
