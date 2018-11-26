package br.com.whitemarket.controller;

import br.com.whitemarket.model.Produto;

public class Util {
	public boolean temErroProduto(Produto produto) {
		if (produto.getNome().isEmpty()) {
			return true;
		} else if (produto.getDescricao().isEmpty()) {
			return true;
		} else if (produto.getFoto().length < 1) {
			return true;
		} else {
			return false;
		}
	}
}
