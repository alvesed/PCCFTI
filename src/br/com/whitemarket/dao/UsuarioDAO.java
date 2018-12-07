package br.com.whitemarket.dao;

import java.util.List;

import br.com.whitemarket.model.Avaliacao;
import br.com.whitemarket.model.Endereco;
import br.com.whitemarket.model.Usuario;

public interface UsuarioDAO {
	
	void cadastraUsuario(Usuario usuario, Endereco endereco);
	Usuario autenticaLogin(String email, String senha);
	void avaliarVendedor(Avaliacao avaliacao);
	List<String> buscaEmailsCadastrado(String verificar_email);
}