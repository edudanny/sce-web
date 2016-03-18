package br.com.tcc.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Messages;

import br.com.tcc.dao.AnoLetivoDAO;
import br.com.tcc.model.AnoLetivo;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class AnoLetivoBean implements Serializable{
	
	private AnoLetivo ano;
	private List<AnoLetivo> listAno;
	private List<AnoLetivo> pesquisa;
	private List<AnoLetivo> anoAux;	
	
	public String listar(){
		listAno = new ArrayList<AnoLetivo>();
		try {
			AnoLetivoDAO anoletivoDAO = new AnoLetivoDAO();
			listAno = anoletivoDAO.listar();
			pesquisa = listAno;
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Anos Letivos");
			erro.printStackTrace();
		}
		return "/pages/anoLetivo/listAnoLetivo.xhtml?faces-redirect=true";
		
	}
	
	public AnoLetivo getAno(){
		return ano;
	}
	
	public void setAno(AnoLetivo anoLetivo){
		this.ano = anoLetivo;
	}
	
	public List<AnoLetivo> getAnoLetivo(){
		return listAno;
	}
	
	public void setAnoLetivo(List<AnoLetivo> anoLetivo){
		this.listAno = anoLetivo;		
	}
	
}
