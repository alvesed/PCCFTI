package br.com.whitemarket.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.whitemarket.model.Produto;

@Repository
public class JPAProdutoDAO implements ProdutoDAO {

	@PersistenceContext
	EntityManager manager;
	
	@Override
	public void adiciona(Produto produto) {
		manager.persist(produto);
	}
}
