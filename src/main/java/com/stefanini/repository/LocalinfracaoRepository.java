package com.stefanini.repository;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.stefanini.model.Localinfracao;
import com.stefanini.model.Tipoinfracao;

public class LocalinfracaoRepository {
	@Inject
	private EntityManager manager;


	public void incluir(Localinfracao localInfracao) {
		this.manager.persist(localInfracao);
	}

	public void altera(Localinfracao localInfracao) {
		this.manager.merge(localInfracao);
	}

	public Localinfracao busca(Integer id) {
		return this.manager.find(Localinfracao.class, id);
	}
	
	public void remove(Integer id) {
		this.manager.remove(manager.find(Localinfracao.class, id));
	}

	public List<Localinfracao> lista() {
		return this.manager.createQuery("select c from denuncia c", Localinfracao.class)
				.getResultList();
	}
	
	public Collection<Localinfracao> listar() {
		// TODO Auto-generated method stub
		Query q = manager.createQuery("SELECT p FROM Localinfracao p");
		return (Collection<Localinfracao>) q.getResultList();
	}
}
