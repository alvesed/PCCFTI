package br.com.whitemarket.dao;


import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.whitemarket.model.Avaliacao;
import br.com.whitemarket.model.Endereco;
import br.com.whitemarket.model.Usuario;

@Repository
public class JPAUsuarioDAO implements UsuarioDAO{
	
	@PersistenceContext
	EntityManager manager;

	@Override
	public void cadastraUsuario(Usuario usuario, Endereco endereco) {
		manager.persist(usuario);
		manager.persist(endereco);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Usuario autenticaLogin(String email, String senha) {
		Usuario usuario = new Usuario();

		Query query = manager.createQuery("SELECT u from Usuario u WHERE u.email = :email and u.senha = :senha");
		query.setParameter("email", email).setParameter("senha", senha);
		
		List<Usuario> listaTemporaria = query.setMaxResults(1).getResultList();
		
		usuario = listaTemporaria.get(0);
		
		System.out.println(usuario.getEmail() + "   " + usuario.getSenha());
			
		return usuario;
	}

	@Override
	public void avaliarVendedor(Avaliacao avaliacao) {
		manager.persist(avaliacao);
		
		long codVendedor = avaliacao.getVendedor().getCod_usuario();
		Usuario u = retornaUsuarioCompleto(codVendedor);
		BigDecimal nota = retornaNotaVendedor(codVendedor);
		
		u.setNota(nota);
		atualizaNotaUsuario(u);
	}

	public BigDecimal retornaNotaVendedor(long codVendedor) {
		Query query = manager.createQuery("SELECT AVG(a.nota) FROM Avaliacao a WHERE a.vendedor.cod_usuario = :cod_usuario");
		query.setParameter("cod_usuario", codVendedor);
		double valorEmDouble = 0;
		try {
			 valorEmDouble = (double) query.getSingleResult();
		} catch (NullPointerException e) {
			
		}
		String valorEmString = "" + valorEmDouble;

		BigDecimal nota = new BigDecimal(valorEmString);
		nota = nota.setScale(2, BigDecimal.ROUND_HALF_EVEN);

		return nota;
	}

	public Usuario retornaUsuarioCompleto(long codUsuario) {
		return manager.find(Usuario.class, codUsuario);
	}
	
	public void atualizaNotaUsuario(Usuario usuario) {
		manager.merge(usuario);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> buscaEmailsCadastrado(String verificar_email) {
		return manager.createQuery("select u.email from usuario u where email = verifica").setParameter("verifica", verificar_email).getResultList();
	}
	
}
