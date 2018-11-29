package br.com.whitemarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.com.whitemarket.model.ItemPedido;
import br.com.whitemarket.model.Pedido;
import br.com.whitemarket.model.Usuario;

@Controller
@SessionAttributes(value = {"carrinho", "usuarioLogado"})
public class ControllerCarrinho {
	
	@ModelAttribute("carrinho")
	public Pedido retornaCarrinho(){
		return new Pedido();
	}
	
	@ModelAttribute("usuarioLogado")
	public Usuario retornaUsuario(){
		return new Usuario();
	}
	
	@RequestMapping(value="/verCarrinho")
	public String cart(@ModelAttribute("carrinho") Pedido pedido, @ModelAttribute("usuarioLogado") Usuario usuario, Model model) {
		
		model.addAttribute("pedido", pedido);
		model.addAttribute("usuario", usuario);
		
		return "cart";
	}
	
	@RequestMapping(value = "/atualizarQuantidadeItemPedido", method = RequestMethod.POST)
	public void atualizarQuantidadeItemPedido (@ModelAttribute("carrinho") Pedido pedido, @RequestParam("codProduto") int codProduto, @RequestParam("qtdProduto") int qtdProduto) {
				
		for(ItemPedido ip : pedido.getListaPedidos()) {
			if (ip.getProduto().getCodProduto() == codProduto) {
				ip.setQuantidade(qtdProduto);
				break;
			}
		}
	}
	
	@RequestMapping(value = "/confirmaCompra")
	public String confirmBuy(@ModelAttribute("carrinho") Pedido pedido, Model model) {
		
		model.addAttribute("pedido", pedido);
		
		return "confirmarCompra";
	}
	
}