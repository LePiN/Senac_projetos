package com.r8store.model.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class JuridicalPerson extends SessionEntity {

	private static final long serialVersionUID = 2056340706921425642L;

	private String cnpj;
	
	private String inscricaoEstadual;

	public JuridicalPerson(String cnpj, String inscricaoEstadual) {
		super();
		this.cnpj = cnpj;
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public JuridicalPerson() {
		super();
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((inscricaoEstadual == null) ? 0 : inscricaoEstadual.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		JuridicalPerson other = (JuridicalPerson) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (inscricaoEstadual == null) {
			if (other.inscricaoEstadual != null)
				return false;
		} else if (!inscricaoEstadual.equals(other.inscricaoEstadual))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JuridicalPerson [cnpj=" + cnpj + ", inscricaoEstadual=" + inscricaoEstadual + "]";
	}
	
}
