package br.com.tcc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "ministra")
public class Ministra implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 6)
	private Integer mincodigo;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Professor minprocodigo;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Turma minturcodigo;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Disciplina mindiscodigo;

	@OneToMany(mappedBy = "ministra", fetch = FetchType.EAGER)
	private List<Frequencia> listFrequencia;

	@OneToMany(mappedBy = "ministra", fetch = FetchType.EAGER)
	private List<Nota> notaCollection;

	public Integer getMincodigo() {
		return mincodigo;
	}

	public void setMincodigo(Integer mincodigo) {
		this.mincodigo = mincodigo;
	}

	public Professor getMinprocodigo() {
		return minprocodigo;
	}

	public void setMinprocodigo(Professor minprocodigo) {
		this.minprocodigo = minprocodigo;
	}

	public Turma getMinturcodigo() {
		return minturcodigo;
	}

	public void setMinturcodigo(Turma minturcodigo) {
		this.minturcodigo = minturcodigo;
	}

	public Disciplina getMindiscodigo() {
		return mindiscodigo;
	}

	public void setMindiscodigo(Disciplina mindiscodigo) {
		this.mindiscodigo = mindiscodigo;
	}

	public List<Frequencia> getListFrequencia() {
		return listFrequencia;
	}

	public void setListFrequencia(List<Frequencia> listFrequencia) {
		this.listFrequencia = listFrequencia;
	}

	public List<Nota> getNotaCollection() {
		return notaCollection;
	}

	public void setNotaCollection(List<Nota> notaCollection) {
		this.notaCollection = notaCollection;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((mincodigo == null) ? 0 : mincodigo.hashCode());
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
		Ministra other = (Ministra) obj;
		if (mincodigo == null) {
			if (other.mincodigo != null)
				return false;
		} else if (!mincodigo.equals(other.mincodigo))
			return false;
		return true;
	}

}
