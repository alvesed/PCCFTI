package br.com.whitemarket.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.whitemarket.dao.UsuarioDAO;
import br.com.whitemarket.model.Avaliacao;
import br.com.whitemarket.model.Endereco;
import br.com.whitemarket.model.Pedido;
import br.com.whitemarket.model.Usuario;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Transactional
@Controller
public class ControllerUsuario {
	
	@Autowired
	UsuarioDAO dao;
	
	@RequestMapping("/cadastrarCliente")
	public String cadastrarCliente() {

    	return "cadastrarCliente";
   	
	}
	
	@RequestMapping(value="/main")
	public String see() {
		return "telaInicial2";
	}
	
	@RequestMapping("/efetivarCadastroCliente")
	public String itemForm(Usuario usuario, Endereco endereco) {
		
		dao.cadastraUsuario(usuario, endereco);

    	return "login";
    	
	}
	
	
	/*
	 * Funções para login
	 * Laura
	 * 
	 * */
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("usuarioLogado");
		session.removeAttribute("carrinho");
		return "redirect:telaPrincipal";
	}
	
	@RequestMapping("/autenticacao")
	public String auth(@RequestParam String email, @RequestParam String senha, Model model, HttpSession session) {
//		JPAUsuarioDAO user = new JPAUsuarioDAO();
//		
//		System.out.println("o que veio pelo form é: " + senha);
//		user.autentica(email, senha);

		Usuario usuario = new Usuario();
		
		//System.out.println("entrou no controller com email = " + email);
		
		try {
			System.out.println("entrou no try");
			usuario = dao.autenticaLogin(email, senha);
			if(usuario.getCod_usuario() == 0) {
				model.addAttribute("erroLogin", "Usuário não encontrado.");
				return "login";
			}
			
			//System.out.println("Retornou do banco usuario = " + usuario.getNome());
			
			//model.addAttribute("usuarioLogado", usuario);
			session.setAttribute("usuarioLogado",	usuario);
			
			Pedido pedido = new Pedido();
			pedido.setUsuario(usuario);
			
			//model.addAttribute("carrinho", pedido);
			session.setAttribute("carrinho", pedido);
			
			return "redirect:telaPrincipal";
			
		} catch (Exception e) {
			model.addAttribute("erroLogin", "Usuário ou Senha Inválidos");
			return "login";
		}

	}
	
	@RequestMapping(value = "/cadastrarCliente/busca", method = RequestMethod.POST, produces = "aplication/JSON")
	public String menu(@RequestParam("verificar_email") String verificar_email) {
		System.out.println("VER SE ESTA CHEGANDO ======= "+ verificar_email);
			
		List<String> emails = new ArrayList<String>();
		emails = dao.buscaEmailsCadastrado(verificar_email);
				
		
		for (String email_verificar : emails ) {
			
		}		
		
		return "emails";
}
	
	@RequestMapping(value="avaliarVendedor", method = RequestMethod.POST)
	public @ResponseBody String avaliaVendedor(HttpSession session, String codPedido, String codVendedor, String nota) {
		Avaliacao a = new Avaliacao();
		
		Usuario comprador = (Usuario) session.getAttribute("usuarioLogado");
		Usuario vendedor = new Usuario();
		Pedido pedido = new Pedido();
		
		vendedor.setCod_usuario(Long.parseLong(codVendedor));
		pedido.setCod_pedido(Long.parseLong(codPedido));	
		
		a.setComprador(comprador);
		a.setVendedor(vendedor);
		a.setPedido(pedido);
		a.setNota(Integer.parseInt(nota));
		
		dao.avaliarVendedor(a);
		
		return "Usuário avaliado com nota " + a.getNota();
	}
	
}
	
