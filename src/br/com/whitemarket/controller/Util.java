package br.com.whitemarket.controller;

import java.math.BigDecimal;

import br.com.whitemarket.model.Produto;

public class Util {
	public boolean temErroProduto(Produto produto) {
		if (produto.getNome().isEmpty()) {
			return true;
		} else if (produto.getDescricao().isEmpty()) {
			return true;
		} else if (produto.getListaFotos().size() < 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public BigDecimal removeMascaraValor(String valor) {
		BigDecimal money;
		if (valor.contains(",") || valor.contains(".")) {
			valor = valor.replace(".", "");
			valor = valor.replace(",", ".");
		}
		money = new BigDecimal(valor);
		return money;
	}
}
