package com.stefanini.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.stefanini.model.Localinfracao;
import com.stefanini.model.Tipoinfracao;
import com.stefanini.repository.LocalinfracaoRepository;
import com.stefanini.repository.TipoinfracaoRepository;

@Stateless
public class LocalinfracaoService {
    @Inject
    private LocalinfracaoRepository localInfracaoRepository;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void incluir(Localinfracao localInfracao){
    	localInfracaoRepository.incluir(localInfracao);
    }
}
