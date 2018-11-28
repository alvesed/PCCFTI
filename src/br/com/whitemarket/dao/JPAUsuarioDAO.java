package br.com.whitemarket.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;


import br.com.whitemarket.model.Usuario;

@Repository
public class JPAUsuarioDAO implements UsuarioDAO{
	
	@PersistenceContext
	EntityManager manager;

	@Override
	public Usuario buscaPorCodigo(Long codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listaUsuario() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void adicionaProduto(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario autentica(String email, String senha) {
	
        manager.createQuery("select a from usuario a where email = :email and senha = :senha");
       
		
		return null;
	}
	
	

}
