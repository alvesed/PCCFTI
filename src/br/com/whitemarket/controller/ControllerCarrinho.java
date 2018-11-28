package br.com.whitemarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.com.whitemarket.model.Pedido;

@Controller
@SessionAttributes(value = "carrinho")
public class ControllerCarrinho {
	
	@ModelAttribute("carrinho")
	public Pedido retornaCarrinho(){
		return new Pedido();
	}

	@RequestMapping(value = "/")
	public String welcome() {
		return "redirect:telaPrincipal";
	}
	
	@RequestMapping(value="/verCarrinho")
	public String cart(@ModelAttribute("carrinho") Pedido pedido, Model model) {
		
		model.addAttribute("pedido", pedido);
		
		return "cart";
	}
	
	@RequestMapping(value = "/atualizarQuantidadeItemPedido", method = RequestMethod.POST)
	public void atualizarQuantidadeItemPedido (@RequestParam("codItemPedido") int codItemPedido, @RequestParam("qtdItemPedido") int qtdItemPedido) {
		
	}
	
}