package br.com.tcc.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Messages;

import br.com.tcc.dao.ResponsavelDAO;
import br.com.tcc.model.Responsavel;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class MatricularBean implements Serializable {
	
	private Responsavel responsavel;
	private List<Responsavel> listResponsavel;
	private List<Responsavel> pesquisa;
	private List<Responsavel> listResponsavelAux;
	
	public String listar(){
		novo();
		/*try {
			ResponsavelDAO responsavelDAO = new ResponsavelDAO();
			listResponsavel = responsavelDAO.listar();
			pesquisa = listResponsavel;
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os responsaveis");
			erro.printStackTrace();
		}*/
		return "/pages/matricula/listMatricula.xhtml?faces-redirect=true";
	}
	
	public void pesquisar(){
		if (!responsavel.getResnome().equals("")){
			for (Responsavel resp : pesquisa) {
				if (resp.getResnome().toLowerCase().contains(responsavel.getResnome().toLowerCase())){
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
			responsavelDAO.merge(responsavel);
			listar();
			
			return "/pages/matricula/listMatricula.xhtml?faces-redirect=true";
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o professor");
			erro.printStackTrace();
		}
		return "";
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
	}
	
	public String prepararCadastro(){
		responsavel = new Responsavel();		
		return "/pages/matricula/matricula.xhtml?faces-redirect=true";
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

}
