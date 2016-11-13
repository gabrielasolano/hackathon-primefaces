package com.stefanini.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.stefanini.model.Agente;
import com.stefanini.service.AgenteService;

@Named("agenteMB")
@SessionScoped
public class AgenteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AgenteService agenteService;

	@Inject
	private Agente agente;

	private Collection<Agente> lista = new ArrayList<Agente>();
	private Integer idMatricula;
	private Integer tempoServico;
	private String dtContratacao;
	private String nome;

	@PostConstruct
	public void inicia() {
		lista = agenteService.listar();
	}

	public Agente getAgente() {
		if (agente == null) {
			agente = new Agente();
		}
		return agente;
	}

	public void setAgente(Agente agente) {
		this.agente = agente;
	}

	public Collection<Agente> getLista() {
		return lista;
	}

	public void setLista(Collection<Agente> lista) {
		this.lista = lista;
	}

	public Integer getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(Integer idMatricula) {
		this.idMatricula = idMatricula;
	}

	public Integer getTempoServico() {
		return tempoServico;
	}

	public void setTempoServico(Integer tempoServico) {
		this.tempoServico = tempoServico;
	}

	public String getDtContratacao() {
		return dtContratacao;
	}

	public void setDtContratacao(String dtContratacao) {
		this.dtContratacao = dtContratacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void cadastrarAgente() {

		try {
			agenteService.incluir(getAgente());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Cadastrado com sucesso!"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage()));
		}

		this.agente = new Agente();
	}

	public void alterarAgente(Integer matricula) {

		/* procurar o id do agente por meio da matrícula antiga */
		try {
			Agente a = procurarMatricular(/* this.idMatricula */matricula);
			a.setNome(this.agente.getNome());
			a.setDtContratacao(this.agente.getDtContratacao());
			a.setTempoServico(this.agente.getTempoServico());
			a.setMatricula(this.agente.getMatricula());

			agenteService.alterar(a);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Alterado com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage()));
		}

		this.agente = new Agente();
		this.idMatricula = new Integer(0);

	}

	public String removerAgente(Integer matricula) {

		try {
			Agente a = procurarMatricular(matricula);
			agenteService.remover(a.getIdAgente());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Removido com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage()));
		}
		this.agente = new Agente();
		return "listarAgente.faces?faces-redirect=true";
	}

	public String voltar() {
		return "listarAgente.faces?faces-redirect=true";

	}

	public Collection<Agente> listar() {
		return this.agenteService.listar();
	}

	private Agente procurarMatricular(Integer matricula) {
		inicia();
		for (Agente ag : lista) {
			if (ag.getMatricula().equals(matricula)) {
				return ag;
			}
		}
		return null;
	}

	public String recuperaId() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		idMatricula = Integer.parseInt(params.get("matricula"));
		nome = params.get("nome");
		tempoServico = Integer.parseInt(params.get("tempoServico"));
		dtContratacao = params.get("dtContratacao");
		return "alterarAgente?faces-redirect=true";
	}

}
