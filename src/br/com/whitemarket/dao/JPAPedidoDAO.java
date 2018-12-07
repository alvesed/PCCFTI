package br.com.whitemarket.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.whitemarket.model.Avaliacao;
import br.com.whitemarket.model.Foto;
import br.com.whitemarket.model.ItemPedido;
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
	
	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public Pedido retornaProdutosDentroDePedido(long codPedido) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
	    EntityManager manager2 = factory.createEntityManager();
	    manager2.getTransaction().begin();
		Query query = manager2.createQuery("SELECT p FROM Pedido p "
				+ "JOIN FETCH p.listaPedidos lp "
				+ "JOIN FETCH lp.produto produto "
				+ "JOIN FETCH p.usuario user WHERE p.cod_pedido = :codPedido");
    	query.setParameter("codPedido", codPedido);
    	manager2.getTransaction().commit();
		
    	List<Pedido> listPedido = query.getResultList();
    	Pedido pedidoCompleto = new Pedido();
    	for (Pedido pedido : listPedido) {
    		pedidoCompleto = pedido; 
    	}
    	
    	for (ItemPedido itemPedido : pedidoCompleto.getListaPedidos()) {
    		Produto produto = itemPedido.getProduto();
        	
        	produto.setListaFotos(retornaPrimeiraFoto(produto.getCodProduto()));
    	}
    	return pedidoCompleto;
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
		System.out.println("testando erro banco 1");	
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
	public List<Produto> retornaListaNomeProdutos(long codigo) {
		System.out.println("testando erro banco 1");
		
		Query query = manager.createQuery("select NEW Produto(codProduto, p.nome) from Produto p "
		   		+ "WHERE p.ativo = 1 AND p.usuario.cod_usuario = :codigo");
		query.setParameter("codigo", codigo);
		
		System.out.println("testando erro banco 2");
 
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
	
	@SuppressWarnings("unchecked")
	public List<Avaliacao> retornaVendedoresAvalidosNoPedido(long codPedido, long codComprador) {
		Query query = manager.createQuery("SELECT a FROM Avaliacao a "
				+ "JOIN FETCH a.pedido p "
				+ "JOIN FETCH a.vendedor u "
				+ "WHERE a.pedido.cod_pedido = :codPedido AND a.comprador.cod_usuario = :codComprador");
		query.setParameter("codPedido", codPedido).setParameter("codComprador", codComprador);
		
		List<Avaliacao> avaliacao = query.getResultList();
		
		return avaliacao;
	}
	
	
}