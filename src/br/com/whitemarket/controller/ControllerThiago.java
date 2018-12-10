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
import br.com.whitemarket.model.Categoria;
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
				   produto.setUrlPrimeiraImagem(util.pegarPrimeiraFoto(produto.getCodProduto()));
			   }


		model.addAttribute("produto", listProdutos); 
		
		List<Categoria> listCategorias = manager.createQuery("select c from Categoria c").getResultList();
		model.addAttribute("listaCategorias", listCategorias);
		
		
		
		   
		manager.close();  
		factory.close();
		
		return "telaInicial";
	}
	
	@RequestMapping(value = "/filtrarPorCategoria")
	public String filtrarPorCategoria(@RequestParam("idCategoria") String idCategoria, String ordenar, Model model) {
					
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
			EntityManager	manager	= factory.createEntityManager();
				
			Query query = null;
			
			String titulo = "Novos Produtos";
			
			System.out.println("oq ta vindo no ordenar é :  " + ordenar + "e no id categoria é :  " + idCategoria + " .");
			
			
			
			long idCategoriaLong = 0;
			if (idCategoria != null && !idCategoria.equalsIgnoreCase("")) {
				idCategoriaLong = Long.parseLong(idCategoria);
			}
				 
			
			
			if (idCategoriaLong == 0 && (ordenar == null || ordenar.equals(""))) {
				
				query = manager.createQuery("select NEW Produto(nome,descricao,condicao,valor,codProduto) from Produto");
			
			}else if(idCategoriaLong != 0 && ordenar == null) {
				
				query = manager.createQuery("select NEW Produto(nome,descricao,condicao,valor,codProduto) from Produto p where p.categoria.id = :idCategoria");
				query.setParameter("idCategoria", idCategoria);
					
			}else if(idCategoriaLong == 0 && ordenar == "menor_preco") {
			
				query = manager.createQuery("select NEW Produto(nome,descricao,condicao,valor,codProduto) from Produto p ORDER BY valor desc");
				titulo = "Produtos com Menor Preço";		
			
				
			}else if(idCategoriaLong == 0 && ordenar == "maior_preco") {
				
				query = manager.createQuery("select NEW Produto(nome,descricao,condicao,valor,codProduto) from Produto p ORDER BY valor asc");
				titulo = "Produtos com Maior Preço";	
				

			}else if(ordenar.equals("mais_vendido")) {
				
				titulo = "Produtos Mais Vendidos";
				query = manager.createQuery("select NEW Produto(valor," +
				   		" (SELECT " + 
				   		" count(i.quantidade) as quantidades " + 
				   		" FROM ItemPedido i "
				   		+ " WHERE i.produto.codProduto = p.codProduto " + 
				   		" ) as quantidadeDeVendas, dataCadastro, codProduto, usuario) from Produto p "
				   		+ "WHERE p.ativo = 1" // "WHERE p.ativo = 1 AND p.categoria.id ="  PARA FAZER A PROXIMA QUERY
				   		+ "ORDER BY quantidadeDeVendas desc");
			
			
			
			// quando categoria e ordenar preenchidos
			
			} else if(idCategoriaLong != 0 && ordenar.equalsIgnoreCase("menor_preco")) {
				query = manager.createQuery("select NEW Produto(nome,descricao,condicao,valor,codProduto) from Produto p where p.categoria.id = :idCategoria ORDER BY valor desc");
				query.setParameter("idCategoria", idCategoria);
			
			}
			
			

			List<Produto> listProdutos = query.getResultList();

			
			Util util = new Util();
		   for(Produto produto: listProdutos) {
			   //if (!util.pegarPrimeiraFoto(produto.getCodProduto()).equals("")) produto.setUrlPrimeiraImagem(util.pegarPrimeiraFoto(produto.getCodProduto()));
		   }

		   model.addAttribute("titulo",titulo);
		   model.addAttribute("produto", listProdutos);

		   // Esse codigo esta sendo replicado para que quando ele selecionar um categoria e visualizar os seus itens ele remontar essa esse menu novamente!
		   List<Categoria> listCategorias = manager.createQuery("select c from Categoria c").getResultList();
			model.addAttribute("listaCategorias", listCategorias);

			
			//id para fazer o filtro de menor preço
			model.addAttribute("idCategoria", idCategoria); // chave || categoria
			
			manager.close();  
			factory.close();
				
			
			return "telaInicial";
	}
	
	
	@RequestMapping(value = "/filtrarPorMenorValor")
	public String filtrarPorMenorValor(Model model) {
					
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
			EntityManager	manager	= factory.createEntityManager();
				
			Query query = manager.createQuery("select NEW Produto(nome,descricao,condicao,valor,codProduto) from Produto as p order by p.valor asc");
	
			List<Produto> listProdutos = query.getResultList();
			
			Util util = new Util();
		   for(Produto produto: listProdutos) {
			   if (!util.pegarPrimeiraFoto(produto.getCodProduto()).equals("")) produto.setUrlPrimeiraImagem(util.pegarPrimeiraFoto(produto.getCodProduto()));
		   }

		   model.addAttribute("produto", listProdutos);

			manager.close();  
			factory.close();
		
			return "telaInicial";
	}
}