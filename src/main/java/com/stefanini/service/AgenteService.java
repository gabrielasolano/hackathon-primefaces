package com.stefanini.service;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.stefanini.model.Agente;
import com.stefanini.model.Tipoinfracao;
import com.stefanini.repository.AgenteRepository;

@Stateless
public class AgenteService {

    @Inject
    private AgenteRepository agenteRepository;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void incluir(Agente agente){
    	
    	if (agenteRepository == null) agenteRepository = new AgenteRepository();
    	agenteRepository.incluir(agente);
    	
    }
    
    public Collection<Agente> listar(){
    	return agenteRepository.lista();
    }

}