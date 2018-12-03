package br.com.whitemarket.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="pedido")
public class Pedido {
	
	public Pedido() {
		this.listaPedidos = new ArrayList<ItemPedido>();
	}
	
	public Pedido(Long cod_pedido, Date data_compra, BigDecimal valor_pago, long quantidadeItensPedido, Usuario usuario) {
		this.data_compra = data_compra;
		this.valor_pago = valor_pago;
		this.quantidadeItensPedido = quantidadeItensPedido;
		this.usuario = usuario;
		this.cod_pedido = cod_pedido;
	}
	
	@Id
	@GeneratedValue
	private Long cod_pedido;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fk_cod_comprador")
	private Usuario usuario;
	
	BigDecimal valor_pago;
	double valor_pagoDouble;

	private boolean finalizado;
	
	@Temporal(TemporalType.DATE)
	private Date data_compra;
	
	@OneToMany(mappedBy = "pedido", targetEntity = ItemPedido.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ItemPedido> listaPedidos;

	@Transient
	private long quantidadeItensPedido;
	
	public long getQuantidadeItensPedido() {
		return quantidadeItensPedido;
	}

	public void setQuantidadeItensPedido(long quantidadeItensPedido) {
		this.quantidadeItensPedido = quantidadeItensPedido;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Long getCod_pedido() {
		return cod_pedido;
	}
	public void setCod_pedido(Long cod_pedido) {
		this.cod_pedido = cod_pedido;
	}

	public BigDecimal getValor_pago() {
		return valor_pago;
	}
	public void setValor_pago(BigDecimal valor_pago) {
		this.valor_pago = valor_pago;
	}

	public double getValor_pagoDouble() {
		return this.getValor_pago().doubleValue();
	}

	public void setValor_pagoDouble(double valor_pagoDouble) {
		this.setValor_pago(BigDecimal.valueOf(valor_pagoDouble));
	}
	
	public boolean isFinalizado() {
		return finalizado;
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	public Date getData_compra() {
		return data_compra;
	}
	public void setData_compra(Date data_compra) {
		this.data_compra = data_compra;
	}
	public List<ItemPedido> getListaPedidos() {
		return listaPedidos;
	}
	public void setListaPedidos(List<ItemPedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}
}
