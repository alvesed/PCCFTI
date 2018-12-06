package br.com.whitemarket.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

import br.com.whitemarket.model.Foto;
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

	public String diasAposComentario(Date data) {


		String dataAposComentario = new String();
		
		Date d1 = data;

		Date d2 = new Date();
		
		System.out.println(d1);
		System.out.println(d2);

		try {

			DateTime dt1 = new DateTime(d1);
			DateTime dt2 = new DateTime(d2);

			System.out.println(Days.daysBetween(dt1, dt2).getDays() + " days, ");
			System.out.println((Hours.hoursBetween(dt1, dt2).getHours() % 24) + " hours, ");
			System.out.println(Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 + " minutes, ");
			System.out.println(Seconds.secondsBetween(dt1, dt2).getSeconds() % 60 + " seconds");
			
			
			if((Days.daysBetween(dt1, dt2).getDays() > 0)){
				dataAposComentario = Days.daysBetween(dt1, dt2).getDays() + " days ";
			} else if ((Hours.hoursBetween(dt1, dt2).getHours() % 24) >0) {
				dataAposComentario =Hours.hoursBetween(dt1, dt2).getHours() % 24 + " hours";
			} else if ((Minutes.minutesBetween(dt1, dt2).getMinutes() % 60) >0 ) {
				dataAposComentario =Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 + " minutes";
			} else {
				dataAposComentario =Seconds.secondsBetween(dt1, dt2).getSeconds() % 60 + " seconds";
			}
			

		 } catch (Exception e) {
			e.printStackTrace();
		 }
		System.out.println(dataAposComentario);
		return dataAposComentario;
	  }
		
	
	public String pegarPrimeiraFoto(long codigo) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
		EntityManager	manager	= factory.createEntityManager();
		
		TypedQuery<Foto> a = manager.createQuery("SELECT f FROM Foto f WHERE f.produto.codProduto = :codigo", Foto.class);
		a.setParameter("codigo", codigo);
		List<Foto> foto = a.getResultList();
				
				
		

		manager.close();  
		factory.close();
		
		return (foto.get(0) != null && !foto.get(0).getUrlFoto().equals("")) ? foto.get(0).getUrlFoto() : "";
		
	}
}
