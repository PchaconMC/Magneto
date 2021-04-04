package com.magneto.tool.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDto implements Serializable {
	
	private int page;
	private int item;
	private String filter;
	private boolean status;
	private static final long serialVersionUID = 1L;
}
