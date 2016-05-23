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
@Table(name = "usuarios")
public class Usuario implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 11)
	private Integer usucodigo;
	
	@Column(length = 11, nullable = true)
	private String usulogin;
	
	@Column(length = 32, nullable = true)
	private String ususenha;
	
	@ManyToOne
	@JoinColumn(name = "usurescodigo", referencedColumnName = "rescodigo")
	private Responsavel usurescodigo;

	@ManyToOne
	@JoinColumn(name = "usuprocodigo", referencedColumnName = "procodigo")
	private Professor usuprocodigo;

	public Integer getUsucodigo() {
		return usucodigo;
	}

	public void setUsucodigo(Integer usucodigo) {
		this.usucodigo = usucodigo;
	}

	public String getUsulogin() {
		return usulogin;
	}

	public void setUsulogin(String usulogin) {
		this.usulogin = usulogin;
	}

	public String getUsusenha() {
		return ususenha;
	}

	public void setUsusenha(String ususenha) {
		this.ususenha = ususenha;
	}

	public Responsavel getUsurescodigo() {
		return usurescodigo;
	}

	public void setUsurescodigo(Responsavel usurescodigo) {
		this.usurescodigo = usurescodigo;
	}

	public Professor getUsuprocodigo() {
		return usuprocodigo;
	}

	public void setUsuprocodigo(Professor usuprocodigo) {
		this.usuprocodigo = usuprocodigo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((usucodigo == null) ? 0 : usucodigo.hashCode());
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
		Usuario other = (Usuario) obj;
		if (usucodigo == null) {
			if (other.usucodigo != null)
				return false;
		} else if (!usucodigo.equals(other.usucodigo))
			return false;
		return true;
	}

}
