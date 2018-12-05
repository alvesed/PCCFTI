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
import br.com.whitemarket.dao.JPAPedidoDAO;
import br.com.whitemarket.dao.ProdutoDAO;
import br.com.whitemarket.model.Cupom;
import br.com.whitemarket.model.Foto;
import br.com.whitemarket.model.Produto;
import br.com.whitemarket.model.Usuario;


@Transactional
@Controller
public class ControllerProduto {

	@Autowired
	ProdutoDAO dao;

	@Autowired
	FotoDAO daoFoto;
	
	@RequestMapping("/verItemJaCadastrado")
	public String verItemJaCadastrado(String codProduto, Model model) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
    	    EntityManager manager = factory.createEntityManager();
    	    model.addAttribute("produto", manager.find(Produto.class, Long.parseLong(codProduto)));
    	    return "verProdutoJaCadastrado";

	}
	
	@RequestMapping("/cadastrarItem")
	public String itemForm(Produto produto, Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		if (usuario == null || usuario.getEmail().equals("")) {
			return "redirect:login";
		}
		//VERIFICA SE É UMA EDIÇÃO
		if (produto.getCodProduto() != 0) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
    	    EntityManager manager = factory.createEntityManager();
    	    manager.getTransaction().begin();
    	    model.addAttribute("produto", manager.find(Produto.class, produto.getCodProduto()));
		}
	    return "cadastrarItem";
	}
	
	//ADICIONA O PRODUTO NO BANCO AO CLICAR EM 'PRÓXIMO'
	@RequestMapping("/adicionaItem")
	public String addItem(Produto produto, Model model) {
		produto.setDataCadastro(new Date());
		produto.setAtivo(false);
		dao.adiciona(produto);
		return "redirect:cadastrarItem?codProduto=" + produto.getCodProduto();
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView uploadFiles(@RequestParam CommonsMultipartFile file, HttpSession session, Produto produto, String codUser) {
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
		produto.setAtivo(true);
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
	
	/*
	 * Funções para cadastro de cupons
	 * Laura
	 * 
	 * */
	
	@RequestMapping("/cupom")
	public String formcupom(HttpSession session, Model model) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		JPAPedidoDAO dao = new JPAPedidoDAO();
		 List<Produto> listProdutos = dao.retornaListaProdutosCadastrados(usuario.getCod_usuario());
		 model.addAttribute("produtos", listProdutos);
		 System.out.println("buscou produtos qnt = " + listProdutos.size());
		return "cupom";
	}
	
	
	@RequestMapping("/cadastrarCupom")
	public String cadastrarCupom(Cupom cupom, HttpSession session) {
		System.out.println("testando passagem de dados do form");
		System.out.println(cupom.getDescricao());
		System.out.println(cupom.getCod_cupom());
		System.out.println(cupom.getTipo_desconto());
		System.out.println(cupom.getValor_desconto());
		System.out.println(cupom.getValor_minimo());
		System.out.println(cupom.getValor_percent());
		return "cupom";
	}
	
	
	//"cadastrarCupom"
}