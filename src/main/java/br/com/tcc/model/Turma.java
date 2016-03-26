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
@Table(name = "turmas")
public class Turma implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 5)
	private Integer turcodigo;

	@Column(length = 8, nullable = false)
	private String turnome;

	@Column(length = 2, nullable = false)
	private int tursala;

	@OneToMany(mappedBy = "minturcodigo", fetch = FetchType.EAGER)
	private List<Ministra> listMinistra;

	@ManyToOne
	@JoinColumn(nullable = false)
	private AnoLetivo anoLetivo;

	public Integer getTurcodigo() {
		return turcodigo;
	}

	public void setTurcodigo(Integer turcodigo) {
		this.turcodigo = turcodigo;
	}

	public String getTurnome() {
		return turnome;
	}

	public void setTurnome(String turnome) {
		this.turnome = turnome;
	}

	public int getTursala() {
		return tursala;
	}

	public void setTursala(int tursala) {
		this.tursala = tursala;
	}

	public List<Ministra> getListMinistra() {
		return listMinistra;
	}

	public void setListMinistra(List<Ministra> listMinistra) {
		this.listMinistra = listMinistra;
	}

	public AnoLetivo getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(AnoLetivo anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((turcodigo == null) ? 0 : turcodigo.hashCode());
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
		Turma other = (Turma) obj;
		if (turcodigo == null) {
			if (other.turcodigo != null)
				return false;
		} else if (!turcodigo.equals(other.turcodigo))
			return false;
		return true;
	}

}