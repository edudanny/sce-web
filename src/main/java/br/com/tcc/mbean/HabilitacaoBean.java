package br.com.tcc.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.tcc.dao.HabilitacaoDAO;
import br.com.tcc.model.Habilitacao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class HabilitacaoBean implements Serializable {

	private Habilitacao habilitacao;
	private List<Habilitacao> habilitacoes;
	private List<Habilitacao> pesquisa = new ArrayList<Habilitacao>();
	private List<Habilitacao> habilitacaoAux = new ArrayList<Habilitacao>();
	
	@PostConstruct
	public void listar(){
		novo();
		try {
			HabilitacaoDAO habilitacaoDAO = new HabilitacaoDAO();
			habilitacoes = habilitacaoDAO.listar();
			pesquisa = habilitacoes;
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as habilitações");
			erro.printStackTrace();
		}
		
	}
	
	public void pesquisar(){
		if (!habilitacao.getNome().equals("")){
			for (Habilitacao habi : pesquisa) {
				if (habi.getNome().toLowerCase().contains(habilitacao.getNome().toLowerCase())){
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
	
	public void salvar(){
		try {
			novo();
			habilitacao.setDescricao("telescopio");
			habilitacao.setNome("lUA");
			
			HabilitacaoDAO habilitacaoDAO = new HabilitacaoDAO();
			habilitacaoDAO.merge(habilitacao);
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a habilitação");
			erro.printStackTrace();
		}
	}
	
	public void excluir(){
		try {
			HabilitacaoDAO habilitacaoDAO = new HabilitacaoDAO();
			novo();
			habilitacao = habilitacaoDAO.buscar(2L);
			
			
			habilitacaoDAO.excluir(habilitacao);
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a habilitação");
			erro.printStackTrace();
		}
	}
	
	public void novo(){
		habilitacao = new Habilitacao();	
	}

	public String prepararCadastro(){
		habilitacao = new Habilitacao();
		return "/pages/habilitacao.xhtml?faces-redirect=true";
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
