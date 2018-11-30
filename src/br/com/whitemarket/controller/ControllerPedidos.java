	package br.com.whitemarket.controller;

import java.util.List;
import br.com.whitemarket.controller.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.whitemarket.model.Foto;
import br.com.whitemarket.model.ItemPedido;
import br.com.whitemarket.model.Pedido;
import br.com.whitemarket.model.Produto;

@Controller
public class ControllerPedidos {
	
	/*
	 * Método que lista os pedidos de um determinado usuário.
	 * @Rafa Nonino
	 */
	@RequestMapping("/verPedidos")
	public String verPedidos(Model model) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
		EntityManager	manager	= factory.createEntityManager();
		

		   List<Pedido> listPedidos =
				   manager.createQuery("select NEW Pedido(data_compra, valor_pago," +
				   		" (SELECT " + 
				   		" SUM(i.quantidade) as quantidades " + 
				   		" FROM ItemPedido i "
				   		+ " WHERE i.pedido.cod_pedido = p.cod_pedido " + 
				   		" )) from Pedido p").getResultList();
		   
		   model.addAttribute("listPedidos", listPedidos);
		   
		   manager.close();  
		   factory.close();
    	return "verPedidos";
	}
	
	/*
	 * Método que lista os produtos listados para a venda de um determinado usuário.
	 * @Rafa Nonino
	 */
	@RequestMapping("/verProdutos")
	public String verProdutos(Model model) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
		EntityManager	manager	= factory.createEntityManager();
		Util util = new Util();
		
		
		   List<Produto> listProdutos =
				   manager.createQuery("select NEW Produto(valor," +
				   		" (SELECT " + 
				   		" count(i.quantidade) as quantidades " + 
				   		" FROM ItemPedido i "
				   		+ " WHERE i.produto.codProduto = p.codProduto " + 
				   		" ), dataCadastro, codProduto) from Produto p").getResultList();
		   
		   for(Produto produto: listProdutos) {
			   produto.setUrlPrimeiraImagem(util.pegarPrimeiraFoto(produto.getCodProduto()));
		   }
		   

		   
		   model.addAttribute("listProdutos", listProdutos);
		   
		   manager.close();  
		   factory.close();
    	return "verProdutos";
	}
}



