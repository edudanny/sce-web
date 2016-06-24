package br.com.tcc.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Messages;

import br.com.tcc.dao.HabilitacaoDAO;
import br.com.tcc.model.Habilitacao;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class HabilitacaoBean implements Serializable {

	private Habilitacao habilitacao;
	private List<Habilitacao> habilitacoes;
	private List<Habilitacao> pesquisa;
	private List<Habilitacao> habilitacaoAux;
	
	private List<String> parametros;
	private String params[] = {"1"};
	
	private List<String> param = new ArrayList<String>();
	
	public String listar(){
		parametros = new ArrayList<String>();
		
		/*if (params.length == 1 && params[0] == null && params[0].equals("")) {
			parametros.add("1");
			parametros.add("1");
		} else if (params.length == 1 && params[0].equals("1")) {
			parametros.add("1");
			parametros.add("1");
		} else if (params.length == 0) {
			parametros.add("1");
			parametros.add("1");
		}*/
		
		if (param.size() == 0) {
			parametros.add("1");
			parametros.add("1");
		} else if (param.get(0).equals("0")) {
			parametros.add("0");
			parametros.add("1");
		}
		
		novo();
		try {
			HabilitacaoDAO habilitacaoDAO = new HabilitacaoDAO();
			habilitacoes = habilitacaoDAO.buscar(Habilitacao.QUERY_SEARCH_ATIVO_INATVO, parametros);
			pesquisa = habilitacoes;
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as habilitações");
			erro.printStackTrace();
		}
		
		return "/pages/habilitacao/listHabilitacao.xhtml?faces-redirect=true";
	}
	
	public void pesquisar(){
		if (!habilitacao.getProhabnome().equals("")){
			for (Habilitacao habi : pesquisa) {
				if (habi.getProhabnome().toLowerCase().contains(habilitacao.getProhabnome().toLowerCase())){
					habilitacaoAux.add(habi);
				}
			}
			habilitacoes = new ArrayList<Habilitacao>();
			habilitacoes = habilitacaoAux;
			habilitacaoAux = new ArrayList<Habilitacao>();
		} else {
			habilitacoes = pesquisa;
		}
	}
	
	public void pesquisarPorStatus(){
		parametros = new ArrayList<String>();
		HabilitacaoDAO habilitacaoDAO = new HabilitacaoDAO();
		
		for (int i = 0; i < param.size(); i++) {
			System.out.println("CHECKBOX = " + param.get(i));
		}
		
		if (param.get(0).equals("0")) {
			parametros.add("0");
			parametros.add("1");			
		} else if (param.get(0).equals("1")) {
			parametros.add("1");
			parametros.add("1");
		} else if (param.size() == 0) {
			parametros.add("1");
			parametros.add("1");
		}
		
		try {
			habilitacoes = habilitacaoDAO.buscar(Habilitacao.QUERY_SEARCH_ATIVO_INATVO, parametros);
			pesquisa = habilitacoes;
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as habilitações");
			erro.printStackTrace();
		}
		
		
		
		/*if (params.length == 1 && params[0].equals("1")) {
			params = new String[2];
			params[0] = "1";
			params[1] = "1";
		}*/
		
		/*if (params.length == 1 && params[0].equals("0")) {
			params = new String[2];
			params[0] = "0";
			params[1] = "1";
		} else if (params.length == 0) {
			params = new String[2];
			params[0] = "1";
			params[1] = "1";
		}*/
		
		/*if (params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				parametros.add(params[i]);
			}
			try {
				HabilitacaoDAO habilitacaoDAO = new HabilitacaoDAO();
				habilitacoes = habilitacaoDAO.buscar(Habilitacao.QUERY_SEARCH_ATIVO_INATVO, parametros);
				pesquisa = habilitacoes;
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Ocorreu um erro ao tentar listar as habilitações");
				erro.printStackTrace();
			}
		} else {
			habilitacoes = new ArrayList<Habilitacao>();
		}*/
		
	}
	
	public String salvar(){
		try {
			HabilitacaoDAO habilitacaoDAO = new HabilitacaoDAO();
			if (habilitacao.getProhabcodigo() != null) {
				habilitacaoDAO.editar(habilitacao);
			} else {
				habilitacaoDAO.salvar(habilitacao);
			}
			listar();
			
			return "/pages/habilitacao/listHabilitacao.xhtml?faces-redirect=true";
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a habilitação");
			erro.printStackTrace();
		}
		return "";
	}
	
	public void excluir(Habilitacao habilitacao){
		try {
			HabilitacaoDAO habilitacaoDAO = new HabilitacaoDAO();
			habilitacao.setProhabstatus(false);
			habilitacaoDAO.editar(habilitacao);
			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir a habilitação");
			erro.printStackTrace();
		}
	}
	
	public String editar(Habilitacao hab){
		habilitacao = hab;
		return "/pages/habilitacao/habilitacao.xhtml?faces-redirect=true";
	}
	
	public void novo(){
		habilitacao = new Habilitacao();
		pesquisa = new ArrayList<Habilitacao>();
		habilitacaoAux = new ArrayList<Habilitacao>();
		habilitacoes = new ArrayList<Habilitacao>();
	}

	public String prepararCadastro(){
		habilitacao = new Habilitacao();
		habilitacao.setProhabfundamental(true);
		habilitacao.setProhabstatus(true);
		return "/pages/habilitacao/habilitacao.xhtml?faces-redirect=true";
	}
	
	public String cancelarCadastro(){
		habilitacao = null;
		listar();
		return "/pages/habilitacao/listHabilitacao.xhtml?faces-redirect=true";
	}
	
	public Habilitacao getHabilitacao() {
		return habilitacao;
	}

	public void setHabilitacao(Habilitacao habilitacao) {
		this.habilitacao = habilitacao;
	}

	public List<Habilitacao> getHabilitacoes() {
		return habilitacoes;
	}

	public void setHabilitacoes(List<Habilitacao> habilitacoes) {
		this.habilitacoes = habilitacoes;
	}

	public String[] getParams() {
/*		params = new String[1];
		String[] frase = {"1"}; */
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public List<String> getParam() {
		return param;
	}

	public void setParam(List<String> param) {
		this.param = param;
	}

}
