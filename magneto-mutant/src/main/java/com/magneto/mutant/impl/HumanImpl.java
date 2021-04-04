package com.magneto.mutant.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.magneto.mutant.dao.IHumanDao;
import com.magneto.mutant.dto.Stats;
import com.magneto.mutant.service.IHumanService;

public class HumanImpl implements IHumanService {

	@Autowired
	private IHumanDao humanDao;
	
	
	@Override
	public Stats getStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMutant(List<String> dna) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
