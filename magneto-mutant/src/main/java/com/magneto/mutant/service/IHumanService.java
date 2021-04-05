package com.magneto.mutant.service;


import com.magneto.mutant.dto.HumanDto;
import com.magneto.mutant.dto.StatsDto;

public interface IHumanService {
	public StatsDto getStats();
	public boolean isMutant(HumanDto humanDto);
}
