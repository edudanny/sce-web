package br.com.tcc.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Table(name = "agenda")
@Entity
public class Agenda implements Serializable{
	
	@Column(length=11)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Integer agecodigo;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar agedthr;
	
	@Column(length=80)
	private String ageresnome;
	
	@Column(length=11)
	private Integer agerescpf;
	
	public Integer getAgecodigo(){
		return this.agecodigo;
	}
	
	public void setAgecodigo(Integer agecodigo){
		this.agecodigo = agecodigo;
	}
	
	public Calendar getAgedthr(){
		return this.agedthr;
	}
	
	public void setAgedthr(Calendar agedthr){
		this.agedthr = agedthr;
	}
	
	public String getAgeresnome(){
		return this.ageresnome;
	}
	
	public void setAgeresnome(String ageresnome){
		this.ageresnome = ageresnome;
	}
	
	public Integer getAgerescpf(){
		return this.agerescpf;
	}
	
	public void setAgerescpf(Integer agerescpf){
		this.agerescpf = agerescpf;
	}
}
