package br.com.tcc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@NamedQueries({
	@NamedQuery(name = Habilitacao.QUERY_SEARCH_ATIVO_INATVO, 
				query = "select h from Habilitacao h where h.prohabstatus = :objeto "
						+ "or h.prohabstatus = :obj order by h.prohabcodigo asc ")
})

@SuppressWarnings("serial")
@Entity
@Table(name="prohabilitacoes")
public class Habilitacao implements Serializable {
	
	public static final String QUERY_SEARCH_ATIVO_INATVO = "Habilitacao.SearchAtivoInativo";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(length = 2)
	private Integer prohabcodigo;
	
	@Column(length=15,nullable=false, unique = true)
	private String prohabnome;
	
	@Column(length=255,nullable=false)
	private String prohabdesc;
	
	@Column(length = 1, nullable = false)
	private boolean prohabfundamental;
	
	@OneToMany(mappedBy = "habilitacao")
    private List<Professor> professores;
	
	@Column(nullable = false)
	private boolean prohabstatus;
	
	public Integer getProhabcodigo() {
		return this.prohabcodigo;
	}
	
	public void setProhabcodigo(Integer prohabcodigo) {
		this.prohabcodigo = prohabcodigo;
	}
	
	public String getProhabnome() {
		return this.prohabnome;
	}
	
	public void setProhabnome(String prohabnome) {
		this.prohabnome = prohabnome;
	}
	
	public String getProhabdesc(){
		return this.prohabdesc;
	}
	
	public void setProhabdesc(String prohabdesc){
		this.prohabdesc = prohabdesc;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public boolean getProhabfundamental() {
		return prohabfundamental;
	}

	public void setProhabfundamental(boolean prohabfundamental) {
		this.prohabfundamental = prohabfundamental;
	}
	
	public boolean isProhabstatus() {
		return prohabstatus;
	}

	public void setProhabstatus(boolean prohabstatus) {
		this.prohabstatus = prohabstatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((prohabcodigo == null) ? 0 : prohabcodigo.hashCode());
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
		Habilitacao other = (Habilitacao) obj;
		if (prohabcodigo == null) {
			if (other.prohabcodigo != null)
				return false;
		} else if (!prohabcodigo.equals(other.prohabcodigo))
			return false;
		return true;
	}
  
}
