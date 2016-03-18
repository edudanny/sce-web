package br.com.tcc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="prohabilitacoes")
public class Habilitacao implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(length = 2)
	private Integer prohabcodigo;
	
	@Column(length=15,nullable=false, unique = true)
	private String prohabnome;
	
	@Column(length=255,nullable=false)
	private String prohabdesc;
	
	public Integer getProhabcodigo() {
		return this.prohabcodigo;
	}
	
	public void setProhabcodigo(Integer prohabcodigo) {
		this.prohabcodigo = prohabcodigo;
	}
	
	public String getProhabnome() {
		return this.prohabnome;
	}
	
	public void setProhabnome(String prohabnome) {
		this.prohabnome = prohabnome;
	}
	
	public String getProhabdesc(){
		return this.prohabdesc;
	}
	
	public void setProhabdesc(String prohabdesc){
		this.prohabdesc = prohabdesc;
	}
	
}
