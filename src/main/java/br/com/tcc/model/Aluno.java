package br.com.tcc.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "alunos")
public class Aluno implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 6)
	private Integer alucodigo;

	@Column(length = 6, nullable = false)
	private Integer alumatricula;

	@Column(length = 80, nullable = false)
	private String alunome;

	@Column(length = 1, nullable = false)
	private int alusexo;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date aludtnas;

	@ManyToOne
	@JoinColumn(name = "alurescodigo", referencedColumnName = "rescodigo")
	private Responsavel alurescodigo;

	@OneToMany(mappedBy = "frealucodigo")
	private List<Frequencia> listFrequencia;

	@OneToMany(mappedBy = "aluno", fetch = FetchType.EAGER)
	private List<Matricula> listMatricula;

	@OneToMany(mappedBy = "notalucodigo", fetch = FetchType.EAGER)
	private List<Nota> listNota;

	public Integer getAlucodigo() {
		return alucodigo;
	}

	public void setAlucodigo(Integer alucodigo) {
		this.alucodigo = alucodigo;
	}

	public Integer getAlumatricula() {
		return alumatricula;
	}

	public void setAlumatricula(Integer alumatricula) {
		this.alumatricula = alumatricula;
	}

	public String getAlunome() {
		return alunome;
	}

	public void setAlunome(String alunome) {
		this.alunome = alunome;
	}

	public int getAlusexo() {
		return alusexo;
	}

	public void setAlusexo(int alusexo) {
		this.alusexo = alusexo;
	}

	public Date getAludtnas() {
		return aludtnas;
	}

	public void setAludtnas(Date aludtnas) {
		this.aludtnas = aludtnas;
	}

	public Responsavel getAlurescodigo() {
		return alurescodigo;
	}

	public void setAlurescodigo(Responsavel alurescodigo) {
		this.alurescodigo = alurescodigo;
	}

	public List<Frequencia> getListFrequencia() {
		return listFrequencia;
	}

	public void setListFrequencia(List<Frequencia> listFrequencia) {
		this.listFrequencia = listFrequencia;
	}

	public List<Matricula> getListMatricula() {
		return listMatricula;
	}

	public void setListMatricula(List<Matricula> listMatricula) {
		this.listMatricula = listMatricula;
	}

	public List<Nota> getListNota() {
		return listNota;
	}

	public void setListNota(List<Nota> listNota) {
		this.listNota = listNota;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((alucodigo == null) ? 0 : alucodigo.hashCode());
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
		Aluno other = (Aluno) obj;
		if (alucodigo == null) {
			if (other.alucodigo != null)
				return false;
		} else if (!alucodigo.equals(other.alucodigo))
			return false;
		return true;
	}

}
