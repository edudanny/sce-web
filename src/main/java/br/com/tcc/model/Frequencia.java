package br.com.tcc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "frequencia")
public class Frequencia implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 20)
	private Long frecodigo;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fredata;

	@Column(name = "frefrequencia")
	private int frefrequencia;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Aluno frealucodigo;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Ministra fremincodigo;

	public Long getFrecodigo() {
		return frecodigo;
	}

	public void setFrecodigo(Long frecodigo) {
		this.frecodigo = frecodigo;
	}

	public Date getFredata() {
		return fredata;
	}

	public void setFredata(Date fredata) {
		this.fredata = fredata;
	}

	public int getFrefrequencia() {
		return frefrequencia;
	}

	public void setFrefrequencia(int frefrequencia) {
		this.frefrequencia = frefrequencia;
	}

	public Aluno getFrealucodigo() {
		return frealucodigo;
	}

	public void setFrealucodigo(Aluno frealucodigo) {
		this.frealucodigo = frealucodigo;
	}

	public Ministra getFremincodigo() {
		return fremincodigo;
	}

	public void setFremincodigo(Ministra fremincodigo) {
		this.fremincodigo = fremincodigo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((frecodigo == null) ? 0 : frecodigo.hashCode());
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
		Frequencia other = (Frequencia) obj;
		if (frecodigo == null) {
			if (other.frecodigo != null)
				return false;
		} else if (!frecodigo.equals(other.frecodigo))
			return false;
		return true;
	}

}
