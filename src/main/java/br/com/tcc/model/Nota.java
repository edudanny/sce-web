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
@Table(name = "nota")
public class Nota implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 20)
	private Long notcodigo;

	@Column(length = 3, nullable = false)
	private String notdescricao;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date notdata;

	@Column(name = "notvalor")
	private Double notvalor;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Aluno notalucodigo;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Ministra notmincodigo;

	public Long getNotcodigo() {
		return notcodigo;
	}

	public void setNotcodigo(Long notcodigo) {
		this.notcodigo = notcodigo;
	}

	public String getNotdescricao() {
		return notdescricao;
	}

	public void setNotdescricao(String notdescricao) {
		this.notdescricao = notdescricao;
	}

	public Date getNotdata() {
		return notdata;
	}

	public void setNotdata(Date notdata) {
		this.notdata = notdata;
	}

	public Double getNotvalor() {
		return notvalor;
	}

	public void setNotvalor(Double notvalor) {
		this.notvalor = notvalor;
	}

	public Aluno getNotalucodigo() {
		return notalucodigo;
	}

	public void setNotalucodigo(Aluno notalucodigo) {
		this.notalucodigo = notalucodigo;
	}

	public Ministra getNotmincodigo() {
		return notmincodigo;
	}

	public void setNotmincodigo(Ministra notmincodigo) {
		this.notmincodigo = notmincodigo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((notcodigo == null) ? 0 : notcodigo.hashCode());
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
		Nota other = (Nota) obj;
		if (notcodigo == null) {
			if (other.notcodigo != null)
				return false;
		} else if (!notcodigo.equals(other.notcodigo))
			return false;
		return true;
	}

}
