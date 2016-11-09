package com.stefanini.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.stefanini.model.Infracoes;
import com.stefanini.repository.InfracoesRepository;

@Stateless
public class InfracoesService {
    @Inject
    private InfracoesRepository infracoesRepository;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void incluir(Infracoes infracoes){
    	
    	if (infracoesRepository == null) infracoesRepository = new InfracoesRepository();
    	infracoesRepository.incluir(infracoes);
    	
    }
}
