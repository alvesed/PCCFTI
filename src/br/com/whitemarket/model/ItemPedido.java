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
public class ItemPedido implements Comparable<ItemPedido> {
	
	public ItemPedido() {}
	
	public ItemPedido(long cod_pedido, long somaquantidade) {
		this.pedido.setCod_pedido(cod_pedido);
		this.quantidade = somaquantidade;
	}
	
	@Id
	@GeneratedValue
	long cod_item_pedido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fk_cod_pedido")
	private Pedido pedido;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fk_cod_produto")
	private Produto produto;
	
	private long quantidade;
	
	@SuppressWarnings("unused")
	private Long fk_cupom_id;
	
	private double valor_desconto;

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

	public long getCod_item_pedido() {
		return cod_item_pedido;
	}

	public void setCod_item_pedido(long cod_item_pedido) {
		this.cod_item_pedido = cod_item_pedido;
	}

	@Override
	public int compareTo(ItemPedido o) {
		return Long.compare(this.getProduto().getUsuario().getCod_usuario(), o.getProduto().getUsuario().getCod_usuario());
	}

	public double getValor_desconto() {
		return valor_desconto;
	}
	
	public double getSetValor_desconto(double valor_desconto) {
		
		if(valor_desconto == 0) {
			if(String.valueOf(this.getProduto().getCupom().getTipo_desconto()).equals("p")) {
				setValor_desconto((this.getProduto().getCupom().getValor_percent() / 100) * this.getProduto().getValor().doubleValue());
			}
			else setValor_desconto(this.getProduto().getCupom().getValor_desconto());
		} else setValor_desconto(valor_desconto);
		
		return getValor_desconto();
	}

	public void setValor_desconto(double valor_desconto) {
		
		this.valor_desconto = valor_desconto;
	}
	
}
