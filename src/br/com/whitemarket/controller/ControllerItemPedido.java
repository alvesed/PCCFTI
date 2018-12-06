package br.com.whitemarket.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.whitemarket.dao.ComentarioDAO;
import br.com.whitemarket.dao.ItemProdutoDAO;
import br.com.whitemarket.model.Comentario;
import br.com.whitemarket.model.ItemPedido;
import br.com.whitemarket.model.Pedido;
import br.com.whitemarket.model.Produto;
import br.com.whitemarket.model.Usuario;

@Transactional
@Controller
public class ControllerItemPedido {
	


	@Autowired
	ComentarioDAO daoComentario;
	
	@Autowired
	ItemProdutoDAO dao;	
	
	@RequestMapping("verProduto")
	public String mostraProduto(@RequestParam String codigoProduto, Model model, HttpSession session) {
		Util util =new Util();
		
		Produto p = dao.buscaPorCodigo(Long.parseLong(codigoProduto));
		
		if (p.getCodProduto() == 0) {
			return "produto404";
		}
		
		List<Comentario> comentario = daoComentario.listaComentarioPorCodigo(Long.parseLong(codigoProduto));
		
		for (int i = 0; i < comentario.size(); i++) {
			comentario.get(i).setDataAposComentario(util.diasAposComentario(comentario.get(i).getData_comentario()));
		}
		
		
		session.setAttribute("produto", p);

		model.addAttribute("fotos", p.getListaFotos());
		model.addAttribute("comentario", comentario);
		
		return "mostraProduto";
	}
	
	@RequestMapping("adicionarComentario")
	public String addComentario(String codProduto , Comentario comentario, HttpSession session, Model model) { //Comentario que vem do jsp e verificar se esta logado ou nao
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		if (usuario == null || usuario.getEmail().equals("")) {
			return "redirect:login";
		} else {
			Produto produto = new Produto();
			produto.setCodProduto(Long.parseLong(codProduto));
			comentario.setProduto(produto);
			comentario.setUsuario(usuario);
			comentario.setData_comentario(new Date());
			
			daoComentario.adicionaComentario(comentario);
			
			
			model.addAttribute("codigoProduto", codProduto);
			return "redirect:verProduto";
		}
		

	}
	
	
	@RequestMapping(value="adicionaCarrinho", method=RequestMethod.POST)
	public @ResponseBody String adicionaAoCarrinho(HttpSession session, String codProduto, String quantidade, String nome, String url, Model model) {
		Pedido pedido = (Pedido) session.getAttribute("carrinho");
		Produto p = (Produto) session.getAttribute("produto");
		
		session.removeAttribute("produto");
		
		int quant = Integer.parseInt(quantidade);
		
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