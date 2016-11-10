package com.stefanini.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.stefanini.model.Agente;
import com.stefanini.model.Infracoes;
import com.stefanini.service.AgenteService;
import com.stefanini.service.InfracoesService;

@Named("infracoesMB")
@SessionScoped
public class InfracoesBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
    private InfracoesService infracoesService;
	
	@Inject
	private AgenteService agenteService;
    
	@Inject
    private Infracoes infracoes;
	
	public List<Integer> todosAgentes(){
		Collection<Agente> colecao = agenteService.listar();
		Collection<Integer> matricula = new ArrayList<Integer>();
		for(Agente ag : colecao){
			Integer mat = ag.getMatricula();
			matricula.add(mat);
		}
		return (List<Integer>) matricula;
	}

	public Infracoes getInfracoes() {
		if(infracoes == null){
			infracoes = new Infracoes();
		}
		return infracoes;
	}

	public void setInfracoes(Infracoes infracoes) {
		this.infracoes = infracoes;
	}
	
	public String cadastrarInfracoes(){
		
		if(infracoesService == null) infracoesService = new InfracoesService();		
		this.infracoes.setPlaca("placa");
		
		try{
			infracoesService.incluir(getInfracoes());
			
		}catch(Exception e){		
			return "/pages/erro.faces?faces-redirect=true";
		}
		
		this.infracoes = new Infracoes();
		return "/pages/sucesso.faces?faces-redirect=true";
	}

}
