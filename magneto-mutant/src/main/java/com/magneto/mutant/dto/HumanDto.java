package com.magneto.mutant.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HumanDto  implements Serializable {
	private List<String> dna;
	private static final long serialVersionUID = 1L;
}
