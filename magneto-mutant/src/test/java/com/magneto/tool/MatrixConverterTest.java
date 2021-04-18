package com.magneto.tool;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.magneto.mutant.dto.Data;
import com.magneto.tool.MatrixConverter;

class MatrixConverterTest {

	@Test
	void testGetMatrixDna() {

		// test para un mutante
		char[][] matrixDna = MatrixConverter.getMatrixDna(Data.dna_mutant_01);
		int[] result = MatrixConverter.search(matrixDna, Data.sequences[0]);

		assertEquals(3, result[0]);
		assertEquals(3, result[1]);
		assertEquals(7, result[2]);

		result = MatrixConverter.search(matrixDna, Data.sequences[1]);

		assertEquals(4, result[0]);
		assertEquals(3, result[1]);
		assertEquals(6, result[2]);

		result = MatrixConverter.search(matrixDna, Data.sequences[2]);

		assertEquals(3, result[0]);
		assertEquals(4, result[1]);
		assertEquals(0, result[2]);

		result = MatrixConverter.search(matrixDna, Data.sequences[3]);

		assertEquals(-1, result[0]);
		assertEquals(-1, result[1]);
		assertEquals(-1, result[2]);

		// Test para un humano
		matrixDna = MatrixConverter.getMatrixDna(Data.dna_human_01);

		result = MatrixConverter.search(matrixDna, Data.sequences[0]);

		assertEquals(-1, result[0]);
		assertEquals(-1, result[1]);
		assertEquals(-1, result[2]);

		result = MatrixConverter.search(matrixDna, Data.sequences[1]);

		assertEquals(-1, result[0]);
		assertEquals(-1, result[1]);
		assertEquals(-1, result[2]);

		result = MatrixConverter.search(matrixDna, Data.sequences[2]);

		assertEquals(-1, result[0]);
		assertEquals(-1, result[1]);
		assertEquals(-1, result[2]);

		result = MatrixConverter.search(matrixDna, Data.sequences[3]);

		assertEquals(-1, result[0]);
		assertEquals(-1, result[1]);
		assertEquals(-1, result[2]);

		for (String sequence : Data.sequences) {
			result = MatrixConverter.search(matrixDna, sequence);
		}
	}   

}
