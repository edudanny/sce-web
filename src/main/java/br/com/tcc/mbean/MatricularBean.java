package br.com.tcc.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Messages;

import br.com.tcc.dao.AlunoDAO;
import br.com.tcc.dao.AnoLetivoDAO;
import br.com.tcc.dao.EnderecoDAO;
import br.com.tcc.dao.MatriculaDAO;
import br.com.tcc.dao.ResponsavelDAO;
import br.com.tcc.dao.SecretariaDAO;
import br.com.tcc.dao.TelefoneDAO;
import br.com.tcc.dao.TurmaDAO;
import br.com.tcc.model.Aluno;
import br.com.tcc.model.AnoLetivo;
import br.com.tcc.model.Endereco;
import br.com.tcc.model.Matricula;
import br.com.tcc.model.Responsavel;
import br.com.tcc.model.Secretaria;
import br.com.tcc.model.Telefone;
import br.com.tcc.model.Turma;
import br.com.tcc.utils.Utilitario;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class MatricularBean implements Serializable {
	
	private Responsavel responsavel;
	private List<Responsavel> listResponsavel;
	private List<Responsavel> pesquisa;
	private List<Responsavel> listResponsavelAux;
	
	private Aluno aluno;
	private List<Aluno> listAluno;
	
	private Endereco endereco;
	
	private List<Telefone> listTelefones;
	
	private Secretaria secretaria;
	
	private List<AnoLetivo> listAnoLetivo;
	
	private Matricula matricula;
	
	private List<Turma> listTurma;
	private Turma turma;
	
	public String listar(){
		novo();
		try {
			ResponsavelDAO responsavelDAO = new ResponsavelDAO();
			listResponsavel = responsavelDAO.listar();
			pesquisa = listResponsavel;
			
			AnoLetivoDAO anoLetivoDAO = new AnoLetivoDAO();
			setListAnoLetivo(anoLetivoDAO.listar());
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os responsaveis");
			erro.printStackTrace();
		}
		return "/pages/matricula/listMatricula.xhtml?faces-redirect=true";
	}
	
	public void pesquisar(){
		if (!responsavel.getRescpf().equals("")){
			for (Responsavel resp : pesquisa) {
				if (resp.getRescpf().toLowerCase().contains(responsavel.getRescpf().toLowerCase())){
					listResponsavelAux.add(resp);
				}
			}
			listResponsavel = new ArrayList<Responsavel>();
			listResponsavel = listResponsavelAux;
			listResponsavelAux = new ArrayList<Responsavel>();
		} else {
			listResponsavel = pesquisa;
		}
	}
	
	public String salvar(){
		try {
			listAluno = new ArrayList<Aluno>();
			listResponsavel = new ArrayList<Responsavel>();
			listResponsavelAux = new ArrayList<Responsavel>();
			listTelefones = new ArrayList<Telefone>();
			
			listar();
			
			return "/pages/matricula/listMatricula.xhtml?faces-redirect=true";
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o professor");
			erro.printStackTrace();
		}
		return "";
	}
	
	public void salvarResponsavel() {
		ResponsavelDAO responsavelDAO = new ResponsavelDAO();
		responsavelDAO.salvar(responsavel);
		
		EnderecoDAO enderecoDAO = new EnderecoDAO();		
		endereco.setEndrescodigo(responsavel.getRescodigo());			
		enderecoDAO.salvar(endereco);
		
		TelefoneDAO telefoneDAO = new TelefoneDAO();
		
		for (Telefone tel : listTelefones) {
			tel.setTelrescodigo(responsavel);
			telefoneDAO.salvar(tel);
		}
		
		System.out.println("SALVO RESPONSAVEL");
	}
	
	public void salvarAluno(Aluno alu) {
		aluno = alu;
		Utilitario utilitario = new Utilitario();
		
		AlunoDAO alunoDAO = new AlunoDAO();		
		aluno.setAlumatricula(utilitario.gerarRandomMatAluno());
		aluno.setAlurescodigo(responsavel);		
		alunoDAO.salvar(aluno);
		
		SecretariaDAO secretariaDAO = new SecretariaDAO();
		secretaria = secretariaDAO.buscar(8);
		
		MatriculaDAO matriculaDAO = new MatriculaDAO();
		
		matricula.setAluno(aluno);
		matricula.setSecretaria(secretaria);
		matricula.setTurma(turma);
		matriculaDAO.salvar(matricula);
		
		System.out.println("SALVO ALUNO ");
	}
	
	public void addAluno() {
		aluno = new Aluno();
		listAluno.add(aluno);		
		
		System.out.println("ADICIONADO ALUNO");
	}
	
	public void excluir(Responsavel responsavel){
		try {
			ResponsavelDAO responsavelDAO = new ResponsavelDAO();
			responsavelDAO.excluir(responsavel);
			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir a habilitação");
			erro.printStackTrace();
		}
	}
	
	public void novo(){
		responsavel = new Responsavel();
		listResponsavel = new ArrayList<Responsavel>();
		pesquisa = new ArrayList<Responsavel>();
		listResponsavelAux = new ArrayList<Responsavel>();
		listAluno = new ArrayList<Aluno>();
		endereco = new Endereco();
		
		listTelefones = new ArrayList<Telefone>();
		
		listTelefones.add(new Telefone());
		listTelefones.add(new Telefone());
		
		turma = new Turma();
		
		listTurma = new ArrayList<Turma>();
		
		matricula = new Matricula();
	}
	
	public String novaMatricula(){
		responsavel = new Responsavel();
		
		addAluno();
		return "/pages/matricula/novaMatricula.xhtml?faces-redirect=true";
	}
	
	public String cancelarCadastro(){
		responsavel = null;
		listar();
		return "/pages/matricula/listMatricula.xhtml?faces-redirect=true";
	}
	
	public String editar(Responsavel resp){
		responsavel = resp;
		
		/*EnderecoDAO enderecoDAO = new EnderecoDAO();
		List<Endereco> ends = enderecoDAO.buscar(Endereco.QUERY_SEARCH_END_RES, responsavel.getRescodigo());
		endereco = ends.get(0);*/
		
		endereco = responsavel.getEndereco();
		
		TelefoneDAO telefoneDAO = new TelefoneDAO();
		listTelefones = telefoneDAO.buscar(Telefone.QUERY_SEARCH_TEL_RES, responsavel.getRescodigo());
		
		AlunoDAO alunoDAO = new AlunoDAO();
		listAluno = alunoDAO.buscar(Aluno.QUERY_SEARCH_ALU_RES, responsavel.getRescodigo());		
		
		return "/pages/matricula/novaMatricula.xhtml?faces-redirect=true";
	}
	
	public void listenerCarregarTurma() {
		TurmaDAO turmaDAO = new TurmaDAO();
		
		listTurma = turmaDAO.buscar(Turma.QUERY_SEARCH_TUR, turma.getAnoLetivo().getAnocodigo());
		
	}
	
	public Responsavel getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}
	public List<Responsavel> getListResponsavel() {
		return listResponsavel;
	}
	public void setListResponsavel(List<Responsavel> listResponsavel) {
		this.listResponsavel = listResponsavel;
	}

	public List<Aluno> getListAluno() {
		return listAluno;
	}

	public void setListAluno(List<Aluno> listAluno) {
		this.listAluno = listAluno;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Telefone> getListTelefones() {
		return listTelefones;
	}

	public void setListTelefones(List<Telefone> listTelefones) {
		this.listTelefones = listTelefones;
	}

	public List<AnoLetivo> getListAnoLetivo() {
		return listAnoLetivo;
	}

	public void setListAnoLetivo(List<AnoLetivo> listAnoLetivo) {
		this.listAnoLetivo = listAnoLetivo;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public List<Turma> getListTurma() {
		return listTurma;
	}

	public void setListTurma(List<Turma> listTurma) {
		this.listTurma = listTurma;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

}
