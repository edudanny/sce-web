package br.com.tcc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name = Turma.QUERY_SEARCH_TUR, 
				query = "select t from Turma t where t.anoLetivo.anocodigo = :objeto ")
})

@SuppressWarnings("serial")
@Entity
@Table(name = "turmas")
public class Turma implements Serializable {
	
	public static final String QUERY_SEARCH_TUR = "Turma.SearchTur";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 5)
	private Integer turcodigo;

	@Column(length = 8, nullable = false)
	private String turnome;

	@Column(length = 2, nullable = false)
	private Integer tursala;

	@OneToMany(mappedBy = "minturcodigo")
	private List<Ministra> listMinistra;

	@ManyToOne
	@JoinColumn(name = "turanocodigo", referencedColumnName = "anocodigo")
	private AnoLetivo anoLetivo;
	
	@OneToMany(mappedBy = "turma")
    private List<Matricula> listMatricula;
	
	@Column(nullable = false)
	private boolean turstatus;

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

	public Integer getTursala() {
		return tursala;
	}

	public void setTursala(Integer tursala) {
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

	public List<Matricula> getListMatricula() {
		return listMatricula;
	}

	public void setListMatricula(List<Matricula> listMatricula) {
		this.listMatricula = listMatricula;
	}

	public boolean isTurstatus() {
		return turstatus;
	}

	public void setTurstatus(boolean turstatus) {
		this.turstatus = turstatus;
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
