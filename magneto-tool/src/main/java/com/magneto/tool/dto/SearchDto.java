package com.magneto.tool.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
/**
 * Esta es una clase generica para mapear las peticiones generales del proyecto de Magneto
 * @author: Pedro Antonio Chacon Garnica
 * @version: 15/04/2021/A
 */
@Getter
@Setter
public class SearchDto implements Serializable {
	
	private int page;
	private int item;
	private String filter;
	private boolean status;
	private static final long serialVersionUID = 1L;
}
