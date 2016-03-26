package br.com.tcc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="disciplinas")
public class Disciplina implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(length=2)
	private Integer discodigo;
	
	@Column(length=30, unique=true,nullable=false)
	private String disnome;
	
	@OneToMany(mappedBy = "mindiscodigo", fetch = FetchType.EAGER)
    private List<Ministra> listMinistra;
	
	public void setDiscodigo(Integer discodigo) {
		this.discodigo= discodigo;
	}
	
	public Integer getDiscodigo() {
		return this.discodigo;
	}
	
	public void setDisnome(String disnome){
		this.disnome=disnome;
	}
	public String getDisnome() {
		return this.disnome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((discodigo == null) ? 0 : discodigo.hashCode());
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
		Disciplina other = (Disciplina) obj;
		if (discodigo == null) {
			if (other.discodigo != null)
				return false;
		} else if (!discodigo.equals(other.discodigo))
			return false;
		return true;
	}

}
