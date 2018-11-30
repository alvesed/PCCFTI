package br.com.whitemarket.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	HttpSession hs;
	
	@RequestMapping(value="/verCarrinho")
	public String cart(HttpSession session, Model model) {
		
		this.hs = session;
		
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
	public void refreshQuantityItemCart(@ModelAttribute("carrinho") Pedido pedido, @RequestParam("codProduto") int codProduto, @RequestParam("qtdProduto") int qtdProduto) {

		for(ItemPedido ip : pedido.getListaPedidos()) {
			if (ip.getProduto().getCodProduto() == codProduto) {
				if (qtdProduto <= 0) {
					removeItemCart(pedido, codProduto);
				}
				
				ip.setQuantidade(qtdProduto);
				break;
			}
		}
	}
	
	@RequestMapping(value="removerItemCarrinho", method = RequestMethod.POST)
	public void removeItemCart(@ModelAttribute("carrinho") Pedido pedido, @RequestParam("codProduto") int codProduto) {
		
		for(ItemPedido ip : pedido.getListaPedidos()) {
			
			if (ip.getProduto().getCodProduto() == codProduto) {
				
				int i = pedido.getListaPedidos().indexOf(ip);
				pedido.getListaPedidos().remove(i);
				break;
				
			}
			
		}
		
	}
	
	@RequestMapping(value = "/confirmaCompra")
	public String confirmBuy(@SessionAttribute("usuarioLogado") Usuario usuario, @ModelAttribute("carrinho") Pedido pedido, Model model) {
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("pedido", pedido);
			
		return "confirmarCompra";
	}
	
	@RequestMapping(value = "")
	public String endBuy(@SessionAttribute("usuarioLogado") Usuario usuario, @ModelAttribute("carrinho") Pedido pedido, Model model) {
		
		if (!usuario.getEmail().equals("") && usuario != null) {
			System.out.println("P");
			System.out.println("A");
			System.out.println("S");
			System.out.println("S");
			System.out.println("O");
			System.out.println("U");
			System.out.println("!");
			pedido.setFinalizado(true);
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
			EntityManager manager = factory.createEntityManager();
			
			manager.getTransaction().begin();
			manager.merge(pedido);
			manager.getTransaction().commit();
			
			manager.close();
			factory.close();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/verificarLogin")
	public Model verifyUserLogin(@SessionAttribute("usuarioLogado") Usuario usuario, Model model) {
		
		model.addAttribute("usuario", usuario);
		
		return model;
	}
	
}