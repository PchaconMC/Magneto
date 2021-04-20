package com.magneto.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
/**
 * Esta Clase es una clase de utilidad que permite validar un ADN y busca en la secuencia
 * si un humano es mutanto o no.  
 * @author: Pedro Antonio Chacon Garnica
 * @version: 15/04/2021/A
 */
public class MatrixConverter {
	private static int bigger;
	/**
	 * Este metodo valida si en la composición base nitrogenada del ADN solo se encuentran las letras (A,T,C,G)
	 * @param dna este parametro contiene la secuencia de ADN a validar
	 * @return true si la secuencia de ADN es valida, false si no lo es
	 */
	public static boolean validDna(List<String> dna) {
		boolean isValid = false;
		String unionDna = dna.stream().collect(Collectors.joining("")).toUpperCase();
		
		Pattern pat = Pattern.compile("(A|C|G|T)+");
	     Matcher mat = pat.matcher(unionDna);                                                                           
	     if (mat.matches()) {
	    	 isValid = true;
	     } else {
	    	 isValid = false;
	     }		

		return isValid;
	}
	/**
	 * Este metodo permite transformar la secuencia de ADN que contiene valores String en una matriz de caracteres
	 * @param dna este parametro contiene la secuencia de ADN que se quiere transformar 
	 * @return retorna una matriz con la secuencia de caracteres correspondiente
	 */
	public static char[][] getMatrixDna(List<String> dna) {
		
		dna = fillLength(dna);
		char[][] matrix = buildMatrixDna(dna);
		return matrix;
	}
	/**
	 * Este metodo rellena con X la matriz para completar una secuencia de nxm, basandose en el valor mas grande de todas las cadenas de string que contiene el ADN 
	 * @param dna este parametro contiene la secuencia de ADN que se quiere calcular 
	 * @return la secuencia de ADN ajustada a un tamaño de nXm
	 */
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
	/**
	 * Este metodo construlle una matriz de caracteres basada en un vector que contiene valores string 
	 * @param dna  este parametro contiene la secuencia de ADN que se quiere transformar
	 * @return matriz de caracteres convertida
	 */
	public static char[][] buildMatrixDna(List<String> dna) {
		char matrix[][] = new char[dna.size()][bigger];
		int i=0;
		for(String name:dna){
			 matrix[i]= name.toCharArray();
			 i++;
		}

		return matrix;
	}		
	/**
	 * Este metodo nos permite buscar en todas las direcciones una secuencia continua de letras de forma oblicua, horizontal o vertical. En todas las direcciones
	 * @param matrix contiene la matriz donde deseamos hacer la busqueda
	 * @param word contiene la secuencia de letras que se desea buscar
	 * @return devolverá un array con tres enteros, el primero de ellos indicará la fila donde se encuentra la primera letra palabra, el segundo indicará la columna
	 * y el tercero indicará en qué dirección se ha encontrado la palabra (0: hacia arriba, 1: diagonal superior derecha, 2: derecha, 3: diagonal inferior derecha,
	 * 4: abajo, 5: diagonal inferior izquierda, 6: izquierda, 7: diagonal superior izquierda). Si una palabra no se encuentra devolverá el array {-1,-1,-1}.
	 */
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
