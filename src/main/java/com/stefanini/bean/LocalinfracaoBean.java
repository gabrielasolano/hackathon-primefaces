package com.stefanini.bean;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.stefanini.model.Localinfracao;
import com.stefanini.model.Tipoinfracao;
import com.stefanini.service.LocalinfracaoService;
import com.stefanini.service.TipoinfracaoService;

@Named("localInfracaoMB")
@SessionScoped
public class LocalinfracaoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
    @Inject
    private LocalinfracaoService localInfracaoService;
    
    @Inject
    private Localinfracao localInfracao;

	
	public Localinfracao getLocalInfracao() {
		return localInfracao;
	}

	public void setLocalInfracao(Localinfracao localInfracao) {
		this.localInfracao = localInfracao;
	}
	
	public Collection<Localinfracao> listar(){
		return this.localInfracaoService.listar();
	}

	public String salvar(){
		
		try{
			localInfracaoService.incluir(getLocalInfracao());
			
		}catch(Exception e){		
			return "/pages/erro.faces?faces-redirect=true";
		}
		
		this.localInfracao = new Localinfracao();
		return "/pages/sucesso.faces?faces-redirect=true";
	}
}
