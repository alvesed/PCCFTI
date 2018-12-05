package br.com.whitemarket.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Comentario> listaComentarioPorCodigo(Long codigo) {
		
		
		//Query query = manager.createQuery("SELECT c FROM Comentario c JOIN FETCH c.usuario.nome u WHERE c.produto.codProduto = :codigo");
    	//query.setParameter("codigo", codigo);
		
		Query query = manager.createQuery("SELECT comentario FROM Comentario comentario WHERE comentario.produto.codProduto = :codigo");
   
		query.setParameter("codigo", codigo);
		
		List<Comentario> listPedidos = query.getResultList();
   		return listPedidos;
	}


	
	
}
