package com.stefanini.bean;

import java.io.Serializable;

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
public class TipoinfracaoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
    @Inject
    private TipoinfracaoService tipoInfracaoService;
    
    @Inject
    private Tipoinfracao tipoInfracao;


	public Tipoinfracao getTipoInfracao() {
		if(tipoInfracao == null){
			tipoInfracao = new Tipoinfracao();
		}
		return tipoInfracao;
	}

	public void setTipoInfracao(Tipoinfracao tipoInfracao) {
		this.tipoInfracao = tipoInfracao;
	}
	
	public void salvar(){
		
		try{
			tipoInfracaoService.incluir(getTipoInfracao());
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage("Erro!"));
		}
	
		this.tipoInfracao = new Tipoinfracao();
		FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Salvo com sucesso!"));	
	}
}
