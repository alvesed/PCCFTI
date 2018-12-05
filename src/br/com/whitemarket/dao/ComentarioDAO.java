package br.com.whitemarket.dao;

import java.util.List;

import br.com.whitemarket.model.Comentario;

public interface ComentarioDAO {
	// Adicionar comentario no db
	void adicionaComentario(Comentario comentario);
	// Separar comentario de cada produto e pegar o usuario que comentou
	
	public List<Comentario> listaComentarioPorCodigo(Long codigo);
	
}
