package br.com.whitemarket.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="item_pedido")
public class ItemPedido {
	
	public ItemPedido() {}
	
	public ItemPedido(long cod_pedido, long somaquantidade) {
		this.pedido.setCod_pedido(cod_pedido);
		this.quantidade = somaquantidade;
		
	}
	
	@Id
	@GeneratedValue
	long cod_item_pedido;
	
	@ManyToOne
	@JoinColumn(name="fk_cod_pedido")
	private Pedido pedido;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fk_cod_produto")
	private Produto produto;
	
	private long quantidade;

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}
	
}
