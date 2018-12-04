package br.com.whitemarket.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.whitemarket.model.Produto;

@Repository
public class JPAItemProdutoDAO implements ItemProdutoDAO {

	@PersistenceContext
	EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	public Produto buscaPorCodigo(Long codigo) {
		
		Produto p = new Produto();
		
		Query query = manager.createQuery("SELECT p FROM Produto p JOIN FETCH p.listaFotos f WHERE p.codProduto = :codigo");
    	query.setParameter("codigo", codigo);
		
    	List<Produto> temp = query.getResultList();
    	if (!temp.isEmpty()) {
    		p = temp.get(0);
    	}
    	
		return p;
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
