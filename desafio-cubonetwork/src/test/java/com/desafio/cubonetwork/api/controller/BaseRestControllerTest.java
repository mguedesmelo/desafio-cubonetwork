package com.desafio.cubonetwork.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class BaseRestControllerTest {
	@Autowired
    protected MockMvc mvc;

	protected String toJson(final Object obj) {
		try {
			return createObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected ObjectMapper createObjectMapper() {
		ObjectMapper toReturn = new ObjectMapper();
    	toReturn.registerModule(new JavaTimeModule());
		return toReturn;
	}

	protected Object fromJsonToModel(String jsonString, Class<?> valueType) throws Exception {
		return createObjectMapper().readValue(jsonString, valueType);
	}
}
