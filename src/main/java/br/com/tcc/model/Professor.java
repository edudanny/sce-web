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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name = Professor.QUERY_LIST_PROF, 
			query = "select p from Professor p order by pronome")
})
@SuppressWarnings("serial")
@Entity
@Table(name = "professores")
public class Professor implements Serializable {
	
	public static final String QUERY_LIST_PROF = "Professor.ListProf";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 3)
	private Integer procodigo;

	@Column(length = 7, nullable = false, unique = true)
	private String promatricula;

	@Column(length = 80, nullable = true)
	private String pronome;

	@OneToMany(mappedBy = "minprocodigo")
	private List<Ministra> listMinistra;

	@OneToMany(mappedBy = "telprocodigo", orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Telefone> listTelefone;

	@ManyToOne
	@JoinColumn(name = "proprohabcodigo", referencedColumnName = "prohabcodigo")
	private Habilitacao habilitacao;

	public Integer getProcodigo() {
		return procodigo;
	}

	public void setProcodigo(Integer procodigo) {
		this.procodigo = procodigo;
	}

	public String getPromatricula() {
		return promatricula;
	}

	public void setPromatricula(String promatricula) {
		this.promatricula = promatricula;
	}

	public String getPronome() {
		return pronome;
	}

	public void setPronome(String pronome) {
		this.pronome = pronome;
	}

	public List<Ministra> getListMinistra() {
		return listMinistra;
	}

	public void setListMinistra(List<Ministra> listMinistra) {
		this.listMinistra = listMinistra;
	}

	public List<Telefone> getListTelefone() {
		return listTelefone;
	}

	public void setListTelefone(List<Telefone> listTelefone) {
		this.listTelefone = listTelefone;
	}

	public Habilitacao getHabilitacao() {
		return habilitacao;
	}

	public void setHabilitacao(Habilitacao habilitacao) {
		this.habilitacao = habilitacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((procodigo == null) ? 0 : procodigo.hashCode());
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
		Professor other = (Professor) obj;
		if (procodigo == null) {
			if (other.procodigo != null)
				return false;
		} else if (!procodigo.equals(other.procodigo))
			return false;
		return true;
	}

}
