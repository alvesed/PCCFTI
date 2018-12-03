package br.com.whitemarket.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.whitemarket.model.Pedido;

public class JPACarrinhoDAO implements CarrinhoDAO{
	
	@PersistenceContext
	EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public Pedido retornaPedidoCadastrado(long codigo) {
		Pedido p = new Pedido();
		
		Query query = manager.createQuery("SELECT pedido FROM Pedido pedido JOIN FETCH pedido.listaPedidos.codProduto WHERE pedido.cod_pedido = :codigo");
    	query.setParameter("codigo", codigo);
		
    	List<Pedido> temp = query.getResultList();
    	if (!temp.isEmpty()) {
    		p = temp.get(0);
    	}
    	
		return p;
	}

	@Override
	public void confirmaPedido(Pedido pedido) {
		manager.merge(pedido);
	}
}
