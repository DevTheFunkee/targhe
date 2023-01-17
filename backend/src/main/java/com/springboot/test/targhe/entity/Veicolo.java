package com.springboot.test.targhe.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "VEICOLO")
public class Veicolo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "veicolo_gen")
	@SequenceGenerator(name = "veicolo_gen", sequenceName = "veicolo_seq", allocationSize = 1)
	private Integer id;

	@Column(name = "TARGA")
	private String targa;

	@Column(name = "TIPO")
	private String tipo;

	@Column(name = "COD_FISC")
	private String proprietario;

	@Column(name = "DATA_IMMATRICOLAZIONE")
	private Date dataImmatricolazione;

	@Column(name = "CILINDRATA")
	private String cilindrata;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getDataImmatricolazione() {
		return dataImmatricolazione;
	}

	public void setDataImmatricolazione(Date dataImmatricolazione) {
		this.dataImmatricolazione = dataImmatricolazione;
	}

	public String getCilindrata() {
		return cilindrata;
	}

	public void setCilindrata(String cilindrata) {
		this.cilindrata = cilindrata;
	}

	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}

}
