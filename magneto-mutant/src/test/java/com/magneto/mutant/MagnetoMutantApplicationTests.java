package com.magneto.mutant;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.magneto.mutant.dto.StatsDto;
import com.magneto.mutant.impl.HumanImpl;
import com.magneto.mutant.service.IHumanService;

@SpringBootTest
class MagnetoMutantApplicationTests {

	@Mock
	private HumanImpl humanImpl;
	
	//@InjectMocks
	//private IHumanService humanService;	
	
    @BeforeEach
    void setUp() {
    }
	
	@Test
	void testGetStats() {
		StatsDto statsDto = humanImpl.getStats();
		assertEquals(1.0, statsDto.getCount_mutant_dna());
		assertEquals(3.0, statsDto.getCount_human_dna());
	}

}
