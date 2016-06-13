package br.com.tcc.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Messages;

import br.com.tcc.dao.DisciplinaDAO;
import br.com.tcc.model.Disciplina;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class DisciplinaBean implements Serializable {
	
	private Disciplina disciplina;
	private List<Disciplina> disciplinas;
	private List<Disciplina> pesquisa ;
	private List<Disciplina> disciplinaAux;

	public String listar(){
		novo();
		try {
			DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
			disciplinas = disciplinaDAO.listar();
			pesquisa = disciplinas;
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as disciplinas");
			erro.printStackTrace();
		}
		
		return "/pages/disciplina/listDisciplinas.xhtml?faces-redirect=true";
		
	}
	public void pesquisar(){
		
		if (!disciplina.getDisnome().equals("")){
			for (Disciplina habi : pesquisa) {
				if (habi.getDisnome().toLowerCase().contains(disciplina.getDisnome().toLowerCase())){
					disciplinaAux.add(habi);
				}
			}
			disciplinas = new ArrayList<Disciplina>();
			disciplinas = disciplinaAux;
			disciplinaAux = new ArrayList<Disciplina>();
		} else {
			disciplinas = pesquisa;
		}
	}
	public String salvar(){
			try {
				DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
				if (disciplina.getDiscodigo() != null) {
					disciplinaDAO.editar(disciplina);
				} else {
					disciplinaDAO.salvar(disciplina);
				}
				listar();
				
				return "/pages/disciplina/listDisciplinas.xhtml?faces-redirect=true";
				
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Ocorreu um erro ao tentar salvar a disciplina");
				erro.printStackTrace();
			}
			return "";
		}
	
	public void excluir(Disciplina disciplina){
		
		try {
			DisciplinaDAO disciplinaDAO = new DisciplinaDAO();			
			disciplinaDAO.editar(disciplina);
			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir a disciplina");
			erro.printStackTrace();
		}
	}
	public String editar(Disciplina dis){
		disciplina = dis;
		return "/pages/disciplina/disciplina.xhtml?faces-redirect=true";
	}
	
	public void novo(){
		disciplina = new Disciplina();
		pesquisa = new ArrayList<Disciplina>();
		disciplinaAux = new ArrayList<Disciplina>();
		disciplinas = new ArrayList<Disciplina>();
	}
	

	public String prepararCadastro(){
		disciplina = new Disciplina();
		return "/pages/disciplina/disciplina.xhtml?faces-redirect=true";
	}
	
	public String cancelarCadastro(){
		disciplina= null;
		listar();
		return "/pages/disciplina/listDisciplinas.xhtml?faces-redirect=true";
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplina) {
		this.disciplinas = disciplina;
	}
	
}
