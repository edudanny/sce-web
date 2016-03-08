package br.com.tcc.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Messages;

import br.com.tcc.dao.AgendaDAO;
import br.com.tcc.model.Agenda;

@SuppressWarnings("serial")
@SessionScoped
@ManagedBean
public class AgendaBean implements Serializable{
	
	private Agenda agenda;
	private List<Agenda> listAgendas;
	private List<Agenda> pesquisa;
	private List<Agenda> listAgendaAux;
	
	@PostConstruct
	public void listar(){
		novo();
		try{
			AgendaDAO agendaDAO = new AgendaDAO();
			listAgendas = agendaDAO.listar();
			pesquisa = listAgendas;
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Agenda");
			erro.printStackTrace();
		}
	}
	
	public void novo(){
		agenda = new Agenda();
		listAgendas = new ArrayList<Agenda>();
		pesquisa = new ArrayList<Agenda>();
		listAgendaAux = new ArrayList<Agenda>();
	}
	
	public String cancelarCadastro(){
		return "/pages/agenda/listagenda.xhtml?faces-redirect=true";
	}
	
	public String salvar(){
		AgendaDAO agendaDAO = new AgendaDAO();
		agendaDAO.salvar(agenda);
		return "/pages/agenda/listagenda.xhtml?faces-redirect=true";
	}
	
	public void excluir(Agenda agenda){
		AgendaDAO agendaDAO = new AgendaDAO();
		agendaDAO.excluir(agenda);
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public List<Agenda> getListAgendas() {
		return listAgendas;
	}

	public void setListAgendas(List<Agenda> listAgendas) {
		this.listAgendas = listAgendas;
	}

}
