package com.stefanini.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TelefonesKey implements Serializable {

	public TelefonesKey(){}
	
	public TelefonesKey(int ddd, int numero){
		this.ddd = ddd;
		this.numero = numero;
	}
	private static final long serialVersionUID = 1L;

	@Column(name = "numero", nullable = false)
	private int numero;
	@Column(name = "ddd", nullable = false)
	private int ddd;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ddd;
		result = prime * result + numero;
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
		TelefonesKey other = (TelefonesKey) obj;
		if (ddd != other.ddd)
			return false;
		if (numero != other.numero)
			return false;
		return true;
	}
}