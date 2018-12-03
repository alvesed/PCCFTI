	package br.com.whitemarket.controller;

import java.util.List;
import br.com.whitemarket.controller.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.whitemarket.model.Foto;
import br.com.whitemarket.model.ItemPedido;
import br.com.whitemarket.model.Pedido;
import br.com.whitemarket.model.Produto;
import br.com.whitemarket.model.Usuario;

@Controller
public class ControllerPedidos {
	
	/*
	 * Método que lista os pedidos de um determinado usuário.
	 * @Rafa Nonino
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/verPedidos")
	public String verPedidos(Model model, HttpSession session) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
		EntityManager	manager	= factory.createEntityManager();
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		
		if(usuario == null) {
			return "login";
		}
		long codigo = usuario.getCod_usuario();
		
		Query query = manager.createQuery("select NEW Pedido(cod_pedido, data_compra, valor_pago," +
				   		" (SELECT " + 
				   		" SUM(i.quantidade) as quantidades " + 
				   		" FROM ItemPedido i "
				   		+ " WHERE i.pedido.cod_pedido = p.cod_pedido " + 
				   		" ), usuario) from Pedido p WHERE"
				   		+ " p.usuario.cod_usuario = :codigo");
		
			query.setParameter("codigo", codigo);

		   List<Pedido> listPedidos = query.getResultList();
		   
		   model.addAttribute("listPedidos", listPedidos);
		   
		   manager.close();  
		   factory.close();
    	return "verPedidos";
	}
	
	/*
	 * Método que lista os produtos listados para a venda de um determinado usuário.
	 * @Rafa Nonino
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/verProdutos")
	public String verProdutos(Model model, HttpSession session) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
		EntityManager	manager	= factory.createEntityManager();
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		
		Util util = new Util();
		if(usuario == null) {
			return "login";
		}
		
		long codigo = usuario.getCod_usuario();
		
		 Query query = manager.createQuery("select NEW Produto(valor," +
				   		" (SELECT " + 
				   		" count(i.quantidade) as quantidades " + 
				   		" FROM ItemPedido i "
				   		+ " WHERE i.produto.codProduto = p.codProduto " + 
				   		" ), dataCadastro, codProduto, usuario) from Produto p "
				   		+ "WHERE p.ativo = 1 AND p.usuario.cod_usuario = :codigo");
		 query.setParameter("codigo", codigo);
		 
		 List<Produto> listProdutos = query.getResultList();
		   
		   for(Produto produto: listProdutos) {
			   produto.setUrlPrimeiraImagem(util.pegarPrimeiraFoto(produto.getCodProduto()));
		   }
		   
		   model.addAttribute("listProdutos", listProdutos);
		   
		   manager.close();  
		   factory.close();
    	return "verProdutos";
	}
	
	@RequestMapping(value="/verCarrinhoJaCadastrado")
	public String cartJaCadastrado(HttpSession session, Model model, String cod_pedido) {
		
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
	    EntityManager manager = factory.createEntityManager();
	    
	    System.out.println(manager.find(Pedido.class, Long.parseLong(cod_pedido)).getValor_pago());
		
		if(usuario != null && !usuario.getEmail().equals("")) {
			model.addAttribute("usuario", usuario);
			model.addAttribute("pedido", manager.find(Pedido.class, Long.parseLong(cod_pedido)));
		} else {
			model.addAttribute("usuario", new Usuario());
		}
		
		return "cartJaCadastrado";
	}
	
}



