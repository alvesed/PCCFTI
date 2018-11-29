package br.com.whitemarket.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.whitemarket.dao.JPAUsuarioDAO;
import br.com.whitemarket.model.Usuario;
import javax.servlet.http.HttpSession;


@Controller
public class ControllerUsuario {
	
	@RequestMapping("/cadastrarCliente")
	public String cadastrarCliente() {

    	return "cadastrarCliente";
   	
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

    	return "cadastrarCliente";
    	
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
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
		
		System.out.println("entrou no controller com email = " + email);
		
		usuario = manager.createQuery(
				  "SELECT u from Usuario u WHERE u.email = :email and u.senha = :senha", Usuario.class).
				  setParameter("email", email).setParameter("senha", senha).getSingleResult();
		
		if(usuario.getCod_usuario() == 0) {
			return "login";
		}
		
		System.out.println("Retornou do banco usuario = " + usuario.getNome());
		
		model.addAttribute("usuarioLogado", usuario);
		session.setAttribute("usuarioLogado",	usuario);
		
		manager.close();  
		factory.close();
		
		return "redirect:telaPrincipal";
	}

}
