package br.com.whitemarket.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="avaliacao")
public class Avaliacao {
	
	@Id
	@GeneratedValue
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_cod_vendedor")
	private Usuario vendedor;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_cod_comprador")
	private Usuario comprador;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_cod_pedido")
	private Pedido pedido;
	
	private int nota;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Usuario getVendedor() {
		return vendedor;
	}
	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}
	public Usuario getComprador() {
		return comprador;
	}
	public void setComprador(Usuario comprador) {
		this.comprador = comprador;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
}