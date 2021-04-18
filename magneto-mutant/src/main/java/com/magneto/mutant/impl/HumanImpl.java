package com.magneto.mutant.impl;


import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magneto.mutant.dao.IHumanDao;
import com.magneto.mutant.dto.HumanDto;
import com.magneto.mutant.dto.StatsDto;
import com.magneto.mutant.entity.Human;
import com.magneto.mutant.service.IHumanService;
import com.magneto.tool.MatrixConverter;
@Service
public class HumanImpl implements IHumanService {

	@Autowired
	private IHumanDao humanDao;
	
	public HumanImpl(IHumanDao humanDao) {
		this.humanDao = humanDao;
	}
	
	@Override
	public StatsDto getStats() {
		StatsDto statsDto = new StatsDto();
		statsDto.setCount_mutant_dna(humanDao.findTotal(true));
		statsDto.setCount_human_dna(humanDao.findTotal(false));
		System.out.println("Total Humanos:: " + statsDto.getCount_human_dna());
		System.out.println("Total Mutantes:: " + statsDto.getCount_mutant_dna());
		
		if (statsDto.getCount_human_dna() > 0D) {
			statsDto.setRatio(statsDto.getCount_mutant_dna() / statsDto.getCount_human_dna());
		}
		
		return statsDto;
	}

	@Override
	public boolean isMutant(HumanDto humanDto) {
		String[] sequences = {"AAAA","CCCC","GGGG","TTTT"};
		String dna = humanDto.getDna().stream().collect(Collectors.joining(",")).toUpperCase();
		Human human = humanDao.findHumanDna(dna).orElse(null);
		boolean isMutant = false;
		if(human==null) {
			human = new Human();
			human.setDna(dna);
			human.setMutant(isMutant);
			char[][] matrixDna = MatrixConverter.getMatrixDna(humanDto.getDna());
			StringBuilder s = new StringBuilder();
			
			for (String sequence : sequences) {
				int[] result = MatrixConverter.search(matrixDna, sequence);
				if(result[0] != -1 && result[1] != -1 && result[2] != -1 )
					human.setMutant(true);
				s.append("|" + sequence + "|," + String.valueOf(result[0]) + "," + String.valueOf(result[1]) + "," + String.valueOf(result[2]));
			}
			human.setResult(s.toString());
			human = humanDao.save(human);
			
			//System.out.println("Dna Humano:: " + human.getDna() + ", Es Mutante:: " + human.isMutant() + " << Result >> :: " + human.getResult());
		}
		isMutant = human.isMutant();
		return isMutant;
	}

	
}
