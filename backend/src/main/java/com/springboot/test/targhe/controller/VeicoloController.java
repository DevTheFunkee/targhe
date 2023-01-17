package com.springboot.test.targhe.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.springboot.test.targhe.customexception.VeicoloException;
import com.springboot.test.targhe.dto.BolloResource;
import com.springboot.test.targhe.dto.DateRangeValidator;
import com.springboot.test.targhe.dto.DominioElement;
import com.springboot.test.targhe.dto.ReturnError;
import com.springboot.test.targhe.entity.Veicolo;
import com.springboot.test.targhe.service.VeicoloService;
import com.springboot.test.targhe.utils.TipoSupport;

import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = { "${app.dev.frontend.local}" })
@RequestMapping(value = "/api/veicolo", produces = MediaType.APPLICATION_JSON_VALUE)
public class VeicoloController {

	@Autowired
	VeicoloService vservice;

	@Autowired
	TipoSupport tipoSupport;

	@Value("${app.call.rest.bollo}")
	String bollopath;

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

		List<DominioElement> tipo = tipoSupport.getTipo();
		if (tipo.stream().anyMatch(i -> veicolo.getTipo().equalsIgnoreCase(i.getName()))) {
			veicolo.setTipo(tipoSupport.retrieveTipo(veicolo.getTipo()).getCode());
		} else {
			return new ResponseEntity<>("Tipo non valido, valori possibili: Auto, Moto, Rimorchio",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.set("Referer", "https://tassa-auto.sistemapiemonte.it/");
			headers.set("Host", "tassa-auto.sistemapiemonte.it");
			headers.set("Content-Type", "application/json");

			HttpEntity<String> httpEntity = new HttpEntity<>(headers);
			ResponseEntity<BolloResource> bollo = null;
			try {
				 bollo = restTemplate.exchange(
						bollopath + "?tipo_veicolo=" + veicolo.getTipo() + "&targa=" + veicolo.getTarga(),
						HttpMethod.GET, httpEntity, BolloResource.class);
			} catch (HttpClientErrorException ex) {
                  if(ex.getStatusCode().value() == 404) {
                	  return new ResponseEntity<>(new ReturnError(ex.getMessage()),
                			  HttpStatus.OK);
                  }
			} catch (HttpStatusCodeException ex) {
                  if(ex.getStatusCode().value() == 404) {
                	  return new ResponseEntity<>(new ReturnError(ex.getMessage()),
                			  HttpStatus.OK);
                  }
			}
			BolloResource body = bollo.getBody();
			Date date1 = new Date();
			if (body != null && body.getUltimoGiornoUtile() != null) {
				date1 = new SimpleDateFormat("dd/MM/yyyy").parse(body.getUltimoGiornoUtile());
			}
			LocalDate futureDate = LocalDate.now().plusMonths(3);
			ZoneId defaultZoneId = ZoneId.systemDefault();
			Date date = Date.from(futureDate.atStartOfDay(defaultZoneId).toInstant());
			DateRangeValidator checker = new DateRangeValidator(new Date(), date);

			if (!checker.isWithinRange(date1)) {
				Date now = Calendar.getInstance().getTime();
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String dateNow = dateFormat.format(now);
				return new ResponseEntity<>(new ReturnError("Controllo effettuato il " + dateNow + " con scadenza prevista per il "
						+ body.getUltimoGiornoUtile()), HttpStatus.OK);
			}
			return new ResponseEntity<>(vservice.insertVeicolo(veicolo), HttpStatus.OK);
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
