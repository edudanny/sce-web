package br.com.tcc.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Messages;

import br.com.tcc.dao.ResponsavelDAO;
import br.com.tcc.model.Aluno;
import br.com.tcc.model.Endereco;
import br.com.tcc.model.Responsavel;
import br.com.tcc.model.Telefone;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class MatricularBean implements Serializable {
	
	private Responsavel responsavel;
	private List<Responsavel> listResponsavel;
	private List<Responsavel> pesquisa;
	private List<Responsavel> listResponsavelAux;
	private List<Responsavel> listNovoResponsavel;
	
	private Aluno aluno;
	private List<Aluno> listAluno;
	private List<Aluno> listNovoAluno;
	
	private Endereco endereco;
	private List<Telefone> listTelefone;
	
	public String listar(){
		novo();
		try {
			ResponsavelDAO responsavelDAO = new ResponsavelDAO();
			listResponsavel = responsavelDAO.listar();
			pesquisa = listResponsavel;
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
			ResponsavelDAO responsavelDAO = new ResponsavelDAO();
			responsavelDAO.salvar(responsavel);
			listar();
			
			return "/pages/matricula/listMatricula.xhtml?faces-redirect=true";
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o professor");
			erro.printStackTrace();
		}
		return "";
	}
	
	public void salvarResponsavel() {
		responsavel.setEndereco(endereco);
		listNovoResponsavel.add(responsavel);
		System.out.println("SALVO RESPONSAVEL");
	}
	
	public void salvarAluno(Aluno alu) {
		listNovoAluno.add(alu);
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
	}
	
	public String novaMatricula(){
		responsavel = new Responsavel();
		
		listNovoAluno = new ArrayList<Aluno>();
		listNovoResponsavel = new ArrayList<Responsavel>();
		
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
		return "/pages/responsavel/responsavel.xhtml?faces-redirect=true";
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

	public List<Telefone> getListTelefone() {
		return listTelefone;
	}

	public void setListTelefone(List<Telefone> listTelefone) {
		this.listTelefone = listTelefone;
	}

}
