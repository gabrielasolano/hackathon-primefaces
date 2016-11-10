package com.stefanini.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.stefanini.model.Infracoes;
import com.stefanini.model.Localinfracao;
import com.stefanini.repository.InfracoesRepository;

@Stateless
public class InfracoesService {
    @Inject
    private InfracoesRepository infracoesRepository;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void incluir(Infracoes infracoes){
    	infracoesRepository.incluir(infracoes);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<Infracoes> listar(){
    	return infracoesRepository.listar();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void alterar(Infracoes infracoes){
    	infracoesRepository.altera(infracoes);
    	
    }
      
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remover(Integer id){
    	infracoesRepository.remove(id);
    }
}
