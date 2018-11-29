package br.com.whitemarket.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.whitemarket.dao.ItemProdutoDAO;
import br.com.whitemarket.model.Produto;
import br.com.whitemarket.model.Foto;

@Controller
public class ControllerThiago {
	
	@Autowired
	ItemProdutoDAO dao;

	@RequestMapping(value = "/")
	public String welcome() {
		return "redirect:telaPrincipal";
	}
	
	@RequestMapping("/telaPrincipal")
	public String menu(Model model) {
		Util util = new Util();

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
		EntityManager	manager	= factory.createEntityManager();
			
		List<Produto> listProdutos =
				   manager.createQuery("select NEW Produto(valor," +
					   		" (SELECT " + 
					   		" count(i.quantidade) as quantidades " + 
					   		" FROM ItemPedido i "
					   		+ " WHERE i.produto.codProduto = p.codProduto " + 
					   		" ), dataCadastro, codProduto) from Produto p").getResultList();
			   
			   for(Produto produto: listProdutos) {
				   System.out.println("TESTE" + produto.getCodProduto());
				   produto.setUrlPrimeiraImagem(util.pegarPrimeiraFoto(produto.getCodProduto()));
			   }


		model.addAttribute("produto", listProdutos);
		   
		manager.close();  
		factory.close();

    	return "telaInicial";
	}
}
