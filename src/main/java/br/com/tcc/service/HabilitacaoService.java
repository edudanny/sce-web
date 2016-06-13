package br.com.tcc.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.google.gson.Gson;

import br.com.tcc.dao.HabilitacaoDAO;
import br.com.tcc.model.Habilitacao;

@Path("habilitacao")
public class HabilitacaoService {
	
	@GET
	public String listar() {
		HabilitacaoDAO habilitacaoDAO = new HabilitacaoDAO();
		List<Habilitacao> listHabilitacao = habilitacaoDAO.listar();
		
		Gson gson = new Gson();
		String json = gson.toJson(listHabilitacao);
		
		return json;
	}

}
