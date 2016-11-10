package com.stefanini.model;
// Generated 07/11/2016 12:05:08 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Infracoes generated by hbm2java
 */
@Entity
@Table(name = "infracoes", catalog = "hackaton")
public class Infracoes implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idInfracao", unique = true, nullable = false)	
	private Integer idInfracao;
	
	@ManyToOne()
	@JoinColumn(name = "idAgente")
	private Agente agente = new Agente();

	@ManyToOne()
	@JoinColumn(name = "idTipoInfracao")
	private Tipoinfracao tipoInfracao = new Tipoinfracao();
	
	@ManyToOne
	@JoinColumn(name = "idLocalInfracao")
	private Localinfracao localInfracao = new Localinfracao();
/*	@Column(name = "idLocalInfracao")
	private int idLocalInfracao;
*/
/*	public int getIdLocalInfracao() {
		return idLocalInfracao;
	}

	public void setIdLocalInfracao(int idLocalInfracao) {
		this.idLocalInfracao = idLocalInfracao;
	}*/

	@Column(name = "placa", nullable = false, length = 7)
	private String placa;
	
	@Column(name = "velocidade")
	private Integer velocidade;

	public Infracoes(){
		
	}

	public Infracoes(Agente agente, Tipoinfracao tipoInfracao, Localinfracao localInfracao, Integer idInfracao,
			String placa, Integer velocidade) {
		this.agente = agente;
		this.tipoInfracao = tipoInfracao;
		this.localInfracao = localInfracao;
		this.idInfracao = idInfracao;
		this.placa = placa;
		this.velocidade = velocidade;
	}

	public Agente getAgente() {
		return agente;
	}

	public void setAgente(Agente agente) {
		this.agente = agente;
	}

	public Tipoinfracao getTipoInfracao() {
		return tipoInfracao;
	}

	public void setTipoInfracao(Tipoinfracao tipoInfracao) {
		this.tipoInfracao = tipoInfracao;
	}
	
	public Localinfracao getLocalInfracao() {
		return localInfracao;
	}

	public void setLocalInfracao(Localinfracao localInfracao) {
		this.localInfracao = localInfracao;
	}

/*	public Localinfracao getLocalInfracao() {
		return localInfracao;
	}

	public void setLocalInfracao(Localinfracao localInfracao) {
		this.localInfracao = localInfracao;
	}*/

	public Integer getIdInfracao() {
		return idInfracao;
	}

	public void setIdInfracao(Integer idInfracao) {
		this.idInfracao = idInfracao;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Integer getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(Integer velocidade) {
		this.velocidade = velocidade;
	}

}
