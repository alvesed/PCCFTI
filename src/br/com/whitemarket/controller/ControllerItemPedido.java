package br.com.whitemarket.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.com.whitemarket.dao.ItemProdutoDAO;
import br.com.whitemarket.model.ItemPedido;
import br.com.whitemarket.model.Pedido;
import br.com.whitemarket.model.Produto;
import br.com.whitemarket.model.Usuario;

import java.util.ArrayList;

@Transactional
@Controller
@SessionAttributes(value = {"carrinho", "usuario"})
public class ControllerItemPedido {
	
	@Autowired
	ItemProdutoDAO dao;
	
	@ModelAttribute("usuario")
	public Usuario retornaUsuario(){
		Usuario u = new Usuario();
		u.setCod_usuario(1);
		return u;
	}
	
	
	@ModelAttribute("carrinho")
	public Pedido retornaCarrinho(){
		return new Pedido();
	}
	
	@RequestMapping("verProduto")
	public String mostraProduto(@RequestParam String codigoProduto, Model model) {
		Produto p = dao.buscaPorCodigo(Long.parseLong(codigoProduto));
		
		if (p.getCodProduto() == 0) {
			return "produto404";
		}
		
		model.addAttribute("produto", p);
		model.addAttribute("fotos", p.getListaFotos());
		
		return "mostraProduto";
	}
	
	@RequestMapping(value="adicionaCarrinho", method=RequestMethod.POST)
	public @ResponseBody String adicionaAoCarrinho(@ModelAttribute("carrinho") Pedido pedido, @ModelAttribute("usuario") Usuario usuario, String codProduto, String quantidade, String nome, String url, Model model) {
		
		int quant = Integer.parseInt(quantidade);
		
		Produto p = new Produto();
		p.setCodProduto(Long.parseLong(codProduto));
		p.setNome(nome);
		p.setDescricao(url);
		
		ItemPedido i = new ItemPedido();
		i.setPedido(pedido);
		i.setProduto(p);
		i.setQuantidade(quant);
		
		if(pedido != null) {
			if(pedido.getListaPedidos() != null) {
				boolean naoAchou = true;
				
				for (ItemPedido iP : pedido.getListaPedidos()) {
					if (iP.getProduto().getCodProduto() == Long.parseLong(codProduto)) {
						iP.setQuantidade(iP.getQuantidade() + quant);
						naoAchou = false;
						break;
					}
				}
				if(naoAchou) {
					pedido.getListaPedidos().add(i);
				}
			} else {
				pedido.setListaPedidos(new ArrayList<ItemPedido>());
				pedido.getListaPedidos().add(i);
			}
		}
		model.addAttribute("carrinho", pedido);
		
		return "adicionado";
	}
}