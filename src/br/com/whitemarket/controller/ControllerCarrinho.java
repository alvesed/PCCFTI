package br.com.whitemarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sun.glass.ui.Application;

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
	
	@RequestMapping(value = "alterarQuantidadeItemCarrinho", method = RequestMethod.POST)
	public void refreshQuantityItemCart(@ModelAttribute("carrinho") Pedido pedido, @RequestParam("codProduto") int codProduto, @RequestParam("qtdProduto") int qtdProduto) {
		
		System.out.println(codProduto);
		System.out.println(qtdProduto);
		
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
	public String confirmBuy(@ModelAttribute("carrinho") Pedido pedido, Model model) {
		
		model.addAttribute("pedido", pedido);
		
		return "confirmarCompra";
	}
	
	@RequestMapping(value = "/verificarLogin")
	public Model verifyUserLogin(@ModelAttribute("usuarioLogado") Usuario usuario, Model model) {
		
		model.addAttribute("usuario", usuario);
		
		return model;
	}
	
}