package br.com.tcc.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

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
	private boolean salvo;
	private String msg;
	
	public String listar(){
		novo();
		try {
			if (salvo) {
				msg = "Habilitação salva com sucesso";
			}
			HabilitacaoDAO habilitacaoDAO = new HabilitacaoDAO();
			habilitacoes = habilitacaoDAO.listar();
			pesquisa = habilitacoes;
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as habilitações");
			erro.printStackTrace();
		}
		
		return "/pages/habilitacao/listHabilitacao.xhtml?faces-redirect=true";
	}
	
	public void pesquisar(){
		msg = new String();
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
			salvo = true;
			listar();
			
			return "/pages/habilitacao/listHabilitacao.xhtml?faces-redirect=true";
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a habilitação");
			erro.printStackTrace();
		}
		return "";
	}
	
	public void excluir(Habilitacao habilitacao){
		salvo = false;
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
