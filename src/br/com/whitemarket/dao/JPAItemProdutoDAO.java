package br.com.whitemarket.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.whitemarket.model.Produto;

@Repository
public class JPAItemProdutoDAO implements ItemProdutoDAO {

	@PersistenceContext
	EntityManager manager;
	
	@Override
	public Produto buscaPorCodigo(Long codigo) {
		return manager.find(Produto.class, codigo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> listaProdutos() {
		return manager.createQuery("SELECT p FROM Produto p").getResultList();
	}

	@Override
	public void adicionaProduto(Produto produto) {
		manager.persist(produto);
	}

}
