package com.stefanini.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.stefanini.model.Agente;
import com.stefanini.service.AgenteService;

@Named("agenteMB")
@SessionScoped
public class AgenteBean implements Serializable{
		
		private static final long serialVersionUID = 1L;

		@Inject
	    private AgenteService agenteService;
	    
		@Inject
	    private Agente agente;
	    
	    public Agente getAgente() {
	    	if(agente == null){
	    		agente = new Agente();
	    	}
			return agente;
		}

		public void setAgente(Agente agente) {
			this.agente = agente;
		}

		public String chamar() {	
	        return "/pages/teste.faces?faces-redirect=true"; //força a redirecionar para teste, mesmo se ocorrer erro
	    }
		
		public String cadastrarAgente(){
			
			if(agenteService == null) agenteService = new AgenteService();
			
			/*Calcular tempo de serviço*/				
			
			try{
				agenteService.incluir(getAgente());
				
			}catch(Exception e){		
				return "/pages/erro.faces?faces-redirect=true";
			}
			
			this.agente = new Agente();
			return "/pages/sucesso.faces?faces-redirect=true";
		}
		
		public String voltar(){
			return "/index.faces?faces-redirect=true";
			
		}
				
	}
