package br.com.tcc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="secretarias")
public class Secretaria implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(length=2)
	private Integer seccodigo;
	
	@Column(length=80)
	private String secnome;
	
	@OneToMany(mappedBy = "ageseccodigo")
    private List<Agenda> listAgenda;
    
    @OneToMany(mappedBy = "matseccodigo")
    private List<Matricula> listMatricula;
	
	public Integer getSeccodigo(){
		return this.seccodigo;
	}
	
	public void setSeccodigo(Integer seccodigo){
		this.seccodigo = seccodigo;
	}
	
	public String getSecnome(){
		return this.secnome;
	}
	
	public void setSecnome(String secnome){
		this.secnome = secnome;
	}

	public List<Agenda> getListAgenda() {
		return listAgenda;
	}

	public void setListAgenda(List<Agenda> listAgenda) {
		this.listAgenda = listAgenda;
	}

	public List<Matricula> getListMatricula() {
		return listMatricula;
	}

	public void setListMatricula(List<Matricula> listMatricula) {
		this.listMatricula = listMatricula;
	}

}
