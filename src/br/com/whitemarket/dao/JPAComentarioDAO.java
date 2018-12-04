package br.com.whitemarket.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.whitemarket.model.Comentario;

@Repository
public class JPAComentarioDAO implements ComentarioDAO{
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public void adicionaComentario(Comentario comentario) {
		manager.persist(comentario);
	}
}
