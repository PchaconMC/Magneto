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
	
	
	@Override
	public StatsDto getStats() {
		StatsDto statsDto = new StatsDto();
		statsDto.setCount_mutant_dna(humanDao.findTotal(true));
		statsDto.setCount_human_dna(humanDao.findTotal(false));
		
		if (statsDto.getCount_human_dna() > 0) {
			System.out.println(statsDto.getCount_mutant_dna() / statsDto.getCount_human_dna());
			System.out.println(statsDto.getCount_mutant_dna());
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
			human.setMutant(false);
			char[][] matrixDna = MatrixConverter.getMatrixDna(humanDto.getDna());
			StringBuilder s = new StringBuilder();
			
			for (String sequence : sequences) {
				int[] result = MatrixConverter.search(matrixDna, sequence);
				if(result[0] != -1 && result[1] != -1 && result[2] != -1 )
					human.setMutant(true);
				s.append("|" + sequence + "|," + String.valueOf(result[0]) + "," + String.valueOf(result[1]) + "," + String.valueOf(result[2]));
				System.out.print("Resultado para la secuencia <<" + sequence + ">> :: " + String.valueOf(result[0])
						+ "," + String.valueOf(result[1]) + "," + String.valueOf(result[2]));
				System.out.println();
			}
			human.setResult(s.toString());
			humanDao.save(human);
		}
				
		isMutant = human.isMutant();
		return isMutant;
	}

	
}
