package com.magneto.mutant.controller;

import java.util.HashMap;
import java.util.List;
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

import com.magneto.mutant.dto.Stats;
import com.magneto.mutant.service.IHumanService;
import com.magneto.tool.MagnetoError;

@RestController
@RequestMapping("/")
public class HumanController {

	@Autowired
	private IHumanService humanService;

	@PostMapping("/")
	public ResponseEntity<?> isMutant(@RequestBody List<String> dna) {
		Map<String, Object> response = new HashMap<>();
		boolean mutant = false;
		HttpStatus status;
		try {
			mutant = humanService.isMutant(dna);
			if(mutant)
				status =HttpStatus.OK;
			else
				status =HttpStatus.FORBIDDEN;
			
		} catch (DataAccessException e) {
			throw new MagnetoError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
					RequestContextHolder.currentRequestAttributes().getSessionId());
		} catch (Exception e) {
			throw new MagnetoError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
					RequestContextHolder.currentRequestAttributes().getSessionId());
		}
		response.put("mensaje", "Para este Humano se ha validado su secuencia de ADN!!");
		response.put("mutant", mutant);
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@GetMapping("/stats")
	public Stats getStats() {
		return humanService.getStats();
	}

}
