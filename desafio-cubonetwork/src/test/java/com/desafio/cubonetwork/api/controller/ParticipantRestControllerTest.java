/**
 * 
 */
package com.desafio.cubonetwork.api.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.desafio.cubonetwork.dto.ParticipantResponseDto;
import com.desafio.cubonetwork.dto.ParticipationRequestDto;
import com.desafio.cubonetwork.dto.ParticipationResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ParticipantRestControllerTest extends BaseRestControllerTest {
	private ParticipationResponseDto doFindParticipation(Long id) throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders
	            .get("/api/participation/" + id)
	            .servletPath("/api/participation/" + id)
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andReturn();

		if (result.getResponse().getContentAsString().isBlank()) {
			return null;
		}
		return (ParticipationResponseDto) fromJsonToModel(
				result.getResponse().getContentAsString(), 
				ParticipationResponseDto.class);
	}

	private ParticipantResponseDto doFindParticipant(Long id) throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders
	            .get("/api/participant/" + id)
	            .servletPath("/api/participant/" + id)
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andReturn();

		if (result.getResponse().getContentAsString().isBlank()) {
			return null;
		}
		return (ParticipantResponseDto) fromJsonToModel(
				result.getResponse().getContentAsString(), 
				ParticipantResponseDto.class);
	}

	private ParticipantResponseDto doSaveParticipation(Long idParticipant, 
			BigDecimal participation, ResultMatcher resultMatcher) throws Exception {
		ParticipationRequestDto participationRequestDto = new ParticipationRequestDto(
				null, idParticipant, participation);

		MvcResult result = mvc.perform(MockMvcRequestBuilders
	            .post("/api/participation")
	            .servletPath("/api/participation")
	            .content(toJson(participationRequestDto))
	            .contentType(MediaType.APPLICATION_JSON)
	            )
				.andExpect(resultMatcher)
	            .andReturn();
		try {
			return (ParticipantResponseDto) fromJsonToModel(
					result.getResponse().getContentAsString(), 
					ParticipantResponseDto.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Test
	void testFindAll() throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders
	            .get("/api/participation")
	            .servletPath("/api/participation")
	            .contentType(MediaType.APPLICATION_JSON)
	            )
	            .andExpect(status().isOk())
	            .andReturn();

		ObjectMapper mapper = createObjectMapper();
    	List<ParticipationResponseDto> list = (List<ParticipationResponseDto>) mapper.readValue(
    			result.getResponse().getContentAsString(), 
				new TypeReference<List<ParticipationResponseDto>>() { });
		assertFalse(list.isEmpty());
		list.forEach(participation -> {
			assertInstanceOf(ParticipationResponseDto.class, participation);
		});
	}

	@Test
	void testFindParticipation() throws Exception {
		ParticipationResponseDto participation = doFindParticipation(1l);
		assertNotNull(participation);
		assertInstanceOf(ParticipationResponseDto.class, participation);
	}

	@Test
	void testFindParticipationNonExistent() throws Exception {
		ParticipationResponseDto participation = doFindParticipation(999999l);
		assertNull(participation);
	}

	@Test
	void testFindParticipant() throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders
	            .get("/api/participant/1")
	            .servletPath("/api/participant/1")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andReturn();

		ParticipantResponseDto participant = (ParticipantResponseDto) fromJsonToModel(
				result.getResponse().getContentAsString(), ParticipantResponseDto.class);
		assertNotNull(participant);
		assertInstanceOf(ParticipantResponseDto.class, participant);
	}

	@Test
	void testSaveParticipation() throws Exception {
		ParticipantResponseDto participantResponseDtoBefore = this.doFindParticipant(1l);
		assertNotNull(participantResponseDtoBefore);
		assertInstanceOf(ParticipantResponseDto.class, participantResponseDtoBefore);

		BigDecimal totalParticipationBefore = participantResponseDtoBefore.totalParticipation();

		ParticipantResponseDto participantResponseDtoAfter = this.doSaveParticipation(
				participantResponseDtoBefore.id(), BigDecimal.TEN, status().isCreated());

		assertNotNull(participantResponseDtoAfter);
		assertInstanceOf(ParticipantResponseDto.class, participantResponseDtoAfter);

		BigDecimal totalParticipationAfter = participantResponseDtoAfter.totalParticipation();

		assertTrue(totalParticipationBefore.compareTo(totalParticipationAfter) != 0);
	}

	@Test
	void testSaveParticipationValueZero() throws Exception {
		ParticipantResponseDto participant = this.doSaveParticipation(1l, BigDecimal.ZERO, status().isBadRequest());
		assertNull(participant);
	}

	@Test
	void testSaveParticipationValueNegative() throws Exception {
		ParticipantResponseDto participant = this.doSaveParticipation(1l, new BigDecimal(-1), status().isBadRequest());
		assertNull(participant);
	}

	@Test
	void testSaveParticipationParticipantNonExistent() throws Exception {
		ParticipantResponseDto participant = this.doSaveParticipation(9999l, 
				BigDecimal.TEN, status().isBadRequest());
		assertNull(participant);
	}
}
