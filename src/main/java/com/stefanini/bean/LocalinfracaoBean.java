package com.stefanini.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

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
public class LocalinfracaoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
    @Inject
    private LocalinfracaoService localInfracaoService;
    
    @Inject
    private Localinfracao localInfracao;
    
	private Collection<Localinfracao> lista = new ArrayList<Localinfracao>();


	@PostConstruct
	public void inicia() {
		lista = localInfracaoService.listar();
	}

	
	public Localinfracao getLocalInfracao() {
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
	
	public Collection<Localinfracao> listar(){
		return this.localInfracaoService.listar();
	}

	public void salvar(){
		
		try{
			localInfracaoService.incluir(getLocalInfracao());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção", "Salvo com sucesso!"));
		}catch(Exception e){		
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Falha ao salvar!"));
		}
		
		this.localInfracao = new Localinfracao();
	}

	public void alterar() {

		/* procurar o id e ve se está na lista */
		try {
			procuraId();
			localInfracaoService.alterar(this.localInfracao);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção", "Tipo de infração alterado com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Tipo de infração não alterado!"));
		}

		this.localInfracao = new Localinfracao();
	}
	
	public void remover() {

		try {

			procuraId();
			localInfracaoService.remover(this.localInfracao.getIdLocalInfracao());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção", "Tipo de infração removido com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Tipo de infração não removido!"));
		}
		this.localInfracao = new Localinfracao();
	}
	
	public String voltar() {
		return "/index.faces?faces-redirect=true";

	}
	
	private void procuraId() throws Exception {
		inicia();
		for (Localinfracao tp : lista) {
			if (tp.getIdLocalInfracao().equals(this.localInfracao.getIdLocalInfracao())) {
				return;
			}
		}
		throw new Exception();
	}
}
