package br.com.whitemarket.controller;

import java.util.ArrayList;
import java.util.List;
import br.com.whitemarket.controller.*;
import br.com.whitemarket.dao.PedidoDAO;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.whitemarket.model.ItemPedido;
import br.com.whitemarket.model.ListaItensDoVendedor;
import br.com.whitemarket.model.Pedido;
import br.com.whitemarket.model.Produto;
import br.com.whitemarket.model.Usuario;

@Controller
public class ControllerPedidos {
	
	@Autowired
	PedidoDAO dao;
	
	/*
	 * Método que lista os pedidos de um determinado usuário.
	 * @Rafa Nonino
	 */
	
	@RequestMapping("/verPedidos")
	public String verPedidos(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		
		if(usuario == null) {
			return "login";
		}
		long codigo = usuario.getCod_usuario();
		System.out.println(codigo);

		List<Pedido> listPedidos = dao.retornaListaPedidos(codigo);
		   
		model.addAttribute("listPedidos", listPedidos);
    	return "verPedidos";
	}
	
	/*
	 * Método que lista os produtos listados para a venda de um determinado usuário.
	 * @Rafa Nonino
	 */
	
	@RequestMapping("/verProdutos")
	public String verProdutos(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		
		Util util = new Util();
		if(usuario == null) {
			return "login";
		}
		
		long codigo = usuario.getCod_usuario();
		
		 List<Produto> listProdutos = dao.retornaListaProdutosCadastrados(codigo);
		   
		   for(Produto produto: listProdutos) {
			   produto.setUrlPrimeiraImagem(util.pegarPrimeiraFoto(produto.getCodProduto()));
		   }
		   
		   model.addAttribute("listProdutos", listProdutos);
		   
    	return "verProdutos";
	}
	
	@RequestMapping(value="/verCarrinhoJaCadastrado")
	public String cartJaCadastrado(HttpSession session, Model model, String cod_pedido) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		
		if (usuario == null || usuario.getEmail().equals("")) {
			return "login";
		}
		
		Pedido pedido = dao.retornaPedidoCadastrado(Long.parseLong(cod_pedido));
		
		if(pedido == null) {
			return "pedido404";
		}
		
		ArrayList<ListaItensDoVendedor> listaDeVendedores = new ArrayList<ListaItensDoVendedor>();

		ItemPedido itemPedido = pedido.getListaPedidos().get(0);
		Usuario vendedor = pedido.getListaPedidos().get(0).getProduto().getUsuario();
		ListaItensDoVendedor itensDoVendedor = new ListaItensDoVendedor(vendedor);
		
		itensDoVendedor.getListaItemPedido().add(itemPedido);
		
		for (int i = 1; i < pedido.getListaPedidos().size(); i++) {
			itemPedido = pedido.getListaPedidos().get(i);
			itemPedido.getProduto().setListaFotos(dao.retornaPrimeiraFoto(itemPedido.getProduto().getCodProduto())); //Faz a query para a primeira foto do produto, que não pode ser retornada na primeira query
			vendedor = itemPedido.getProduto().getUsuario();
			
			if (vendedor.getCod_usuario() == itensDoVendedor.getVendedor().getCod_usuario()) {
				itensDoVendedor.getListaItemPedido().add(itemPedido);
			} else {
				listaDeVendedores.add(itensDoVendedor);
				itensDoVendedor = new ListaItensDoVendedor(vendedor);
				itensDoVendedor.getListaItemPedido().add(itemPedido);
			}
		}
		listaDeVendedores.add(itensDoVendedor);
		
		model.addAttribute("lista", listaDeVendedores);
		model.addAttribute("pedido", pedido);
		return "mostraPedido";
	}
	
}