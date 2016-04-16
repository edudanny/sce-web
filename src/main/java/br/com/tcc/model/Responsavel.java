package br.com.tcc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "responsaveis")
public class Responsavel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 6)
	private Integer rescodigo;

	@Column(length = 80, nullable = false)
	private String resnome;

	@Column(length = 15, nullable = false)
	private String rescpf;

	@Column(nullable = false)
	private String resrg;

	@Column(length = 10, nullable = false)
	private String resparentesco;

	@OneToMany(mappedBy = "alurescodigo")
	private List<Aluno> listAluno;

	@OneToMany(mappedBy = "telrescodigo")
	private List<Telefone> lisTelefone;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "responsaveis")
	private Endereco endereco;

	public Integer getRescodigo() {
		return rescodigo;
	}

	public void setRescodigo(Integer rescodigo) {
		this.rescodigo = rescodigo;
	}

	public String getResnome() {
		return resnome;
	}

	public void setResnome(String resnome) {
		this.resnome = resnome;
	}

	public String getRescpf() {
		return rescpf;
	}

	public void setRescpf(String rescpf) {
		this.rescpf = rescpf;
	}

	public String getResrg() {
		return resrg;
	}

	public void setResrg(String resrg) {
		this.resrg = resrg;
	}

	public String getResparentesco() {
		return resparentesco;
	}

	public void setResparentesco(String resparentesco) {
		this.resparentesco = resparentesco;
	}

	public List<Aluno> getListAluno() {
		return listAluno;
	}

	public void setListAluno(List<Aluno> listAluno) {
		this.listAluno = listAluno;
	}

	public List<Telefone> getLisTelefone() {
		return lisTelefone;
	}

	public void setLisTelefone(List<Telefone> lisTelefone) {
		this.lisTelefone = lisTelefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((rescodigo == null) ? 0 : rescodigo.hashCode());
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
		Responsavel other = (Responsavel) obj;
		if (rescodigo == null) {
			if (other.rescodigo != null)
				return false;
		} else if (!rescodigo.equals(other.rescodigo))
			return false;
		return true;
	}

}
