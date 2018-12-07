package br.com.whitemarket.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.whitemarket.model.Cupom;

@Repository
@SuppressWarnings("unchecked")
public class JPACupomDAO implements CupomDAO {

	@PersistenceContext
	EntityManager manager;
	
	@Override
	public void adicionaCupom(Cupom cupom) {
		manager.persist(cupom);
	}

	@Override
	public Cupom encontrarCupom(String strCupom) {
		
		Cupom c = new Cupom();
		
		Query query = manager.createQuery("SELECT c FROM Cupom c JOIN FETCH c.usuario u WHERE c.cod_cupom = :codCupom");
		query.setParameter("codCupom", strCupom);
		
		List<Cupom> list = query.getResultList();
		
		
		System.out.println("PASSEI AQUI!");
		if(!list.isEmpty()) {
			c = list.get(0);
			System.out.println(c.getCod_cupom());
			
		} else {
			System.out.println("FALHOU");
		}
		return c;
	}

	
	@Override
	public List<Cupom> listaCupom() {
		return manager.createQuery("SELECT c FROM Cupom c").getResultList();
	}

	
	
}
