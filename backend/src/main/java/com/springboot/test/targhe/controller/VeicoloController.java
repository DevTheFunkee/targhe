package com.springboot.test.targhe.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.test.targhe.customexception.VeicoloException;
import com.springboot.test.targhe.entity.Veicolo;
import com.springboot.test.targhe.service.VeicoloService;
import com.springboot.test.targhe.utils.TargheBeanUtils;

import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = { "${app.dev.frontend.local}" })
@RequestMapping(value = "/api/veicolo", produces = MediaType.APPLICATION_JSON_VALUE)
public class VeicoloController {

	@Autowired
	TargheBeanUtils targhebeanutils;
	
	@Autowired
	VeicoloService vservice;


	@GetMapping(value = "/{id}")
	@ApiResponse(code = 200, message = "Success")
	public ResponseEntity<?> getVeicoloById(@PathVariable(value = "id") Integer id) throws VeicoloException {
		try {
			return new ResponseEntity<>(vservice.getVeicoloById(id), HttpStatus.OK);
		} catch (VeicoloException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	@ApiResponse(code = 200, message = "Success")
	public ResponseEntity<?> getAllVeicolo() throws VeicoloException {
		try {
			return new ResponseEntity<>(vservice.getAllVeicolo(), HttpStatus.OK);
		} catch (VeicoloException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	@ApiResponse(code = 200, message = "Success")
	public ResponseEntity<?> insertVeicolo(@RequestBody Veicolo veicolo) throws VeicoloException, ParseException {
		try {
			return targhebeanutils.insertVeicoloSupport(veicolo);
		} catch (VeicoloException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

	}

	

	@PutMapping
	@ApiResponse(code = 200, message = "Success")
	public ResponseEntity<?> updateVeicolo(@RequestBody Veicolo veicolo) throws VeicoloException {
		try {
			return new ResponseEntity<>(vservice.updateVeicolo(veicolo), HttpStatus.OK);
		} catch (VeicoloException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{id}")
	@ApiResponse(code = 200, message = "Success")
	public ResponseEntity<?> deletVeicoloById(@PathVariable(value = "id") Integer id) throws VeicoloException {
		try {
			vservice.deleteVeicoloById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (VeicoloException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
