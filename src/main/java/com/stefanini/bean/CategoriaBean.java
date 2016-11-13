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

import com.stefanini.model.Categoria;
import com.stefanini.service.CategoriaService;

@Named("categoriaMB")
@SessionScoped
public class CategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaService categoriaService;

	@Inject
	private Categoria categoria;

	private Collection<Categoria> lista = new ArrayList<Categoria>();

	private Integer idCategoria;
	private String descricao;

	@PostConstruct
	public void inicia() {
		lista = categoriaService.listar();
	}

	public Categoria getCategoria() {
		if (categoria == null) {
			categoria = new Categoria();
		}
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Collection<Categoria> getLista() {
		return lista;
	}

	public void setLista(Collection<Categoria> lista) {
		this.lista = lista;
	}

	public void salvar() {

		try {
			categoriaService.incluir(getCategoria());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Salvo com sucesso!"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage()));
		}
		this.categoria = new Categoria();
	}

	public void alterar(Integer id) {

		try {
			this.categoria.setIdCategoria(id);
			categoriaService.alterar(this.categoria);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Alterado com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage()));
		}

		this.categoria = new Categoria();

	}

	public String remover(Integer id) {

		try {
			// procuraId();
			categoriaService.remover(id);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Removido com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage()));
		}
		this.categoria = new Categoria();

		return "listarCategoria?faces-redirect=true";
	}

	public String voltar() {
		return "listarCategoria?faces-redirect=true";

	}

	public Collection<Categoria> listar() {
		return this.categoriaService.listar();
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String recuperaId() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		idCategoria = Integer.parseInt(params.get("idCategoria"));
		descricao = params.get("descricao");
		return "alterarCategoria?faces-redirect=true";
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
