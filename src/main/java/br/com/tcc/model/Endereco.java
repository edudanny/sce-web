package br.com.tcc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "enderecos")
public class Endereco implements Serializable {

	@Id
	@Column(length = 6, nullable = false)
	private Integer endrescodigo;

	@Column(length = 8, nullable = false)
	private int endcep;

	@Column(length = 40, nullable = false)
	private String endbairro;

	@Column(length = 50, nullable = false)
	private String endrua;

	@Column(length = 5, nullable = true)
	private String endnumero;

	@Column(length = 100, nullable = true)
	private String endcomplemento;

	@OneToOne
	@JoinColumn(name = "endrescodigo", referencedColumnName = "rescodigo", insertable = false, updatable = false)
	private Responsavel responsaveis;

	public Integer getEndrescodigo() {
		return endrescodigo;
	}

	public void setEndrescodigo(Integer endrescodigo) {
		this.endrescodigo = endrescodigo;
	}

	public int getEndcep() {
		return endcep;
	}

	public void setEndcep(int endcep) {
		this.endcep = endcep;
	}

	public String getEndbairro() {
		return endbairro;
	}

	public void setEndbairro(String endbairro) {
		this.endbairro = endbairro;
	}

	public String getEndrua() {
		return endrua;
	}

	public void setEndrua(String endrua) {
		this.endrua = endrua;
	}

	public String getEndnumero() {
		return endnumero;
	}

	public void setEndnumero(String endnumero) {
		this.endnumero = endnumero;
	}

	public String getEndcomplemento() {
		return endcomplemento;
	}

	public void setEndcomplemento(String endcomplemento) {
		this.endcomplemento = endcomplemento;
	}

	public Responsavel getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(Responsavel responsaveis) {
		this.responsaveis = responsaveis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((endrescodigo == null) ? 0 : endrescodigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (endrescodigo == null) {
			if (other.endrescodigo != null)
				return false;
		} else if (!endrescodigo.equals(other.endrescodigo))
			return false;
		return true;
	}

}
