package br.com.tcc.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Messages;

import br.com.tcc.dao.AnoLetivoDAO;
import br.com.tcc.dao.TurmaDAO;
import br.com.tcc.model.Aluno;
import br.com.tcc.model.AnoLetivo;
import br.com.tcc.model.Turma;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class TurmaBean implements Serializable {
	
	private Turma turma;
	private List<Turma> listTurma;
	private List<Turma> pesquisa;
	private List<Turma> listTurmaAux;
	
	//private List<Turma> listTurmaSalvar;
	
	private List<AnoLetivo> listAnoLetivo;
	private AnoLetivo anoLetivo = new AnoLetivo();
	
	//private List<Matricula> listMatricula = new ArrayList<Matricula>();
	
	private List<Aluno> listAluno = new ArrayList<Aluno>();
	
	public String listar(){
		novo();
		try {
			TurmaDAO turmaDAO = new TurmaDAO();
			listTurma = turmaDAO.listar();
			pesquisa = listTurma;
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as turmas");
			erro.printStackTrace();
		}
		
		return "/pages/turma/listTurma.xhtml?faces-redirect=true";
	}
	
	public void pesquisar(){
		if (!turma.getTurnome().equals("")){
			for (Turma tur : pesquisa) {
				if (tur.getTurnome().toLowerCase().contains(turma.getTurnome().toLowerCase())){
					listTurmaAux.add(tur);
				}
			}
			listTurma = new ArrayList<Turma>();
			listTurma = listTurmaAux;
			listTurmaAux = new ArrayList<Turma>();
		} else {
			listTurma = pesquisa;
		}
	}
	
	public String salvar(){
		//listTurmaSalvar = new ArrayList<Turma>();
		try {			
			TurmaDAO turmaDAO = new TurmaDAO();
			if (turma.getTurcodigo() != null) {
				turmaDAO.editar(turma);
			} else {
				turmaDAO.salvar(turma);
			}
			listar();			
			return "/pages/turma/listTurma.xhtml?faces-redirect=true";
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a turma");
			erro.printStackTrace();
		}
		return "";
	}
	
	public void excluir(Turma turma){
		try {
			TurmaDAO turmaDAO = new TurmaDAO();
			turmaDAO.editar(turma);
			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir a turma");
			erro.printStackTrace();
		}
	}
	
	public String editar(Turma tur){
		turma = tur;
		return "/pages/turma/turma.xhtml?faces-redirect=true";
	}
	
	public void novo(){
		turma = new Turma();
		listTurma = new ArrayList<Turma>();
		pesquisa = new ArrayList<Turma>();
		listTurmaAux = new ArrayList<Turma>();
	}
	
	public String prepararCadastro(){
		turma = new Turma();
		
		listAnoLetivo = new ArrayList<AnoLetivo>();
		AnoLetivoDAO anoLetivoDAO = new AnoLetivoDAO();
		listAnoLetivo = anoLetivoDAO.listar();
		turma.setAnoLetivo(listAnoLetivo.get(0));
		return "/pages/turma/turma.xhtml?faces-redirect=true";
	}
	
	public String cancelarCadastro(){
		turma = null;
		listar();
		return "/pages/turma/listTurma.xhtml?faces-redirect=true";
	}
	
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	public List<Turma> getListTurma() {
		return listTurma;
	}
	public void setListTurma(List<Turma> listTurma) {
		this.listTurma = listTurma;
	}

	public List<AnoLetivo> getListAnoLetivo() {
		return listAnoLetivo;
	}

	public void setListAnoLetivo(List<AnoLetivo> listAnoLetivo) {
		this.listAnoLetivo = listAnoLetivo;
	}

	public List<Aluno> getListAluno() {
		return listAluno;
	}

	public void setListAluno(List<Aluno> listAluno) {
		this.listAluno = listAluno;
	}

	public AnoLetivo getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(AnoLetivo anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

}
