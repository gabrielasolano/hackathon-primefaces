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
import com.stefanini.model.Tipoinfracao;
import com.stefanini.service.AgenteService;
import com.stefanini.service.DenunciaService;
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
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção", "Salvo com sucesso!"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Falha ao salvar!"));
		}
		this.tipoInfracao = new Tipoinfracao();
	}

	public void alterar() {

		/* procurar o id e ve se está na lista */
		try {
			procuraId();
			tipoInfracaoService.alterar(this.tipoInfracao);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção", "Tipo de infração alterado com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Tipo de infração não alterado!"));
		}

		this.tipoInfracao = new Tipoinfracao();

	}

	public void remover() {

		try {

			procuraId();
			tipoInfracaoService.remover(this.tipoInfracao.getIdTipoInfracao());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção", "Tipo de infração removido com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Tipo de infração não removido!"));
		}
		this.tipoInfracao = new Tipoinfracao();
	}

	public String voltar() {
		return "/index.faces?faces-redirect=true";

	}

	private void procuraId() throws Exception {
		inicia();
		for (Tipoinfracao tp : lista) {
			if (tp.getIdTipoInfracao().equals(this.tipoInfracao.getIdTipoInfracao())) {
				return;
			}
		}
		throw new Exception();
	}

	public Collection<Tipoinfracao> listar() {
		return this.tipoInfracaoService.listar();
	}

}
