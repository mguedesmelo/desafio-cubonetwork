package com.desafio.cubonetwork.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ParticipationRequestDto(
		Long id, 
		@NotNull Long idParticipant, 
		@NotNull @Positive BigDecimal participation) {

}
