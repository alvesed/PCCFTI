package br.com.whitemarket.dao;

import java.util.List;

import br.com.whitemarket.model.Usuario;

public interface UsuarioDAO {
	
	Usuario buscaPorCodigo(Long codigo);
	List<Usuario> listaUsuario();
	void adicionaProduto(Usuario usuario);
	
	Usuario autentica(String email, String senha);

}
