package br.com.whitemarket.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.whitemarket.model.Foto;
import br.com.whitemarket.model.Pedido;
import br.com.whitemarket.model.Produto;

@Repository
public class JPAPedidoDAO implements PedidoDAO{
	
	@PersistenceContext
	EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public Pedido retornaPedidoCadastrado(long codigo) {
		Pedido p = new Pedido();
		
		Query query = manager.createQuery("SELECT pedido FROM Pedido pedido "
				+ "JOIN FETCH pedido.listaPedidos lP "
				+ "JOIN FETCH lP.produto produto "
				+ "JOIN FETCH produto.usuario u "
				+ "WHERE pedido.cod_pedido = :codigo");
    	query.setParameter("codigo", codigo);
		
    	List<Pedido> temp = query.getResultList();
    	if (!temp.isEmpty()) {
    		p = temp.get(0);
    	}
    	
		return p;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> retornaListaPedidos(long codigo) {
		Query query = manager.createQuery("select NEW Pedido(cod_pedido, data_compra, valor_pago," +
		   		" (SELECT " + 
		   		" SUM(i.quantidade) as quantidades " + 
		   		" FROM ItemPedido i "
		   		+ " WHERE i.pedido.cod_pedido = p.cod_pedido " + 
		   		" ), usuario) from Pedido p WHERE"
		   		+ " p.usuario.cod_usuario = :codigo");

		query.setParameter("codigo", codigo);
		
		List<Pedido> listPedidos = query.getResultList();
   		return listPedidos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> retornaListaProdutosCadastrados(long codigo) {
		Query query = manager.createQuery("select NEW Produto(valor," +
		   		" (SELECT " + 
		   		" count(i.quantidade) as quantidades " + 
		   		" FROM ItemPedido i "
		   		+ " WHERE i.produto.codProduto = p.codProduto " + 
		   		" ), dataCadastro, codProduto, usuario) from Produto p "
		   		+ "WHERE p.ativo = 1 AND p.usuario.cod_usuario = :codigo");
		query.setParameter("codigo", codigo);
 
		List<Produto> listProdutos = query.getResultList();
		return listProdutos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Foto> retornaPrimeiraFoto(long codProduto) {
		Query query = manager.createQuery("SELECT f FROM Foto f WHERE f.produto.codProduto = :codigo");
		query.setParameter("codigo", codProduto);
		
		List<Foto> listaFotos = query.setMaxResults(1).getResultList();
		
		return listaFotos;
	}
}