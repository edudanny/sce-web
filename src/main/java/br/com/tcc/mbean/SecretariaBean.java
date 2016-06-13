package br.com.tcc.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Messages;

import br.com.tcc.dao.SecretariaDAO;
import br.com.tcc.model.Secretaria;

@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class SecretariaBean implements Serializable{
	
	private Secretaria secretaria;
	private List<Secretaria> listSecretarias;
	private List<Secretaria> pesquisa;
	private List<Secretaria> listSecretariaAux;
	
	public String listar(){
		novo();
		try{
			SecretariaDAO secretariaDAO = new SecretariaDAO();
			listSecretarias = secretariaDAO.listar();
			pesquisa = listSecretarias;
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Anos Letivos");
			erro.printStackTrace();
		}
		return "/pages/secretaria/listSecretaria.xhtml?faces-redirect=true";
	}
	
	public void pesquisar(){
		if(!secretaria.getSecnome().equals("")){
			for(Secretaria sec : pesquisa){
				if(sec.getSecnome().toLowerCase().contains(secretaria.getSecnome().toLowerCase())){
					listSecretariaAux.add(sec);
				}
			}
			listSecretarias = new ArrayList<Secretaria>();
			listSecretarias = listSecretariaAux;
			listSecretariaAux = new ArrayList<Secretaria>();			
		}else{
			listSecretarias = pesquisa;
		}
	}
	
	public String salvar(){
		try {
			SecretariaDAO secretariaDAO = new SecretariaDAO();
			
			if (secretaria.getSeccodigo() != null) {
				secretariaDAO.editar(secretaria);
			} else {
				secretariaDAO.salvar(secretaria);
			}
			listar();	
			return "/pages/secretaria/listSecretaria.xhtml?faces-redirect=true";
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar informações da secretária");
			erro.printStackTrace();
		}
		return "";
	}
	
	public void excluir(Secretaria secretaria){
		try {
			SecretariaDAO secretariaDAO = new SecretariaDAO();
			secretaria.setSecstatus(false);
			secretariaDAO.editar(secretaria);
			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir a secretária");
			erro.printStackTrace();
		}
	}
	
	public String editar(Secretaria sec){
		secretaria = sec;
		return "/pages/secretaria/secretaria.xhtml?faces-redirect=true";
	}
	
	public void novo(){
		secretaria = new Secretaria();
		pesquisa = new ArrayList<Secretaria>();
		listSecretariaAux  = new ArrayList<Secretaria>();
	}

	public String prepararCadastro(){
		secretaria = new Secretaria();
		return "/pages/secretaria/secretaria.xhtml?faces-redirect=true";
	}
	
	public String cancelarCadastro(){
		secretaria = null;
		listar();
		return "/pages/secretaria/listSecretaria.xhtml?faces-redirect=true";
	}
	
	public Secretaria getSecretaria(){
		return secretaria;
	}
	
	public void setSecretaria(Secretaria secretaria){
		this.secretaria = secretaria;
	}
	
	public List<Secretaria> getListSecretarias(){
		return listSecretarias;
	}
	
	public void setListSecretaria(List<Secretaria> secretarias){
		this.listSecretarias = secretarias;
	}

}
