package br.com.whitemarket.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerProduto {
	@RequestMapping("/cadastrarItem")
	public String itemForm() {
		/*EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
        EntityManager manager = factory.createEntityManager();
        
        manager.getTransaction().begin();
		manager.getTransaction().commit();
		
		factory.close();
		manager.close();*/
    	return "cadastrarItem";
	}
	
	@RequestMapping("/adicionaItem")
	public String addItem() {
		/*EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
        EntityManager manager = factory.createEntityManager();
        
        manager.getTransaction().begin();
		manager.getTransaction().commit();
		
		factory.close();
		manager.close();*/
    	return "adicionaItem";
	}
}
