package com.stefanini.bean;

import java.io.Serializable;

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

	public void salvar(){
		
		try{
			localInfracaoService.incluir(getLocalInfracao());
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage("Erro!"));
		}
	
		FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Salvo com sucesso!"));	
	}
}
