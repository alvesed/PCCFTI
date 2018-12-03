package br.com.whitemarket.dao;

import br.com.whitemarket.model.Pedido;

public interface CarrinhoDAO {
	public Pedido retornaPedidoCadastrado(long codigo);
	public void confirmaPedido(Pedido pedido);
}
