package br.com.tcc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="habilitacao")
public class Habilitacao extends GenericDomain {
	
	@Column(length=45,nullable=false)
	private String nome;
	
	@Column(length=45,nullable=false)
	private String descricao;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
