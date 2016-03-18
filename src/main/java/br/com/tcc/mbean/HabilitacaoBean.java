package br.com.tcc.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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
	
	public String listar(){
		novo();
		try {
			HabilitacaoDAO habilitacaoDAO = new HabilitacaoDAO();
			habilitacoes = habilitacaoDAO.listar();
			pesquisa = habilitacoes;
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as habilitações");
			erro.printStackTrace();
		}
		
		return "/pages/habilitacao/listHabilitacaoAux.xhtml?faces-redirect=true";
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
	
	public String salvar(){
		try {
			HabilitacaoDAO habilitacaoDAO = new HabilitacaoDAO();
			habilitacaoDAO.merge(habilitacao);
			listar();			
			return "/pages/habilitacao/listHabilitacaoAux.xhtml?faces-redirect=true";
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a habilitação");
			erro.printStackTrace();
		}
		return "";
	}
	
	public void excluir(Habilitacao habilitacao){
		try {
			HabilitacaoDAO habilitacaoDAO = new HabilitacaoDAO();			
			habilitacaoDAO.excluir(habilitacao);
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
		return "/pages/habilitacao/habilitacao.xhtml?faces-redirect=true";
	}
	
	public String cancelarCadastro(){
		habilitacao = null;
		listar();
		return "/pages/habilitacao/listHabilitacaoAux.xhtml?faces-redirect=true";
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


}
