package com.stefanini.repository;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.stefanini.model.Tipotelefone;

public class TipotelefoneRepository {
	@Inject
	private EntityManager manager;


	public void incluir(Tipotelefone tipoTelefone) {
		this.manager.persist(tipoTelefone);
	}

	public void altera(Tipotelefone tipoTelefone) {
		this.manager.merge(tipoTelefone);
	}

	public Tipotelefone busca(Integer id) {
		return this.manager.find(Tipotelefone.class, id);
	}
	
	public void remove(Integer id) {
		this.manager.remove(manager.find(Tipotelefone.class, id));
	}

	public List<Tipotelefone> lista() {
		return this.manager.createQuery("select c from Tipotelefone c", Tipotelefone.class)
				.getResultList();
	}
	
	public Collection<Tipotelefone> listar() {
		// TODO Auto-generated method stub
		Query q = manager.createQuery("SELECT p FROM Tipotelefone p");
		return (Collection<Tipotelefone>) q.getResultList();
	}
}
