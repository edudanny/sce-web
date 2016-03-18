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
@Table(name="anos")
public class AnoLetivo implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(length=1)
	private Integer anocodigo;
	
	@Column(length=2, nullable=false, unique=true)
	private String anonome;
	
	public Integer getAnocodigo(){
		return this.anocodigo;
	}
	
	public void setAnocodigo(Integer anocodigo){
		this.anocodigo = anocodigo;
	}
	
	public String getAnonome(){
		return this.anonome;
	}
	
	public void setAnonome(String anonome){
		this.anonome = anonome;
	}

}
