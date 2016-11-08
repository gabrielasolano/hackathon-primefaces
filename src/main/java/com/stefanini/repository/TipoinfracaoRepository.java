package com.stefanini.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.stefanini.model.Tipoinfracao;

public class TipoinfracaoRepository {
	@Inject
	private EntityManager manager;


	public void incluir(Tipoinfracao tipoInfracao) {
		this.manager.persist(tipoInfracao);
	}

	public void altera(Tipoinfracao tipoInfracao) {
		this.manager.merge(tipoInfracao);
	}

	public Tipoinfracao busca(Integer id) {
		return this.manager.find(Tipoinfracao.class, id);
	}

	public List<Tipoinfracao> lista() {
		return this.manager.createQuery("select c from denuncia c", Tipoinfracao.class)
				.getResultList();
	}
}