package br.com.whitemarket.model;

import java.util.ArrayList;
import java.util.List;

public class ListaItensDoVendedor {
	
	private List<ItemPedido> listaItemPedido;
	private Usuario vendedor;
	
	public ListaItensDoVendedor(Usuario vendedor) {
		this.listaItemPedido = new ArrayList<ItemPedido>();
		this.vendedor = vendedor;
	}
	
	public List<ItemPedido> getListaItemPedido() {
		return listaItemPedido;
	}
	public void setListaItemPedido(ArrayList<ItemPedido> listaItemPedido) {
		this.listaItemPedido = listaItemPedido;
	}
	public Usuario getVendedor() {
		return vendedor;
	}
	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}
}
