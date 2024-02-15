package com.desafio.cubonetwork.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class BaseRestControllerTest {
	private ObjectMapper objectMapper = null;
	@Autowired
    protected MockMvc mvc;

	protected String toJson(final Object obj) {
		try {
			return createOrGetObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected ObjectMapper createOrGetObjectMapper() {
		if (this.objectMapper == null) {
			this.objectMapper = new ObjectMapper();
			this.objectMapper.registerModule(new JavaTimeModule());
		}
		return this.objectMapper;
	}

	protected Object fromJsonToModel(String jsonString, Class<?> valueType) throws Exception {
		return createOrGetObjectMapper().readValue(jsonString, valueType);
	}
}
