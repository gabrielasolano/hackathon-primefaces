package com.stefanini.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Telefones implements java.io.Serializable {

private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "idTipoTelefone")
	private Tipotelefone tipoTelefone = new Tipotelefone();
	
	@ManyToOne
	@JoinColumn(name = "cpfProprietario")
	private Proprietario proprietario = new Proprietario();
	
	@EmbeddedId
	private TelefonesKey telefoneKey = new TelefonesKey();
	
	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

	public Telefones() {
		this.telefoneKey = new TelefonesKey();
	}

	public TelefonesKey getTelefoneKey() {
		return telefoneKey;
	}

	public void setTelefoneKey(TelefonesKey telefoneKey) {
		this.telefoneKey = telefoneKey;
	}

	public Tipotelefone getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(Tipotelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}
}