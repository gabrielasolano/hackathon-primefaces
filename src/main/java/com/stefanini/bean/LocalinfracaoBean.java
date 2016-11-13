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

import com.stefanini.model.Localinfracao;
import com.stefanini.service.LocalinfracaoService;

@Named("localInfracaoMB")
@SessionScoped
public class LocalinfracaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private LocalinfracaoService localInfracaoService;

	@Inject
	private Localinfracao localInfracao;

	private Collection<Localinfracao> lista = new ArrayList<Localinfracao>();

	private Integer idLocalInfracao;
	private String descricao;
	private Double velocidade;

	@PostConstruct
	public void inicia() {
		lista = localInfracaoService.listar();
	}

	public Localinfracao getLocalInfracao() {
		if (localInfracao == null) {
			localInfracao = new Localinfracao();
		}
		return localInfracao;
	}

	public void setLocalInfracao(Localinfracao localInfracao) {
		this.localInfracao = localInfracao;
	}

	public Collection<Localinfracao> getLista() {
		return lista;
	}

	public void setLista(Collection<Localinfracao> lista) {
		this.lista = lista;
	}

	public void salvar() {

		try {
			localInfracaoService.incluir(getLocalInfracao());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Salvo com sucesso!"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage()));
		}
		this.localInfracao = new Localinfracao();
	}

	public void alterar(Integer id) {

		try {
			this.localInfracao.setIdLocalInfracao(id);
			localInfracaoService.alterar(this.localInfracao);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Alterado com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage()));
		}

		this.localInfracao = new Localinfracao();

	}

	public String remover(Integer id) {

		try {
			// procuraId();
			localInfracaoService.remover(id);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Removido com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage()));
		}
		this.localInfracao = new Localinfracao();

		return "listarLocalInfracao?faces-redirect=true";
	}

	public String voltar() {
		return "listarLocalInfracao?faces-redirect=true";

	}

	public Collection<Localinfracao> listar() {
		return this.localInfracaoService.listar();
	}

	public Integer getIdLocalInfracao() {
		return idLocalInfracao;
	}

	public void setIdLocalInfracao(Integer idLocalInfracao) {
		this.idLocalInfracao = idLocalInfracao;
	}

	public String recuperaId() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		idLocalInfracao = Integer.parseInt(params.get("idLocalInfracao"));
		descricao = params.get("descricao");
		velocidade = Double.parseDouble(params.get("velocidade"));
		return "alterarLocalInfracao?faces-redirect=true";
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(Double velocidade) {
		this.velocidade = velocidade;
	}

}
