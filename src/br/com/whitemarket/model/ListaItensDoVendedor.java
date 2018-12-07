package br.com.whitemarket.model;

import java.util.ArrayList;
import java.util.List;

public class ListaItensDoVendedor {
	
	private List<ItemPedido> listaItemPedido;
	private Usuario vendedor;
	private boolean avaliado;
	
	public ListaItensDoVendedor(Usuario vendedor) {
		this.listaItemPedido = new ArrayList<ItemPedido>();
		this.vendedor = vendedor;
	}
	
	public List<ItemPedido> getListaItemPedido() {
		return listaItemPedido;
	}
	
	public Usuario getVendedor() {
		return vendedor;
	}
	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}

	public boolean isAvaliado() {
		return avaliado;
	}

	public void setAvaliado(boolean avaliado) {
		this.avaliado = avaliado;
	}

	public void setListaItemPedido(List<ItemPedido> listaItemPedido) {
		this.listaItemPedido = listaItemPedido;
	}
	
}