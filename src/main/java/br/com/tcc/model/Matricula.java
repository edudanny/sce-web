package br.com.tcc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name = Matricula.QUERY_QTD_ALUNO_ANO, 
				query = "select m from Matricula m where m.turma.anoLetivo.anonome = :objeto ")
})

@SuppressWarnings("serial")
@Entity
@Table(name = "matricula")
public class Matricula implements Serializable {
	
	public static final String QUERY_QTD_ALUNO_ANO = "Matricula.QtdAlunoPorAno";
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 6)
    private Integer matcodigo;
	
    @ManyToOne
    @JoinColumn(name = "matalucodigo", referencedColumnName = "alucodigo")
    private Aluno aluno;
    
    @ManyToOne
    @JoinColumn(name = "matseccodigo", referencedColumnName = "seccodigo")
    private Secretaria secretaria;
    
    @ManyToOne
    @JoinColumn(name = "matturcodigo", referencedColumnName = "turcodigo")
    private Turma turma;

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

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
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
