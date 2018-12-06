	package br.com.whitemarket.controller;
	

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.uol.pagseguro.domain.Address;
import br.com.uol.pagseguro.domain.Item;
import br.com.whitemarket.model.Endereco;
import br.com.whitemarket.model.ItemPedido;
import br.com.whitemarket.model.Pedido;
import br.com.whitemarket.model.Usuario;
import br.com.whitemarket.controller.Util;
@Controller
public class ControllerCarrinho {
	
	@RequestMapping(value="/verCarrinho")
	public String cart(HttpSession session) {
		return "cart";
	}
	
	@RequestMapping(value = "alterarQuantidadeItemCarrinho", method = RequestMethod.POST)
	public void refreshQuantityItemCart(HttpSession session, @RequestParam("codProduto") int codProduto, @RequestParam("qtdProduto") String qtdProduto) {

		Pedido pedido = (Pedido) session.getAttribute("carrinho");
		
		try {
		
			for(ItemPedido ip : pedido.getListaPedidos()) {
				ip.setQuantidade(Integer.parseInt(qtdProduto));
				break;
			}
			
		} catch (Exception e) {
			Logger.getLogger(e.toString());
		}
			
	}
	
	@RequestMapping(value="removerItemCarrinho", method = RequestMethod.POST)
	public void removeItemCart(HttpSession session, @RequestParam("codProduto") int codProduto) {
		
		Pedido pedido = (Pedido) session.getAttribute("carrinho");
		
		try {
			
			for(ItemPedido ip : pedido.getListaPedidos()) {
				
				if (ip.getProduto().getCodProduto() == codProduto) {
					
					int i = pedido.getListaPedidos().indexOf(ip);
					pedido.getListaPedidos().remove(i);
					break;
					
				}
				
			}
			
		} catch (Exception e) {
			Logger.getLogger(e.toString());
		}
		
	}
	
	@RequestMapping(value = "/confirmaCompra")
	public String confirmBuy(HttpSession session) {
		
		Util util = new Util();
		
		
		Pedido pedido = (Pedido) session.getAttribute("carrinho");
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		try {
			session.setAttribute("endereco", util.getEnderecoUsuarioLogado(usuario.getCod_usuario()));
		} catch (Exception e) {
			session.setAttribute("endereco", new Endereco());
		}
		
		
		try {
			
			double sum = 0.0;
			for(ItemPedido ip : pedido.getListaPedidos()) {
				
				sum += ip.getProduto().getValor().doubleValue() * ip.getQuantidade();
				System.out.println(sum);
			}
			
			pedido.setValor_pagoDouble(sum);
			
			if (usuario != null && !usuario.getEmail().equals("")) {
				pedido.setFinalizado(false);
				pedido.setData_compra(new Date());
				
				EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
				EntityManager manager = factory.createEntityManager();
				
				manager.getTransaction().begin();
				manager.persist(pedido);
				manager.getTransaction().commit();
				
				manager.close();
				factory.close();
			}
			
		} catch (Exception e) {
			Logger.getLogger(e.toString());
		}
		
		return (usuario != null && !usuario.getEmail().equals("")) ? "confirmarCompra" : "login";
	}
	
	@RequestMapping(value = "/finalizarCompra")
	public String endBuy(HttpSession session) {
		
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		Pedido pedido = (Pedido) session.getAttribute("carrinho");
		
		try {
			if (!usuario.getEmail().equals("") && usuario != null) {
				pedido.setFinalizado(true);
				
				EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
				EntityManager manager = factory.createEntityManager();
				
				manager.getTransaction().begin();
				manager.merge(pedido);
				manager.getTransaction().commit();
				
				manager.close();
				factory.close();
			}
			
			pedido = new Pedido();
			pedido.setUsuario(usuario);
			pedido.setListaPedidos(new ArrayList<ItemPedido>());
			
			session.setAttribute("carrinho", pedido);
		
		} catch (Exception e) {
			Logger.getLogger(e.toString());
		}
		
		return (usuario != null && !usuario.getEmail().equals("")) ? "redirect:verPedidos" : "login";
	}
	
	@RequestMapping(value = "/cadastrarEndereco")
	public String cadastrarEndereco(HttpSession session) {
		
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		Pedido pedido = (Pedido) session.getAttribute("carrinho");
		
		try {
			session.setAttribute("carrinho", pedido);
		} catch (Exception e) {
			Logger.getLogger(e.toString());
		}
		
		return (usuario != null && !usuario.getEmail().equals("")) ? "cadastrarEndereco" : "login";
	}
	
	@RequestMapping(value = "/finalizarPagSeguro")
	public String finalizarPagSeguro(Address adress, HttpSession session) {
		
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		Pedido pedido = (Pedido) session.getAttribute("carrinho");
		
		try {
			
			adress.setCountry("Brasil");
			session.setAttribute("address", adress);
			
			List<Item> listItemPedido;
			listItemPedido = new ArrayList<Item>();
			
	
			
			for(ItemPedido itemPedido: pedido.getListaPedidos()) {
				Item item = new Item();
				item.setId(String.valueOf(itemPedido.getProduto().getCodProduto()));
				item.setDescription(itemPedido.getProduto().getNome());
				item.setQuantity((int)itemPedido.getQuantidade());
				item.setAmount(itemPedido.getProduto().getValor());
				listItemPedido.add(item);
			   }
			
			session.setAttribute("listItemPedido", listItemPedido);
		} catch (Exception e) {
			Logger.getLogger(e.toString());
		}
		
		return (usuario != null && !usuario.getEmail().equals("")) ? "redirect:pagseguro-criarpagamento" : "login";

	}
	
}