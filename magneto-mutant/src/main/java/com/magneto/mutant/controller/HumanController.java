package com.magneto.mutant.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import com.magneto.mutant.dto.HumanDto;
import com.magneto.mutant.dto.StatsDto;
import com.magneto.mutant.service.IHumanService;
import com.magneto.tool.MagnetoError;
import com.magneto.tool.MatrixConverter;

import io.swagger.annotations.ApiOperation;

/**
 * Este Microservicio nos ofrece las operaciones necesarias para el
 * reclutamiento de mutantes para Magneto
 * 
 * @author: Pedro Antonio Chacon Garnica
 * @version: 15/04/2021/A
 *
 */
@RestController
@RequestMapping("")
public class HumanController {

	@Autowired
	private IHumanService humanService;

	
	@ApiOperation(value = "Permite identificar si un humano es mutante o no, basado en su secuencia de ADN",notes = "La composición base nitrogenada del ADN solo admite las letras (A,T,C,G).<br>Retorna <b>true</b> si el humano es mutante, solo se mantiene un registro por ADN")
	@PostMapping("mutant/")
	public ResponseEntity<?> isMutant(@RequestBody HumanDto humanDto) {
		Map<String, Object> response = new HashMap<>();
		boolean mutant = false;
		HttpStatus status;
		String mensaje;
		try {

			if (MatrixConverter.validDna(humanDto.getDna())) {//Valida que la secuencia de ADN sea correcta, solo admite las letras (A,T,C,G)
				mensaje = "Para este Humano, se ha validado su secuencia de ADN!!";
				mutant = humanService.isMutant(humanDto);

				if (mutant)
					status = HttpStatus.OK;
				else
					status = HttpStatus.FORBIDDEN;

			} else {
				mensaje = "La composición base nitrogenada del ADN solo admite las letras (A,T,C,G), no es posible validar este humano, verifique el ADN.";
				status = HttpStatus.FORBIDDEN;
			}

		} catch (DataAccessException e) {
			throw new MagnetoError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
					RequestContextHolder.currentRequestAttributes().getSessionId());
		} catch (Exception e) {
			throw new MagnetoError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
					RequestContextHolder.currentRequestAttributes().getSessionId());
		}
		response.put("mensaje", mensaje);
		response.put("mutant", mutant);
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	
	@ApiOperation(value = "Estadisticas para Magneto",notes = "Nos permite reportar las estadísticas de las verificaciones de ADN (total de humanos, total de mutantes y su ratio)")
	@GetMapping("stats")
	public StatsDto getStats() {
		return humanService.getStats();
	}

}
