package com.magneto.mutant.dto;

import java.util.Arrays;
import java.util.List;

public class Data {
	public static final StatsDto stats_001 = new StatsDto(10.0,5.0,2.0); 
	public static final StatsDto stats_002 = new StatsDto(10.0,20.0,0.5); 
	public static final StatsDto stats_003 = new StatsDto(25.0,5.0,5.0); 
	public static final StatsDto stats_004 = new StatsDto(40.0,100.0,0.4); 
	public static final List<String> dna_human_01 = Arrays.asList("ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTT");
	public static final HumanDto humanDto_001 = new HumanDto(dna_human_01); 
	public static final List<String> dna_human_02 = Arrays.asList("ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTA");
	public static final HumanDto humanDto_002 = new HumanDto(dna_human_02); 
	public static final List<String> dna_human_03 = Arrays.asList("ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG");
	public static final HumanDto humanDto_003 = new HumanDto(dna_human_03); 
	public static final List<String> dna_human_04 = Arrays.asList("ATGCG","CAGT","TTA","AG","G","TCACTA");
	public static final HumanDto humanDto_004 = new HumanDto(dna_human_04); 
	public static final List<String> dna_mutant_01 = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
	public static final HumanDto mutantDto_001 = new HumanDto(dna_mutant_01); 
	public static final String[] sequences = {"AAAA","CCCC","GGGG","TTTT"};

}
