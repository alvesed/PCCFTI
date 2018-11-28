package br.com.whitemarket.dao;

import java.util.List;

import br.com.whitemarket.model.Produto;

public interface ItemProdutoDAO {
	
	Produto buscaPorCodigo(Long codigo);
	List<Produto> listaProdutos();
	void adicionaProduto(Produto produto);
	
}
