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

import com.stefanini.model.Tipotelefone;
import com.stefanini.service.TipotelefoneService;

@Named("tipoTelefoneMB")
@SessionScoped
public class TipotelefoneBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipotelefoneService tipoTelefoneService;

	@Inject
	private Tipotelefone tipoTelefone;

	private Collection<Tipotelefone> lista = new ArrayList<Tipotelefone>();

	private Integer idTipoTelefone;
	private String descricao;

	@PostConstruct
	public void inicia() {
		lista = tipoTelefoneService.listar();
	}

	public Tipotelefone getTipoTelefone() {
		if (tipoTelefone == null) {
			tipoTelefone = new Tipotelefone();
		}
		return tipoTelefone;
	}

	public void setTipoTelefone(Tipotelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public Collection<Tipotelefone> getLista() {
		return lista;
	}

	public void setLista(Collection<Tipotelefone> lista) {
		this.lista = lista;
	}

	public void salvar() {

		try {
			tipoTelefoneService.incluir(getTipoTelefone());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Salvo com sucesso!"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage()));
		}
		this.tipoTelefone = new Tipotelefone();
	}

	public void alterar(Integer id) {

		try {
			this.tipoTelefone.setIdTipoTelefone(id);
			tipoTelefoneService.alterar(this.tipoTelefone);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Alterado com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage()));
		}

		this.tipoTelefone = new Tipotelefone();

	}

	public String remover(Integer id) {

		try {
			// procuraId();
			tipoTelefoneService.remover(id);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Removido com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage()));
		}
		this.tipoTelefone = new Tipotelefone();

		return "listarTipoTelefone?faces-redirect=true";
	}

	public String voltar() {
		return "listarTipoTelefone?faces-redirect=true";

	}

	public Collection<Tipotelefone> listar() {
		return this.tipoTelefoneService.listar();
	}

	public Integer getIdTipoTelefone() {
		return idTipoTelefone;
	}

	public void setIdTipoTelefone(Integer idTipoTelefone) {
		this.idTipoTelefone = idTipoTelefone;
	}

	public String recuperaId() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		idTipoTelefone = Integer.parseInt(params.get("idTipoTelefone"));
		descricao = params.get("descricao");
		return "alterarTipoTelefone?faces-redirect=true";
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
