package br.com.whitemarket.dao;

import br.com.whitemarket.model.Produto;

public interface ProdutoDAO {
	void adiciona(Produto produto);
	void edita(Produto produto);
}
