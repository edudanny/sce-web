package br.com.tcc.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Messages;

import br.com.tcc.dao.HabilitacaoDAO;
import br.com.tcc.dao.ProfessorDAO;
import br.com.tcc.model.Habilitacao;
import br.com.tcc.model.Professor;
import br.com.tcc.utils.Utilitario;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class ProfessorBean implements Serializable {
	
	private Professor professor;
	private List<Professor> listProfessor;
	private List<Professor> pesquisa;
	private List<Professor> listProfessorAux;
	
	private List<Habilitacao> listHabilitacao;
	
	public String listar(){
		novo();
		try {
			ProfessorDAO professorDAO = new ProfessorDAO();
			listProfessor = professorDAO.listar();
			pesquisa = listProfessor;
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os professores");
			erro.printStackTrace();
		}
		
		return "/pages/professor/listProfessor.xhtml?faces-redirect=true";
	}
	
	public void pesquisar(){
		if (!professor.getPronome().equals("")){
			for (Professor prof : pesquisa) {
				if (prof.getPronome().toLowerCase().contains(professor.getPronome().toLowerCase())){
					listProfessorAux.add(prof);
				}
			}
			listProfessor = new ArrayList<Professor>();
			listProfessor = listProfessorAux;
			listProfessorAux = new ArrayList<Professor>();
		} else {
			listProfessor = pesquisa;
		}
	}
	
	public String salvar(){
		try {
			ProfessorDAO professorDAO = new ProfessorDAO();
			
			if (professor.getPromatricula() == null) {
				Utilitario util = new Utilitario();
				professor.setPromatricula(util.geraRandomMat());
			}			
			professorDAO.merge(professor);
			listar();
			
			return "/pages/professor/listProfessor.xhtml?faces-redirect=true";
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o professor");
			erro.printStackTrace();
		}
		return "";
	}
	
	public void excluir(Professor professor){
		try {
			ProfessorDAO professorDAO = new ProfessorDAO();
			professorDAO.excluir(professor);
			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir a habilitação");
			erro.printStackTrace();
		}
	}
	
	public String editar(Professor prof){
		professor = prof;
		return "/pages/professor/professor.xhtml?faces-redirect=true";
	}
	
	public void novo(){
		professor = new Professor();
		listProfessor = new ArrayList<Professor>();
		pesquisa = new ArrayList<Professor>();
		listProfessorAux = new ArrayList<Professor>();
	}
	
	public String prepararCadastro(){
		professor = new Professor();
		
		listHabilitacao = new ArrayList<Habilitacao>();
		HabilitacaoDAO habilitacaoDAO = new HabilitacaoDAO();
		listHabilitacao = habilitacaoDAO.listar();
		
		return "/pages/professor/professor.xhtml?faces-redirect=true";
	}
	
	public String cancelarCadastro(){
		professor = null;
		listar();
		return "/pages/professor/listProfessor.xhtml?faces-redirect=true";
	}
	
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public List<Professor> getListProfessor() {
		return listProfessor;
	}
	public void setListProfessor(List<Professor> listProfessor) {
		this.listProfessor = listProfessor;
	}

	public List<Habilitacao> getListHabilitacao() {
		return listHabilitacao;
	}

	public void setListHabilitacao(List<Habilitacao> listHabilitacao) {
		this.listHabilitacao = listHabilitacao;
	}
	
}
