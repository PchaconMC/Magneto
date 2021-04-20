package com.magneto.mutant.controller;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magneto.mutant.dto.Data;
import com.magneto.mutant.service.IHumanService;

@WebMvcTest(HumanController.class)
@AutoConfigureMockMvc(addFilters=false)
public class HumanControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private IHumanService humanService;
	ObjectMapper objectMapper;
	
    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }
	
	@Test
	void testIsMutant() throws Exception, JsonProcessingException {
		
	    // Validacion para un mutante
		//Given
		
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Para este Humano, se ha validado su secuencia de ADN!!");
        response.put("mutant", true);
		
		when(humanService.isMutant(Data.mutantDto_001)).thenReturn(true);
		
//        // When
//        mvc.perform(post("/mutant/")
//                .contentType(MediaType.APPLICATION_JSON)
//        .content(objectMapper.writeValueAsString(Data.mutantDto_001)))
//        // Then
//        .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.mensaje").value("Para este Humano, se ha validado su secuencia de ADN!!"))
//                .andExpect(jsonPath("$.mutant").value(true))
//        .andExpect(content().json(objectMapper.writeValueAsString(response)));
        
  
	}
	@Test
	void testIsHuman() throws Exception, JsonProcessingException {
		
		// Validacion para un humano
		//Given
		
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Para este Humano, se ha validado su secuencia de ADN!!");
        response.put("mutant", false);
		
		when(humanService.isMutant(Data.humanDto_001)).thenReturn(false);
		
		
		
        // When
        mvc.perform(post("/mutant/")
                .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(Data.humanDto_001)))
        // Then
        .andExpect(status().isForbidden())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mensaje").value("Para este Humano, se ha validado su secuencia de ADN!!"))
                .andExpect(jsonPath("$.mutant").value(false))
        .andExpect(content().json(objectMapper.writeValueAsString(response)));
	}	
	@Test
	void testIsBatADN() throws Exception, JsonProcessingException {
		
        // Validacion para cuando el ADN esta mal formado
        //Given
		
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "La composición base nitrogenada del ADN solo admite las letras (A,T,C,G), no es posible validar este humano, verifique el ADN.");
        response.put("mutant", false);
		
		when(humanService.isMutant(Data.humanDto_error)).thenReturn(false);
		
        // When
        mvc.perform(post("/mutant/")
                .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(Data.humanDto_error)))
        // Then
        .andExpect(status().isForbidden())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mensaje").value("La composición base nitrogenada del ADN solo admite las letras (A,T,C,G), no es posible validar este humano, verifique el ADN."))
                .andExpect(jsonPath("$.mutant").value(false))
        .andExpect(content().json(objectMapper.writeValueAsString(response)));
		        
		
	}	
	@Test
	void testGetStats() throws Exception {
		//Given
		when(humanService.getStats()).thenReturn(Data.stats_001);
		//When
		mvc.perform(get("/stats/").contentType(MediaType.APPLICATION_JSON))
		//Then
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.count_mutant_dna").value("0.0"))
			.andExpect(jsonPath("$.count_human_dna").value("0.0"))
			.andExpect(jsonPath("$.ratio").value("0.0"));
		verify(humanService).getStats();	
		
	}
}
