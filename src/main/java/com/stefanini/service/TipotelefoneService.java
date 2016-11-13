package com.stefanini.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.stefanini.model.Tipotelefone;
import com.stefanini.repository.TipotelefoneRepository;

@Stateless
public class TipotelefoneService {
    @Inject
    private TipotelefoneRepository tipoTelefoneRepository;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void incluir(Tipotelefone tipoTelefone){
    	tipoTelefoneRepository.incluir(tipoTelefone);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<Tipotelefone> listar(){
    	return tipoTelefoneRepository.listar();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void alterar(Tipotelefone tipoTelefone){
    	tipoTelefoneRepository.altera(tipoTelefone);
    	
    }
      
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remover(Integer id){
    	tipoTelefoneRepository.remove(id);
    }
}
