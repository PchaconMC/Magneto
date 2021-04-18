package com.magneto.mutant.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.magneto.mutant.dto.Data;
import com.magneto.mutant.dto.HumanDto;
import com.magneto.mutant.dto.StatsDto;
import com.magneto.mutant.service.IHumanService;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class HumanImplTest {

	@Autowired
	private IHumanService humanService;	

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getStats() {
    	
    	boolean mutant = false;
    	mutant = humanService.isMutant(Data.humanDto_001); 
    	assertFalse(mutant);
    	mutant = humanService.isMutant(Data.humanDto_002);  
    	assertFalse(mutant);
    	mutant = humanService.isMutant(Data.humanDto_003);
    	assertFalse(mutant);
    	mutant = humanService.isMutant(Data.humanDto_004);
    	assertFalse(mutant);
    	mutant = humanService.isMutant(Data.mutantDto_001);
    	assertTrue(mutant);
    	
        StatsDto statsDto = humanService.getStats();

        //Test para el total de humanos 
       assertEquals(4D,statsDto.getCount_human_dna());
       //Test para el total de Mutantes 
       assertEquals(1D,statsDto.getCount_mutant_dna());
       //Test para el Ratio 
       assertEquals(0.25D,statsDto.getRatio());
    }

    @Test
    void isMutant() {
    	boolean mutant = false;
    	
    	HumanDto humanDto = new HumanDto();

    	// Test para humano
    	humanDto.setDna(Data.dna_human_01);
    	
    	mutant = humanService.isMutant(humanDto);
    	assertFalse(mutant);
    	
    	// Test para mutante
    	humanDto.setDna(Data.dna_mutant_01);
    	mutant = humanService.isMutant(humanDto);    	
    	
    	assertTrue(mutant);
    }
}