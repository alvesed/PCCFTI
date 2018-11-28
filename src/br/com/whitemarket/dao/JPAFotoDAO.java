package br.com.whitemarket.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.whitemarket.model.Foto;

@Repository
public class JPAFotoDAO implements FotoDAO{
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public void adicionaFoto(Foto foto) {
		manager.persist(foto);
	}
}
