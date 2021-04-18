package com.magneto.tool;

import java.util.ArrayList;
import java.util.List;

public class MatrixConverter {
	private static int bigger;
	
	
	public static char[][] getMatrixDna(List<String> dna) {
		
		dna = fillLength(dna);
		char[][] matrix = buildMatrixDna(dna);
		return matrix;
	}
	public static List<String> fillLength(List<String> dna) {
		bigger =0;
		List<String> newDna = new ArrayList<String>();
		for(String name:dna){
			if(bigger<name.length())
				bigger =name.length(); 
		}
		for(String name:dna){
			newDna.add(String.format("%-"+ Integer.toString(bigger) +"s", name).replace(' ', 'X'));
		}
		
		return newDna;
	}
	
	public static char[][] buildMatrixDna(List<String> dna) {
		char matrix[][] = new char[dna.size()][bigger];
		int i=0;
		for(String name:dna){
			 matrix[i]= name.toCharArray();
			 i++;
		}

		return matrix;
	}		
	
	public static int[] search(char[][] matrix, String word) {
		int[] result = new int[]{-1, -1, -1};
	    for (int fila = 0; fila < matrix.length; fila++) {
	        for (int columna = 0; columna < matrix[fila].length; columna++) {

	            // busca derecha
	            if (columna + (word.length() - 1) < matrix[fila].length) {
	                boolean encontrada = true;

	                for (int letras = 0; letras < word.length(); letras++) {
	                    if (word.charAt(letras) != matrix[fila][columna + letras]) {
	                        encontrada = false;
	                        break;
	                    }
	                }
	                if (encontrada) {
	                    result[0] = fila;
	                    result[1] = columna;
	                    result[2] = 2;
	                }
	            }

	            // busca izquierda
	            if (columna - (word.length() - 1) >= 0) {
	                boolean encontrada = true;

	                for (int letras = 0; letras < word.length(); letras++) {
	                    if (word.charAt(letras) != matrix[fila][columna - letras]) {
	                        encontrada = false;
	                        break;
	                    }
	                }
	                if (encontrada) {
	                    result[0] = fila;
	                    result[1] = columna;
	                    result[2] = 6;
	                }
	            }

	            // busca abajo
	            if (fila + (word.length() - 1) < matrix.length) {
	                boolean encontrada = true;

	                for (int letras = 0; letras < word.length(); letras++) {
	                    if (word.charAt(letras) != matrix[fila + letras][columna]) {
	                        encontrada = false;
	                        break;
	                    }
	                }
	                if (encontrada) {
	                    result[0] = fila;
	                    result[1] = columna;
	                    result[2] = 4;
	                }
	            }

	            // busca arriba
	            if (fila - (word.length() - 1) >= 0) {
	                boolean encontrada = true;

	                for (int letras = 0; letras < word.length(); letras++) {
	                    if (word.charAt(letras) != matrix[fila - letras][columna]) {
	                        encontrada = false;
	                        break;
	                    }
	                }
	                if (encontrada) {
	                    result[0] = fila;
	                    result[1] = columna;
	                    result[2] = 0;
	                }
	            }

	            // diagonal arriba derecha
	            if ((fila - (word.length() - 1) >= 0) && (columna + (word.length() - 1) < matrix[fila].length)) {
	                boolean encontrada = true;

	                for (int letras = 0; letras < word.length(); letras++) {
	                    if (word.charAt(letras) != matrix[fila - letras][columna + letras]) {
	                        encontrada = false;
	                        break;
	                    }
	                }
	                if (encontrada) {
	                    result[0] = fila;
	                    result[1] = columna;
	                    result[2] = 1;
	                }
	            }
	            //

	            // diagonal arriba izquierda
	            if ((fila - (word.length() - 1) >= 0) && (columna - (word.length() - 1) >= 0)) {
	                boolean encontrada = true;

	                for (int letras = 0; letras < word.length(); letras++) {
	                    if (word.charAt(letras) != matrix[fila - letras][columna - letras]) {
	                        encontrada = false;
	                        break;
	                    }
	                }
	                if (encontrada) {
	                    result[0] = fila;
	                    result[1] = columna;
	                    result[2] = 7;
	                }
	            }

	            // diagonal abajo derecha
	            if ((fila + (word.length() - 1) < matrix.length) && (columna + (word.length() - 1) <= matrix[fila].length)) {
	                boolean encontrada = true;

	                for (int letras = 0; letras < word.length(); letras++) {
	                    if (word.charAt(letras) != matrix[fila + letras][columna + letras]) {
	                        encontrada = false;
	                        break;
	                    }
	                }
	                if (encontrada) {
	                    result[0] = fila;
	                    result[1] = columna;
	                    result[2] = 3;
	                }
	            }

	            // diagonal abajo izquierda
	            if ((fila + (word.length() - 1) < matrix.length) && (columna - (word.length() - 1) >= 0)) {
	                boolean encontrada = true;

	                for (int letras = 0; letras < word.length(); letras++) {
	                    if (word.charAt(letras) != matrix[fila + letras][columna - letras]) {
	                        encontrada = false;
	                        break;
	                    }
	                }
	                if (encontrada) {
	                    result[0] = fila;
	                    result[1] = columna;
	                    result[2] = 5;
	                }
	            }

	        }
	    }

	    return result;
	}	
}
