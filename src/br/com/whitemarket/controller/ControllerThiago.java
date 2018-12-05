package br.com.whitemarket.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.whitemarket.dao.ItemProdutoDAO;
import br.com.whitemarket.model.Produto;
import br.com.whitemarket.model.Foto;
import br.com.whitemarket.model.Pedido;

@Controller
public class ControllerThiago {
	  
	@Autowired
	ItemProdutoDAO dao;

	@RequestMapping(value = "/telaPrincipal")
	public String welcome(Model model) {
		
		Util util = new Util();

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
		EntityManager	manager	= factory.createEntityManager();
			
		List<Produto> listProdutos =
				   manager.createQuery("select NEW Produto(nome, descricao, condicao, valor, codProduto) from Produto p").getResultList();
			   
			   for(Produto produto: listProdutos) {
				   //if (!util.pegarPrimeiraFoto(produto.getCodProduto()).equals("")) produto.setUrlPrimeiraImagem(util.pegarPrimeiraFoto(produto.getCodProduto()));
			   }


		model.addAttribute("produto", listProdutos);
		   
		manager.close();  
		factory.close();
		
		return "telaInicial";
	}
	
	@RequestMapping(value = "/telaPrincipal/busca", method = RequestMethod.POST, produces = "aplication/JSON")
	public String menu(@RequestParam("busca") String buscarProduto, Model model) {
		System.out.println("PARAMETRO BUSCA ======== ========================= "+ buscarProduto);
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
		EntityManager	manager	= factory.createEntityManager();
			
		List<Produto> listProdutos =
				manager.createQuery("select NEW Produto(descricao) from Produto p where descricao = buscarProduto").getResultList();
		
		   for(Produto produto: listProdutos) {
			   //if (!util.pegarPrimeiraFoto(produto.getCodProduto()).equals("")) produto.setUrlPrimeiraImagem(util.pegarPrimeiraFoto(produto.getCodProduto()));
		   }


		   model.addAttribute("produto", listProdutos);
	   
			manager.close();  
			factory.close();
			
		
		return "telaInicial";
		
	}
		
		
		@RequestMapping(value = "/telaPrincipal/buscaCategoria", method = RequestMethod.POST, produces = "aplication/JSON")
		public String buscaPorCategoria(@RequestParam("buscaCategoria") long buscaCategoria, Model model) {
			System.out.println("PARAMETRO DO BUSCA POR CATEGORIA     "+ buscaCategoria);
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
			EntityManager	manager	= factory.createEntityManager();
				
			// p.produto.categoria - aqui referencia o atributo categoria da model nao a coluna do banco "fk_categoria
			Query query = manager.createQuery("select NEW Produto(nome,descricao,condicao,valor,codProduto) from Produto p where p.categoria.id = :buscaCategoria");
			query.setParameter("buscaCategoria", buscaCategoria);

	
			List<Produto> listProdutos = query.getResultList();
			
			
		   for(Produto produto: listProdutos) {
			   //if (!util.pegarPrimeiraFoto(produto.getCodProduto()).equals("")) produto.setUrlPrimeiraImagem(util.pegarPrimeiraFoto(produto.getCodProduto()));
		   }


		   model.addAttribute("produto", listProdutos);
	   
			manager.close();  
			factory.close();
				
			
			return "telaInicial";
	}
}