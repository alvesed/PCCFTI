package br.com.whitemarket.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.whitemarket.model.Foto;
import br.com.whitemarket.model.Pedido;
import br.com.whitemarket.model.Produto;

public class Util {
	public boolean temErroProduto(Produto produto) {
		if (produto.getNome().isEmpty()) {
			return true;
		} else if (produto.getDescricao().isEmpty()) {
			return true;
		} else if (produto.getListaFotos().size() < 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public BigDecimal removeMascaraValor(String valor) {
		BigDecimal money;
		if (valor.contains(",") || valor.contains(".")) {
			valor = valor.replace(".", "");
			valor = valor.replace(",", ".");
		}
		money = new BigDecimal(valor);
		return money;
	}
	
	
	public String pegarPrimeiraFoto(long codigo) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
		EntityManager	manager	= factory.createEntityManager();
		
		TypedQuery<Foto> a = manager.createQuery("SELECT f FROM Foto f WHERE f.produto.codProduto = :codigo", Foto.class);
		a.setParameter("codigo", codigo);
		List<Foto> foto = a.getResultList();
				
				
		

		   manager.close();  
		   factory.close();
		  return foto.get(0).getUrlFoto();
		
	}
}
