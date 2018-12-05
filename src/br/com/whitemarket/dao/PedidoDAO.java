package br.com.whitemarket.dao;

import java.util.List;

import br.com.whitemarket.model.Foto;
import br.com.whitemarket.model.Pedido;
import br.com.whitemarket.model.Produto;

public interface PedidoDAO {
	public Pedido retornaPedidoCadastrado(long codigo);
	public List<Pedido> retornaListaPedidos(long codigo);
	public List<Produto> retornaListaProdutosCadastrados(long codigo);
	public List<Produto> retornaListaNomeProdutos(long codigo);
	public List<Foto> retornaPrimeiraFoto(long codProduto);
	public Pedido retornaProdutosDentroDePedido(long codPedido);
}