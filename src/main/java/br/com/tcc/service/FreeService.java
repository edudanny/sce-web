package br.com.tcc.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("free")
public class FreeService {
	
	@GET
	public String exibir() {
		return "Sistema de Frequencia";
	}

}
