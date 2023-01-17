package com.springboot.test.targhe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.test.targhe.customexception.VeicoloException;
import com.springboot.test.targhe.entity.Veicolo;
import com.springboot.test.targhe.repository.VeicoloRepository;

@Service
public class VeicoloService {
	@Autowired
	VeicoloRepository veicolorepo;

	public Veicolo getVeicoloById(Integer id) throws VeicoloException {
		try {
			Optional<Veicolo> veicolo = veicolorepo.findById(id);
			if (veicolo.isEmpty()) {
				throw new VeicoloException("record non trovato");
			}
			return veicolo.get();
		} catch (Exception e) {
			throw new VeicoloException("problema nel recupero del veicolo tramite id: " + id);
		}
	}

	public List<Veicolo> getAllVeicolo() throws VeicoloException {
		try {
			return veicolorepo.findAll();
		} catch (Exception e) {
			throw new VeicoloException("problema nel recupero dei veicoli");
		}
	}

	public void deleteVeicoloById(Integer id) throws VeicoloException {
		try {
			veicolorepo.deleteById(id);
			;
		} catch (Exception e) {
			throw new VeicoloException("problema durante la delete del veicolo: " + id);
		}
	}

	public Veicolo updateVeicolo(Veicolo v) throws VeicoloException {
		try {
			v = veicolorepo.saveAndFlush(v);
			return v;
		} catch (Exception e) {
			throw new VeicoloException("problema durante l'update del veicolo" + v.getTarga());
		}
	}

	public Veicolo insertVeicolo(Veicolo v) throws VeicoloException {
		try {
			v = veicolorepo.saveAndFlush(v);
			return v;
		} catch (Exception e) {
			throw new VeicoloException("problema durante l'inserimento del veicolo");
		}
	}
}
