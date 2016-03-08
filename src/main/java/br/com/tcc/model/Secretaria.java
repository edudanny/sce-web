package br.com.tcc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@SuppressWarnings("serial")
@Table(name="secretaria")
public class Secretaria implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(length=2)
	private Integer seccodigo;
	
	@Column(length=80)
	private String secnome;
	
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

}
