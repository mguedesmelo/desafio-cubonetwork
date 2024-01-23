package com.desafio.cubonetwork.dto;

import java.math.BigDecimal;

public record ParticipantRequestDto(Long id, String firstName, String lastName, BigDecimal totalParticipation) {

}
