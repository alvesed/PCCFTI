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
	
	
	@RequestMapping(value = "busca")
	public String busca(@RequestParam String busca, Model model) {
		
		Util util = new Util();

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
		EntityManager	manager	= factory.createEntityManager();
			
		List<Produto> listProdutos =
				   manager.createQuery("select NEW Produto(nome, descricao, condicao, valor, codProduto) from Produto p where p.nome LIKE :busca").setParameter("busca", "%" + busca + "%").getResultList();
			   
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

			System.out.println("idCategoria  :" + idCategoria + "  ordenar   :" + ordenar);
			
			long idCategoriaLong = 0;
			
			if (idCategoria != null && !idCategoria.equalsIgnoreCase("")) {
				idCategoriaLong = Long.parseLong(idCategoria);
				
				System.out.println("na condição" + idCategoriaLong);
				
			}else {
				idCategoriaLong = 0;
			}
			
			if (ordenar == null) {
				ordenar = "";
			}
				 
			if (idCategoriaLong == 0 && ordenar.equals("") ) {
				
				query = manager.createQuery("select NEW Produto(nome,descricao,condicao,valor,codProduto) from Produto");
			
			}else if(idCategoriaLong != 0 && ordenar.equals("maior_preco")) {
				
				query = manager.createQuery("select NEW Produto(nome,descricao,condicao,valor,codProduto) from Produto p where p.categoria.id = :idCategoriaLong ORDER BY valor desc");
				query.setParameter("idCategoriaLong", idCategoriaLong); // nao esta setando aqui
					
				
			}else if(idCategoriaLong != 0 && (ordenar == null || ordenar.equals(""))) {
				
				query = manager.createQuery("select NEW Produto(nome,descricao,condicao,valor,codProduto) from Produto p where p.categoria.id = :idCategoriaLong");
				query.setParameter("idCategoriaLong", idCategoriaLong); // nao esta setando aqui
					
				
			}else if(idCategoriaLong == 0 && ordenar.equals("menor_preco")) {
			
				System.out.println(" Dentro da condição    -     idCategoria  :" + idCategoria + "  ordenar   :" + ordenar);
		
				query = manager.createQuery("select NEW Produto(nome,descricao,condicao,valor,codProduto) from Produto p ORDER BY valor asc");
				titulo = "Produtos com Menor Preço";		
			
				
			}else if(idCategoriaLong == 0 && ordenar.equals("maior_preco")) {
				System.out.println(" Dentro da condição    -     idCategoria  :" + idCategoria + "  ordenar   :" + ordenar);
				
				query = manager.createQuery("select NEW Produto(nome,descricao,condicao,valor,codProduto) from Produto p ORDER BY valor desc");
				titulo = "Produtos com Maior Preço";	
				

			}else if(idCategoriaLong == 0 && ordenar.equals("mais_vendido")) {
				
				titulo = "Produtos Mais Vendidos";
				query = manager.createQuery("select NEW Produto(valor," +
				   		" (SELECT " + 
				   		" count(i.quantidade) as quantidades " + 
				   		" FROM ItemPedido i "
				   		+ " WHERE i.produto.codProduto = p.codProduto " + 
				   		" ) as quantidadeDeVendas, dataCadastro, codProduto, usuario, p.nome) from Produto p "
				   		+ "WHERE p.ativo = 1 " // "WHERE p.ativo = 1 AND p.categoria.id ="  PARA FAZER A PROXIMA QUERY
				   		+ "ORDER BY quantidadeDeVendas desc");
				
				
				
				
			}else if(idCategoriaLong !=0 && ordenar.equals("mais_vendido")) {
				
				titulo = "Produtos Mais Vendidos";
				query = manager.createQuery("select NEW Produto(valor," +
				   		" (SELECT " + 
				   		" count(i.quantidade) as quantidades " + 
				   		" FROM ItemPedido i "
				   		+ " WHERE i.produto.codProduto = p.codProduto " + 
				   		" ) as quantidadeDeVendas, dataCadastro, codProduto, usuario, p.nome) from Produto p "
				   		+ "WHERE p.ativo = 1 AND p.categoria.id =:idCategoriaLong "
				   		+ "ORDER BY quantidadeDeVendas desc");
				
				query.setParameter("idCategoriaLong", idCategoriaLong);
			
			// quando categoria e ordenar preenchidos
			
			} else if(idCategoriaLong != 0 && ordenar.equalsIgnoreCase("menor_preco")) {  
				
				query = manager.createQuery("select NEW Produto(nome,descricao,condicao,valor,codProduto) from Produto p where p.categoria.id = :idCategoriaLong ORDER BY valor asc");			
				query.setParameter("idCategoriaLong", idCategoriaLong);
	
			}else if(idCategoriaLong != 0 && ordenar.equalsIgnoreCase("maior_preco")){
				
				query = manager.createQuery("select NEW Produto(nome,descricao,condicao,valor,codProduto) from Produto p where p.categoria.id = :idCategoriaLong ORDER BY valor desc");			
				query.setParameter("idCategoriaLong", idCategoriaLong);
				
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
}