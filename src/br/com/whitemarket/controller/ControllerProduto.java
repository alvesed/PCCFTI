package br.com.whitemarket.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.whitemarket.dao.FotoDAO;
import br.com.whitemarket.dao.PedidoDAO;
import br.com.whitemarket.dao.ProdutoDAO;
import br.com.whitemarket.model.Categoria;
import br.com.whitemarket.model.Foto;
import br.com.whitemarket.model.Produto;
import br.com.whitemarket.model.Usuario;


@Transactional
@Controller
public class ControllerProduto {

	@Autowired
	ProdutoDAO dao;
	
	@Autowired
	PedidoDAO daoPedido;

	@Autowired
	FotoDAO daoFoto;
	
	@RequestMapping("/verItemJaCadastrado")
	public String verItemJaCadastrado(String codProduto, Model model) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
    	    EntityManager manager = factory.createEntityManager();
    	    model.addAttribute("produto", manager.find(Produto.class, Long.parseLong(codProduto)));
    	    
    	    manager.close();
    	    factory.close();
    	    return "verProdutoJaCadastrado";

	}
	
	
	@RequestMapping("/cadastrarItem")
	public String itemForm(Produto produto, Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		if (usuario == null || usuario.getEmail().equals("")) {
			return "redirect:login";
		}
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
	    EntityManager manager = factory.createEntityManager();
	    
	    List<Categoria> query = manager.createQuery("SELECT c FROM Categoria c").getResultList();
		model.addAttribute("categorias", query);
		//VERIFICA SE É UMA EDIÇÃO
		if (produto.getCodProduto() != 0) {
    	    manager.getTransaction().begin();
    	    model.addAttribute("produto", manager.find(Produto.class, produto.getCodProduto()));
		}
	    manager.close();
	    factory.close();
	    return "cadastrarItem";
	}
	
	//ADICIONA O PRODUTO NO BANCO AO CLICAR EM 'PRÓXIMO'
	@RequestMapping("/adicionaItem")
	public String addItem(Produto produto, Model model, String idCategoria) {
		produto.setDataCadastro(new Date());
		produto.setAtivo(false);
		produto.setCategoria(new Categoria(Long.valueOf(idCategoria)));
		model.addAttribute("idCategoria", idCategoria);
		if (produto.getCondicao().equals("novo")) {
			produto.setEstadoProduto(null);
		}
		dao.adiciona(produto);
		return "redirect:cadastrarItem?codProduto=" + produto.getCodProduto();
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView uploadFiles(String idCategoria, @RequestParam CommonsMultipartFile file, HttpSession session, Produto produto, String codUser) {
		Date date = new Date();
		String path = "C:\\Users\\FTI\\git\\PCCFTI\\WebContent\\res\\img\\fotosProduto";
		String filename = produto.getCodProduto() + "_" + "foto_" + date.getTime() + file.getOriginalFilename();
		String url = "res/img/fotosProduto";
		System.out.println(path + " " + filename);
		
		try {
			byte[] barr = file.getBytes();
			
			BufferedOutputStream bout = new BufferedOutputStream(
					new FileOutputStream(path + "/" + filename));
			
			Object o = bout;
			bout.write(barr);
			bout.flush();
			bout.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		Foto foto = new Foto();
		foto.setProduto(produto);
		foto.setUrlFoto(url + "/" + filename);
		List<Foto> fotinha = new ArrayList<Foto>();
		fotinha.add(foto);
		produto.setListaFotos(fotinha);
		produto.setDataCadastro(new Date());
		produto.setCategoria(new Categoria(Long.valueOf(idCategoria)));
		produto.setAtivo(true);
		if (produto.getCondicao().equals("novo")) {
			produto.setEstadoProduto(null);
		}
		dao.edita(produto);
		
		return new ModelAndView("redirect:cadastrarItem?codProduto=" + produto.getCodProduto() + "&fotos=" + produto.getQtdFiles());
	}
	
	//ACIONADO AO CLICAR EM 'CONCLUIR'	
	@RequestMapping("/concluirItem")
	public String addSuccess(Produto produto) {
		return "redirect:verProdutos";
	}
	
	//SE O USUARIO CLICAR EM CANCELAR ANTES DE CONCLUIR O PRODUTO, O MESMO É REMOVIDO DO BANCO.
	@SuppressWarnings("unused")
	@RequestMapping("/listaProdutos")
	public String cancelar(long codProduto) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
	    EntityManager manager = factory.createEntityManager();
	    
	    manager.getTransaction().begin();
			Query queryFotos = manager.createQuery("Delete from Foto f WHERE f.produto.codProduto = :id");
			queryFotos.setParameter("id", codProduto);
			int rowsFilhos = queryFotos.executeUpdate();
			Query queryProduto = manager.createQuery("Delete from Produto p WHERE p.codProduto = :id");
			queryProduto.setParameter("id", codProduto);
			int rowsProduto = queryProduto.executeUpdate();
		manager.getTransaction().commit();
		
		manager.close();
		factory.close();
		
		return "redirect:telaPrincipal";
	}

}