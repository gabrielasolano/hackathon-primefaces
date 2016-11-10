package com.stefanini.repository;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.stefanini.model.Infracoes;
import com.stefanini.model.Tipoinfracao;

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
	
	public void remove(Integer id) {
		this.manager.remove(manager.find(Infracoes.class, id));
	}

	public List<Infracoes> lista() {
		return this.manager.createQuery("select c from Infracoes c", Infracoes.class)
				.getResultList();
	}
	
	public Collection<Infracoes> listar() {
		// TODO Auto-generated method stub
		Query q = manager.createQuery("SELECT p FROM Infracoes p");
		return (Collection<Infracoes>) q.getResultList();
	}
}
