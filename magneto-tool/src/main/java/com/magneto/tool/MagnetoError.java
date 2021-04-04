package com.magneto.tool;

import org.springframework.http.HttpStatus;

public class MagnetoError extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private HttpStatus status; 
	private String sesion_id;
	private String path;
	public String getSesion_id() {
		return sesion_id;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public String getPath() {
		return path;
	}
	public MagnetoError(String message, HttpStatus status, String sesion_id) {
		super(message);
		this.status = status;
		this.sesion_id = sesion_id;
	}
	public MagnetoError(String message, HttpStatus status, String sesion_id, String path) {
		super(message);
		this.status = status;
		this.sesion_id = sesion_id;
		this.path = path;
	}
}
