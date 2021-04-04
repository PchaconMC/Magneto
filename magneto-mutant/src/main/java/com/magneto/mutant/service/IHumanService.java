package com.magneto.mutant.service;

import java.util.List;


import com.magneto.mutant.dto.Stats;

public interface IHumanService {
	public Stats getStats();
	public boolean isMutant(List<String> dna);
}
