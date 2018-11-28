package br.com.whitemarket.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.whitemarket.model.Produto;

@Controller
public class ControllerProduto {
	@RequestMapping("/cadastrarItem")
	public String itemForm(Produto produto, Model model) {
		if (produto.getCodProduto() > 0) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("cadastro");
    	    EntityManager manager = factory.createEntityManager();
    	    manager.getTransaction().begin();
    	    model.addAttribute("produto", manager.find(Produto.class, produto.getCodProduto()));
    	    return "cadastrarItem";
		}
    	return "cadastrarItem";
	}
	
	@RequestMapping("/adicionaItem")
	public String addItem(Produto produto) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
        EntityManager manager = factory.createEntityManager();
        
        manager.getTransaction().begin();
        manager.persist(produto);
		manager.getTransaction().commit();
		
		factory.close();
		manager.close();
		return "redirect:cadastrarItem";
	}
}
