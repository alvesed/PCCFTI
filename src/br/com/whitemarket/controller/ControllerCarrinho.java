package br.com.whitemarket.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.whitemarket.model.ItemPedido;
import br.com.whitemarket.model.Pedido;
import br.com.whitemarket.model.Usuario;

@Controller
public class ControllerCarrinho {
	
	@RequestMapping(value="/verCarrinho")
	public String cart(HttpSession session) {
		return "cart";
	}
	
	@RequestMapping(value = "alterarQuantidadeItemCarrinho", method = RequestMethod.POST)
	public void refreshQuantityItemCart(HttpSession session, @RequestParam("codProduto") int codProduto, @RequestParam("qtdProduto") String qtdProduto) {

		Pedido pedido = (Pedido) session.getAttribute("carrinho");
		
		for(ItemPedido ip : pedido.getListaPedidos()) {
			ip.setQuantidade(Integer.parseInt(qtdProduto));
			break;
		}
	}
	
	@RequestMapping(value="removerItemCarrinho", method = RequestMethod.POST)
	public void removeItemCart(HttpSession session, @RequestParam("codProduto") int codProduto) {
		
		Pedido pedido = (Pedido) session.getAttribute("carrinho");
		
		for(ItemPedido ip : pedido.getListaPedidos()) {
			
			if (ip.getProduto().getCodProduto() == codProduto) {
				
				int i = pedido.getListaPedidos().indexOf(ip);
				pedido.getListaPedidos().remove(i);
				break;
				
			}
			
		}
		
	}
	
	@RequestMapping(value = "/confirmaCompra")
	public String confirmBuy(HttpSession session, Model model) {
		
		Pedido pedido = (Pedido) session.getAttribute("carrinho");
		
		double sum = 0.0;
		for(ItemPedido ip : pedido.getListaPedidos()) {
			
			sum += ip.getProduto().getValor().doubleValue() * ip.getQuantidade();
			System.out.println(sum);
		}
		
		pedido.setValor_pagoDouble(sum);
		
		model.addAttribute("pedido", pedido);
			
		return "confirmarCompra";
	}
	
	@RequestMapping(value = "/finalizarCompra")
	public String endBuy(HttpSession session) {
		
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		Pedido pedido = (Pedido) session.getAttribute("carrinho");
		
		if (!usuario.getEmail().equals("") && usuario != null) {
			pedido.setFinalizado(true);
			pedido.setData_compra(new Date());
			
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
		
		return "redirect:verPedidos";
	}
	
	@RequestMapping(value = "/verificarLogin")
	public Model verifyUserLogin(HttpSession session, Model model) {
		
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		
		model.addAttribute("usuario", usuario);
		
		return model;
	}
	
	@RequestMapping(value="/test")
	public String test() {
		return "headerH";
	}
	
}