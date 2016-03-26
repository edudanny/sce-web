package br.com.tcc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "matricula")
public class Matricula implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 6)
    private Integer matcodigo;
	
    @ManyToOne
    @JoinColumn(nullable = false)
    private Aluno aluno;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Secretaria secretaria;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private AnoLetivo anoLetivo;

	public Integer getMatcodigo() {
		return matcodigo;
	}

	public void setMatcodigo(Integer matcodigo) {
		this.matcodigo = matcodigo;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
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
				+ ((matcodigo == null) ? 0 : matcodigo.hashCode());
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
		Matricula other = (Matricula) obj;
		if (matcodigo == null) {
			if (other.matcodigo != null)
				return false;
		} else if (!matcodigo.equals(other.matcodigo))
			return false;
		return true;
	}
    
    

}
