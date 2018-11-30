package br.com.whitemarket.controller;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.com.whitemarket.model.ItemPedido;
import br.com.whitemarket.model.Pedido;
import br.com.whitemarket.model.Usuario;

@Controller
@SessionAttributes(value = {"carrinho", "usuarioLogado"})
public class ControllerCarrinho {
	
	@RequestMapping(value="/verCarrinho")
	public String cart(HttpSession session, Model model) {
		
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		Pedido pedido = (Pedido) session.getAttribute("carrinho");
		
		if(usuario != null && !usuario.getEmail().equals("")) {
			model.addAttribute("usuario", usuario);
			model.addAttribute("pedido", pedido);
		} else {
			model.addAttribute("usuario", new Usuario());
		}
		
		return "cart";
	}
	
	@RequestMapping(value = "alterarQuantidadeItemCarrinho", method = RequestMethod.POST)
	public void refreshQuantityItemCart(HttpSession session, @RequestParam("codProduto") int codProduto, @RequestParam("qtdProduto") int qtdProduto) {

		Pedido pedido = (Pedido) session.getAttribute("carrinho");
		
		for(ItemPedido ip : pedido.getListaPedidos()) {
			if (ip.getProduto().getCodProduto() == codProduto) {
				if (qtdProduto <= 0) {
					removeItemCart(session, codProduto);
				}
				
				ip.setQuantidade(qtdProduto);
				break;
			}
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
		
		BigDecimal sum = BigDecimal.valueOf(0.0);
		for(ItemPedido ip : pedido.getListaPedidos()) {
			
			System.out.println(ip.getProduto().getNome());
			
			sum.add(
					ip.getProduto().getValor().multiply(
							BigDecimal.valueOf(
									ip.getQuantidade())));
			
		}
		
		pedido.setValor_pago(sum);
		
		model.addAttribute("pedido", pedido);
			
		return "confirmarCompra";
	}
	
	@RequestMapping(value = "/finalizarCompra")
	public String endBuy(HttpSession session, Model model) {
		
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
		
		return "redirect:verPedidos";
	}
	
	@RequestMapping(value = "/verificarLogin")
	public Model verifyUserLogin(@SessionAttribute("usuarioLogado") Usuario usuario, Model model) {
		
		model.addAttribute("usuario", usuario);
		
		return model;
	}
	
}