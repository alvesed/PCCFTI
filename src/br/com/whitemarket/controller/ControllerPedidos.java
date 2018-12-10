package br.com.whitemarket.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.whitemarket.dao.PedidoDAO;
import br.com.whitemarket.model.Avaliacao;
import br.com.whitemarket.model.Foto;
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
	
	@RequestMapping(value="/mostraPedido")
	public String verPedidoFechado(HttpSession session, Model model, String cod_pedido) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		
		if (usuario == null || usuario.getEmail().equals("")) {
			return "login";
		}
		
		Pedido pedido = dao.retornaPedidoCadastrado(Long.parseLong(cod_pedido));
		
		if(pedido == null) {
			return "pedido404";
		}
		
		Collections.sort(pedido.getListaPedidos());
		
		ArrayList<ListaItensDoVendedor> listaDeVendedores = new ArrayList<ListaItensDoVendedor>();
		
		String[] fotos = new String[pedido.getListaPedidos().size()];

		ItemPedido itemPedido = pedido.getListaPedidos().get(0);
		Usuario vendedor = pedido.getListaPedidos().get(0).getProduto().getUsuario();
		ListaItensDoVendedor itensDoVendedor = new ListaItensDoVendedor(vendedor);
		List<Foto> foto = dao.retornaPrimeiraFoto(itemPedido.getProduto().getCodProduto());
		
		fotos[0] = foto.get(0).getUrlFoto();
		
		itensDoVendedor.getListaItemPedido().add(itemPedido);
		
		for (int i = 1; i < pedido.getListaPedidos().size(); i++) {
			itemPedido = pedido.getListaPedidos().get(i);
			foto = dao.retornaPrimeiraFoto(itemPedido.getProduto().getCodProduto());
			
			fotos[i] = foto.get(0).getUrlFoto();
						
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
		
		List<Avaliacao> listaAvaliacoes = dao.retornaVendedoresAvalidosNoPedido(pedido.getCod_pedido(), usuario.getCod_usuario());
		
		for(Avaliacao a : listaAvaliacoes) {
			for (ListaItensDoVendedor listaVendedor : listaDeVendedores) {
				long codVendedorAvaliado = a.getVendedor().getCod_usuario();
				long codVendedorDaVez = listaVendedor.getVendedor().getCod_usuario();
				
				if(codVendedorAvaliado == codVendedorDaVez) {
					listaVendedor.setAvaliado(true);
				}
			}
		}
		
		model.addAttribute("lista", listaDeVendedores);
		model.addAttribute("foto", fotos);
		model.addAttribute("codPedido", pedido.getCod_pedido());
		
		return "mostraPedido";
	}
	
	//BOTÃO GERAR PDF. Lucas
	@RequestMapping("/gerarPdf")
	public String gerarPdf(Model model, HttpSession session, @RequestParam("cod_pedido") long codPedido, HttpServletResponse response, HttpServletRequest request) throws IOException {
		Util util = new Util();
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
				
		if(usuario == null) {
			return "login";
		}
				
		GerarPdfUtil.gerarPdf(dao.retornaProdutosDentroDePedido(codPedido), util.getEnderecoUsuarioLogado(usuario.getCod_usuario()));
		
		File file = new File("C:\\PDF_Teste.pdf");
		
		InputStream inputS = new BufferedInputStream(new FileInputStream(file));
		String mimeType = URLConnection.guessContentTypeFromStream(inputS);
		
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}
		
		response.setContentType(mimeType);
		response.setContentLength((int)file.length());
		response.setHeader("Content-Disposition", String.format("attachment-filename=\"%s\"", file.getName()));
		
		FileCopyUtils.copy(inputS, response.getOutputStream());
		
		return "redirect:verPedidos";
	}
		
}