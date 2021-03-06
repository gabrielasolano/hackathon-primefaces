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

import com.stefanini.model.Tipoinfracao;
import com.stefanini.service.TipoinfracaoService;

@Named("tipoInfracaoMB")
@SessionScoped
public class TipoinfracaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoinfracaoService tipoInfracaoService;

	@Inject
	private Tipoinfracao tipoInfracao;

	private Collection<Tipoinfracao> lista = new ArrayList<Tipoinfracao>();

	private Integer idTipoInfracao;
	private String descricao;
	private Double valor;

	@PostConstruct
	public void inicia() {
		lista = tipoInfracaoService.listar();
	}

	public Tipoinfracao getTipoInfracao() {
		if (tipoInfracao == null) {
			tipoInfracao = new Tipoinfracao();
		}
		return tipoInfracao;
	}

	public void setTipoInfracao(Tipoinfracao tipoInfracao) {
		this.tipoInfracao = tipoInfracao;
	}

	public Collection<Tipoinfracao> getLista() {
		return lista;
	}

	public void setLista(Collection<Tipoinfracao> lista) {
		this.lista = lista;
	}

	public void salvar() {

		try {
			tipoInfracaoService.incluir(getTipoInfracao());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Salvo com sucesso!"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage()));
		}
		this.tipoInfracao = new Tipoinfracao();
	}

	public void alterar(Integer id) {

		try {
			this.tipoInfracao.setIdTipoInfracao(id);
			tipoInfracaoService.alterar(this.tipoInfracao);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Alterado com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage()));
		}

		this.tipoInfracao = new Tipoinfracao();

	}

	public String remover(Integer id) {

		try {
			// procuraId();
			tipoInfracaoService.remover(id);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Removido com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage()));
		}
		this.tipoInfracao = new Tipoinfracao();

		return "listarTipoInfracao?faces-redirect=true";
	}

	public String voltar() {
		return "listarTipoInfracao?faces-redirect=true";

	}

	public Collection<Tipoinfracao> listar() {
		return this.tipoInfracaoService.listar();
	}

	public Integer getIdTipoInfracao() {
		return idTipoInfracao;
	}

	public void setIdTipoInfracao(Integer idTipoInfracao) {
		this.idTipoInfracao = idTipoInfracao;
	}

	public String recuperaId() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		idTipoInfracao = Integer.parseInt(params.get("idTipoInfracao"));
		descricao = params.get("descricao");
		valor = Double.parseDouble(params.get("valor"));
		return "alterarTipoInfracao?faces-redirect=true";
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
