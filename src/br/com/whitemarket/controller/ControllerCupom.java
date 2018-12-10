package br.com.whitemarket.controller;

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

import br.com.whitemarket.dao.CupomDAO;
import br.com.whitemarket.dao.PedidoDAO;
import br.com.whitemarket.model.Cupom;
import br.com.whitemarket.model.Produto;
import br.com.whitemarket.model.Usuario;

@Transactional
@Controller
public class ControllerCupom {

	@Autowired
	CupomDAO dao;
	
	@Autowired
	PedidoDAO daoPedido;
	
	@RequestMapping(value = "/verificaCupom", method = RequestMethod.POST)
	@ResponseBody String verificaCupom(@RequestParam("codCupom") String strCupom) {
		
		Cupom cupom = dao.encontrarCupom(strCupom);
		Date date = cupom.getData_expiracao();
		Date actualDate = new Date();
		
		if(cupom != null) {
			if(cupom.getCod_cupom() != null) {
				if(cupom.getQnt_cupons() > 0) {
					if(actualDate.before(date)) {
						return "valid";
					}
				}
				return "expirated";
			}
		}
		return "invalid";
	}
	
	
	
	
	/*
	 * Funções para cadastro de cupons
	 * Laura
	 * 
	 * */
	
	
	@RequestMapping("/cupom")
	public String formcupom(HttpSession session, Model model) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		
		long codigo = usuario.getCod_usuario();
		Util util = new Util();
		//JPAPedidoDAO dao = new JPAPedidoDAO();
		
		try {
			 List<Produto> listProdutos = daoPedido.retornaListaProdutosCadastrados(codigo);
			   for(Produto produto: listProdutos) {
				   produto.setUrlPrimeiraImagem(util.pegarPrimeiraFoto(produto.getCodProduto()));
			   }
			   
			   model.addAttribute("listProdutos", listProdutos);
			 System.out.println("buscou produtos qnt = " + listProdutos.size());
			
		} catch (Exception e) {
			System.out.println("caiu no catch cupom");
		}
		

		return "cupom";
	}
	
	
	@RequestMapping("/cadastrarCupom")
	public String cadastrarCupom(Cupom cupom, HttpSession session) {
		System.out.println("testando passagem de dados do form");
		System.out.println(cupom.getDescricao());
		System.out.println(cupom.getCod_cupom());
		System.out.println(cupom.getTipo_desconto());
		System.out.println(cupom.getValor_desconto());
		System.out.println(cupom.getValor_minimo());
		System.out.println(cupom.getValor_percent());
		
		
		
		dao.adicionaCupom(cupom);
		
		
		
		return "cupom";
	}
	
	
	//"cadastrarCupom"
	
}
