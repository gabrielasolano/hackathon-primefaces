package com.stefanini.repository;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.stefanini.model.Agente;

public class AgenteRepository {

		@Inject
		private EntityManager manager;

		public void incluir(Agente agente) {
			this.manager.persist(agente);
		}

		public void altera(Agente agente) {
			this.manager.merge(agente);
		}

		public void remove(Integer id) {
			this.manager.remove(manager.find(Agente.class, id));
		}
		
		public Agente busca(Integer id) {
			return this.manager.find(Agente.class, id);
		}

		public List<Agente> lista() {
			return this.manager.createQuery("select c from Agente c", Agente.class)
					.getResultList();
		}
		
		public Collection<Agente> listar(){
			Query q = this.manager.createQuery("select c from Agente c");
			return (Collection<Agente>) q.getResultList();
		}
	}