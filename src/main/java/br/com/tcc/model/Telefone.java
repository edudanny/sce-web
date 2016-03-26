package br.com.tcc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "telefones")
public class Telefone implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 11)
	private Integer telcodigo;

	@Column(length = 10, nullable = true)
	private String telnumero;

	@ManyToOne
	@JoinColumn(name = "telrescodigo", referencedColumnName = "rescodigo")
	private Responsavel telrescodigo;

	@ManyToOne
	@JoinColumn(name = "telprocodigo", referencedColumnName = "procodigo")
	private Professor telprocodigo;

	public Integer getTelcodigo() {
		return telcodigo;
	}

	public void setTelcodigo(Integer telcodigo) {
		this.telcodigo = telcodigo;
	}

	public String getTelnumero() {
		return telnumero;
	}

	public void setTelnumero(String telnumero) {
		this.telnumero = telnumero;
	}

	public Responsavel getTelrescodigo() {
		return telrescodigo;
	}

	public void setTelrescodigo(Responsavel telrescodigo) {
		this.telrescodigo = telrescodigo;
	}

	public Professor getTelprocodigo() {
		return telprocodigo;
	}

	public void setTelprocodigo(Professor telprocodigo) {
		this.telprocodigo = telprocodigo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((telcodigo == null) ? 0 : telcodigo.hashCode());
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
		Telefone other = (Telefone) obj;
		if (telcodigo == null) {
			if (other.telcodigo != null)
				return false;
		} else if (!telcodigo.equals(other.telcodigo))
			return false;
		return true;
	}

}
