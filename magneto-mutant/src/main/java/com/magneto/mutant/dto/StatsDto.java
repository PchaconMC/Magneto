package com.magneto.mutant.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatsDto implements Serializable {

	private double count_mutant_dna;
	private double count_human_dna;
	private double ratio=0.0;
	private static final long serialVersionUID = 1L;


}
