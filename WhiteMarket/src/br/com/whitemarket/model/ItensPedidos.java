package br.com.whitemarket.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="itens_pedidos")
public class ItensPedidos {
	
	@ManyToOne
	@JoinColumn(name="fk_cod_pedido")
	private Pedidos pedidos;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fk_cod_produto")
	private Produto produto;
	
	private int quantidade;
}
