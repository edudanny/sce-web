package br.com.tcc.model;

import java.io.Serializable;
import java.util.Calendar;

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
@Table(name = "agendas")
@Entity
public class Agenda implements Serializable {

	@Column(length = 11)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer agecodigo;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar agedthr;

	@Column(length = 80)
	private String agenome;

	@ManyToOne
	@JoinColumn(name = "ageseccodigo", referencedColumnName = "seccodigo")
	private Secretaria ageseccodigo;

	public Integer getAgecodigo() {
		return this.agecodigo;
	}

	public void setAgecodigo(Integer agecodigo) {
		this.agecodigo = agecodigo;
	}

	public Calendar getAgedthr() {
		return this.agedthr;
	}

	public void setAgedthr(Calendar agedthr) {
		this.agedthr = agedthr;
	}

	public String getAgenome() {
		return this.agenome;
	}

	public void setAgenome(String agenome) {
		this.agenome = agenome;
	}

	public Secretaria getAgeseccodigo() {
		return ageseccodigo;
	}

	public void setAgeseccodigo(Secretaria ageseccodigo) {
		this.ageseccodigo = ageseccodigo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((agecodigo == null) ? 0 : agecodigo.hashCode());
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
		Agenda other = (Agenda) obj;
		if (agecodigo == null) {
			if (other.agecodigo != null)
				return false;
		} else if (!agecodigo.equals(other.agecodigo))
			return false;
		return true;
	}

}


