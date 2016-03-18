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
@Table(name="disciplinas")
public class Disciplina implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(length=2)
	private Integer discodigo;
	
	@Column(length=30, unique=true,nullable=false)
	private String disnome;
	
	public void setDiscodigo(Integer discodigo) {
		this.discodigo= discodigo;
	}
	
	public Integer getDiscodigo() {
		return this.discodigo;
	}
	
	public void setDisnome(String disnome){
		this.disnome=disnome;
	}
	public String getDisnome() {
		return this.disnome;
	}

}
