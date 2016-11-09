package com.stefanini.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.stefanini.model.Infracoes;

public class InfracoesRepository {
	@Inject
	private EntityManager manager;

	public void incluir(Infracoes infracoes) {
		this.manager.persist(infracoes);
	}

	public void altera(Infracoes infracoes) {
		this.manager.merge(infracoes);
	}

	public Infracoes busca(Integer id) {
		return this.manager.find(Infracoes.class, id);
	}

	public List<Infracoes> lista() {
		return this.manager.createQuery("select c from agente c", Infracoes.class)
				.getResultList();
	}
}
