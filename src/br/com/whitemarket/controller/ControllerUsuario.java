package br.com.whitemarket.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.whitemarket.model.Usuario;


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
	public String auth() {
		
		return "login";
	}

}
