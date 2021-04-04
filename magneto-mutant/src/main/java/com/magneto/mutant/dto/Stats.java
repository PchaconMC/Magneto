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
public class Stats  implements Serializable{
	
	private int count_mutant_dna;
	private int count_human_dna;
	private double ratio;
	private static final long serialVersionUID = 1L;
}
