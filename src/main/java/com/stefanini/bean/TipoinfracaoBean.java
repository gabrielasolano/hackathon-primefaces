package com.stefanini.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

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
	
	public String salvar(){
		
		if(tipoInfracaoService == null) tipoInfracaoService = new TipoinfracaoService();
		
		try{
			tipoInfracaoService.incluir(getTipoInfracao());
			
		}catch(Exception e){		
			return "/pages/erro.faces?faces-redirect=true";
		}
		
		this.tipoInfracao = new Tipoinfracao();
		return "/pages/sucesso.faces?faces-redirect=true";
	}
	
	public String voltar(){
		return "/index.faces?faces-redirect=true";
		
	}
	
	public Collection<Integer> todosTiposInfracoes(){
		Collection<Tipoinfracao> colecao = tipoInfracaoService.listar();
		Collection<Integer> idTipo = new ArrayList<Integer>();
		for(Tipoinfracao tipo : colecao){
			Integer descTipo = tipo.getIdTipoInfracao();
			idTipo.add(descTipo);
		}
		return idTipo;
	}
}
