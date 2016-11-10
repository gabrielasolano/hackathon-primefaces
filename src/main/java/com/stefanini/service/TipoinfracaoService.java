package com.stefanini.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.stefanini.model.Tipoinfracao;
import com.stefanini.repository.TipoinfracaoRepository;

@Stateless
public class TipoinfracaoService {
    @Inject
    private TipoinfracaoRepository tipoInfracaoRepository;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void incluir(Tipoinfracao tipoInfracao){
    	tipoInfracaoRepository.incluir(tipoInfracao);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<Tipoinfracao> listar(){
    	return tipoInfracaoRepository.lista();
    }
	
}
