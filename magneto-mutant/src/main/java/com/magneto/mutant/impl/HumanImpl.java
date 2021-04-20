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
/**
 * Esta clase implementa los metodos necesarios para la investigación de mutantes
 * @author: Pedro Antonio Chacon Garnica
 * @version: 18/04/2021/A
 *
 */
@Service
public class HumanImpl implements IHumanService {

	@Autowired
	private IHumanDao humanDao;
	
	public HumanImpl(IHumanDao humanDao) {
		this.humanDao = humanDao;
	}
	/**
	 * Este Metodo nos permite reportar las estadísticas de las verificaciones de ADN (total de humanos, total de mutantes y su ratio)
	 * @return nos regresa objeto que contiene (total de humanos, total de mutantes y su ratio)
	 */
	@Override
	public StatsDto getStats() {
		StatsDto statsDto = new StatsDto();
		statsDto.setCount_mutant_dna(humanDao.findTotal(true));
		statsDto.setCount_human_dna(humanDao.findTotal(false));
		
		//Valida si el total de humanos es mayor a cero para evitar un error de desbordamiento
		if (statsDto.getCount_human_dna() > 0D) {
			statsDto.setRatio(statsDto.getCount_mutant_dna() / statsDto.getCount_human_dna());
		}
		
		return statsDto;
	}

	/**
	 * Nos permite validar si el ADN de un humano contiene la secuencia de un mutante
	 * @param humanDto contiene el ADN de un humano
	 * @return retorna true si un humano es un mutante
	 */
	@Override
	public boolean isMutant(HumanDto humanDto) {
		String[] sequences = {"AAAA","CCCC","GGGG","TTTT"};
		//Convierte en un string toda la secuencia DNA y la separa por "," para hacer la busqueda y validar que no se repita una secuendia ya validada anteriormente
		String dna = humanDto.getDna().stream().collect(Collectors.joining(",")).toUpperCase();
		Human human = humanDao.findHumanDna(dna).orElse(null);
		boolean isMutant = false;
		
		if(human==null) { //Si no se encuentra el humano registrado, inicia el proceso de validación y registro
			human = new Human();
			human.setDna(dna);
			human.setMutant(isMutant);
			char[][] matrixDna = MatrixConverter.getMatrixDna(humanDto.getDna()); //Convierte la secuencia de ADN en una Matrix y valida la secuencia de ADN
			StringBuilder s = new StringBuilder();
			
			for (String sequence : sequences) {
				int[] result = MatrixConverter.search(matrixDna, sequence);
				if(result[0] != -1 && result[1] != -1 && result[2] != -1 )
					human.setMutant(true);
				s.append("|" + sequence + "|," + String.valueOf(result[0]) + "," + String.valueOf(result[1]) + "," + String.valueOf(result[2]));
			}
			human.setResult(s.toString()); //Almacena el resultado de la secuencia
			human = humanDao.save(human);
		}
		isMutant = human.isMutant();
		return isMutant;
	}

	
}
