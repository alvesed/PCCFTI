package br.com.whitemarket.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="produto")
public class Produto {
	
	public Produto() {}
	
	public Produto(BigDecimal valor, long quantidadeDeVendas) {
		this.valor = valor;
		this.quantidadeDeVendas = quantidadeDeVendas;
		
	}
	
	@Id
	@GeneratedValue()
	@Column(name="cod_produto")
	private Long codProduto;
	
	private String nome;
	private String descricao;
	
	@Column(name="estado_produto")
	private String estadoProduto;
	
	private String condicao;
	
	private BigDecimal valor;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fk_cod_vendedor")
	Usuario usuario;
	
	@OneToMany(mappedBy = "produto", targetEntity = Foto.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Foto> listaFotos;
	
	long quantidadeDeVendas;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public long getQuantidadeDeVendas() {
		return quantidadeDeVendas;
	}

	public void setQuantidadeDeVendas(long quantidadeDeVendas) {
		this.quantidadeDeVendas = quantidadeDeVendas;
	}

	public Long getCodProduto() {
		return codProduto;
	}
	public void setCodProduto(Long codProduto) {
		this.codProduto = codProduto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getEstadoProduto() {
		return estadoProduto;
	}
	public void setEstadoProduto(String estadoProduto) {
		this.estadoProduto = estadoProduto;
	}
	public String getCondicao() {
		return condicao;
	}
	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public List<Foto> getListaFotos() {
		return listaFotos;
	}
	public void setListaFotos(List<Foto> listaFotos) {
		this.listaFotos = listaFotos;
	}

}
