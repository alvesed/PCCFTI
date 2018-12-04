package br.com.whitemarket.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.whitemarket.dao.JPAUsuarioDAO;
import br.com.whitemarket.model.Pedido;
import br.com.whitemarket.model.Usuario;
import javax.servlet.http.HttpSession;


@Controller
public class ControllerUsuario {
	
	@RequestMapping("/cadastrarCliente")
	public String cadastrarCliente() {

    	return "cadastrarCliente";
   	
	}
	
	@RequestMapping(value="/main")
	public String see() {
		return "telaInicial2";
	}
	
	@RequestMapping("/efetivarCadastroCliente")
	public String itemForm(Usuario usuario) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
        EntityManager manager = factory.createEntityManager();
        
        manager.getTransaction().begin();
        manager.persist(usuario);
		manager.getTransaction().commit();
		
		manager.close();
		factory.close();

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
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
		EntityManager	manager	= factory.createEntityManager();
		Usuario usuario = new Usuario();
		
		//System.out.println("entrou no controller com email = " + email);
		try {
			System.out.println("entrou no try");
			usuario = manager.createQuery(
				  "SELECT u from Usuario u WHERE u.email = :email and u.senha = :senha", Usuario.class).
				  setParameter("email", email).setParameter("senha", senha).getSingleResult();
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
			
			manager.close();  
			factory.close();
			
			return "redirect:telaPrincipal";
			
		} catch (Exception e) {
			model.addAttribute("erroLogin", "Usuário ou Senha Inválidos");
			return "login";
		}
		
		

	}
	

	
	
	
	
//	@RequestMapping("/verificarLoginRepetido")
//	public String verificarLoginRepetido(String valor) {
//		
//		Connection conn = null;
//		Statement stmt = null;
//
//		try {
//			conn = db.obterConexao();
//
//			String sql = "SELECT * FROM usuario WHERE email = verificar_email";
//			
//			stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//			
//			}
//
//		} catch (SQLException e) {
//			System.out.println("Erro ao consultar");
//			e.printStackTrace();
//		}
//		
//		return verificar_email;
//		
//    	
//	}
	

}
