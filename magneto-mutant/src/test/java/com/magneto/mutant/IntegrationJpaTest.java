package com.magneto.mutant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.magneto.mutant.dao.IHumanDao;
import com.magneto.mutant.entity.Human;

@DataJpaTest
public class IntegrationJpaTest {
	
	@Autowired
	private IHumanDao humanDao;
	
	@Test
	void testFindTotal() {
		double totalHuman=0D;
		double totalMutant=0D;
		Date dateNow = new Date();
		
		Human human = new Human(1L,"ATGCGA,CAGTGC,TTATTT,AGACGG,GCGTCA,TCACTG",false,"|AAAA|,-1,-1,-1|CCCC|,-1,-1,-1|GGGG|,-1,-1,-1|TTTT|,-1,-1,-1",dateNow);
		human = humanDao.save(human);
		
		human.setId(1L);
		human.setRegistration_date(dateNow);
		human.setDna("ATGCGA,CAGTGC,TTATTT,AGACGG,GCGTCA,TCACTG");
		human.setMutant(false);
		human.setResult("|AAAA|,-1,-1,-1|CCCC|,-1,-1,-1|GGGG|,-1,-1,-1|TTTT|,-1,-1,-1");
		
		human = humanDao.save(human);
		
		assertFalse(human.isMutant());
		assertEquals("ATGCGA,CAGTGC,TTATTT,AGACGG,GCGTCA,TCACTG", human.getDna());
		assertEquals("|AAAA|,-1,-1,-1|CCCC|,-1,-1,-1|GGGG|,-1,-1,-1|TTTT|,-1,-1,-1", human.getResult());
		assertEquals(0, human.getRegistration_date().compareTo(dateNow));
		
		totalHuman = humanDao.findTotal(false);
		totalMutant = humanDao.findTotal(true);
		
		assertEquals(1D, totalHuman);
		assertEquals(0D, totalMutant);
	}
	
	@Test
	void testFindHumanDna() {
		Date dateNow = new Date();
		String dna = "ATGCGA,CAGTGC,TTATTT,AGACGG,GCGTCA,TCACTG";
		
		Human human = new Human(1L,dna,false,"|AAAA|,-1,-1,-1|CCCC|,-1,-1,-1|GGGG|,-1,-1,-1|TTTT|,-1,-1,-1",dateNow);
		human = humanDao.save(human);
		
		human = humanDao.findHumanDna(dna).orElse(null);
		assertTrue(human!=null);
		if(human!=null) {
			assertEquals(dna, human.getDna());
			assertEquals("|AAAA|,-1,-1,-1|CCCC|,-1,-1,-1|GGGG|,-1,-1,-1|TTTT|,-1,-1,-1", human.getResult());
		}
	}
	
}
