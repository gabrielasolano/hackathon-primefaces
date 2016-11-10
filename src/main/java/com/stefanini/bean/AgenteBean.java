package com.stefanini.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.stefanini.model.Agente;
import com.stefanini.model.Infracoes;
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

	public void cadastrarAgente() {

		try {
			agenteService.incluir(getAgente());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção", "Agente cadastrado com sucesso!"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Agente não cadastrado!"));
		}

		this.agente = new Agente();
	}

	public void alterarAgente() {

		/* procurar o id do agente por meio da matrícula antiga */
		try {
			Agente a = procurarMatricular(this.idMatricula);
			a.setNome(this.agente.getNome());
			a.setDtContratacao(this.agente.getDtContratacao());
			a.setTempoServico(this.agente.getTempoServico());
			a.setMatricula(this.agente.getMatricula());

		
			agenteService.alterar(a);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção", "Agente alterado com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Agente não alterado!"));
		}

		this.agente = new Agente();
		this.idMatricula = new Integer(0);

	}

	public void removerAgente() {
	
		try {
			Agente a = procurarMatricular(this.agente.getMatricula());
			agenteService.remover(a.getIdAgente());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção", "Agente removido com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Agente não removido!"));
		}
		this.agente = new Agente();
	}

	public String voltar() {
		return "/index.faces?faces-redirect=true";

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

}
