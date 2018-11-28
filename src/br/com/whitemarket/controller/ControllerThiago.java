package br.com.whitemarket.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.whitemarket.model.Produto;

@Controller
public class ControllerThiago {

	
	@RequestMapping("/telaPrincipal")
	public String menu(Model model) {


				EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
				EntityManager	manager	= factory.createEntityManager();
				

				   List<Produto> produto = manager.createQuery("SELECT p FROM Produto p").getResultList();
				   
				   model.addAttribute("produto", produto);
				   
				   manager.close();  
				   factory.close();
			
		
		
		
		
    	return "telaInicial";
	}
	
	
}
