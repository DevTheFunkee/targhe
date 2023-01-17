package com.springboot.test.targhe.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.springboot.test.targhe.customexception.VeicoloException;
import com.springboot.test.targhe.dto.DominioElement;



@Configuration
@ConfigurationProperties(prefix = "com.test.targhe")
@PropertySource("classpath:application.properties")
public class TipoSupport {
	public static final Logger logger = LoggerFactory.getLogger(TipoSupport.class);

	private List<DominioElement> tipo = new ArrayList<>();

	public DominioElement retrieveTipo(String name) throws VeicoloException {
		return this.tipo.stream()
				.filter(tipo -> StringUtils.equalsIgnoreCase(tipo.getName(), name)).findFirst()
				.orElseThrow(() -> new VeicoloException("Elemento non trovato"));
	}

	public boolean verifyTipo(DominioElement tipo) {

		return tipo == null || this.tipo.contains(tipo);
	}

	public boolean verifyTipo(String code) {

		DominioElement obj = new DominioElement();
		obj.setCode(code);
		return code == null || this.tipo.contains(obj);
	}

	public List<DominioElement> getTipo() {
		return tipo;
	}

	public void setTipo(List<DominioElement> completezza) {
		this.tipo = completezza;
	}

}
