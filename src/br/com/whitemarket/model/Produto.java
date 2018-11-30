package br.com.whitemarket.model;

import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.whitemarket.controller.Util;

@Entity
@Table(name="produto")
public class Produto {
	
	public Produto() {}
	
	public Produto(BigDecimal valor, long quantidadeDeVendas, Date dataCadastro, long codProduto) {
		this.valor = valor;
		this.quantidadeDeVendas = quantidadeDeVendas;
		this.dataCadastro = dataCadastro;
		this.codProduto = codProduto;
	}
	
	
	
	@Id
	@GeneratedValue()
	@Column(name="cod_produto")
	private long codProduto;
	
	@Transient
	private String urlPrimeiraImagem;

	private String nome;
	private String descricao;
	private boolean ativo;
	
	@Column(name="estado_produto")
	private String estadoProduto;
	
	private String condicao;
	private BigDecimal valor;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fk_cod_vendedor")
	Usuario usuario;
	
	@Column(name="fotos")
	private int qtdFiles;
	
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@OneToMany(mappedBy = "produto", targetEntity = Foto.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Foto> listaFotos;
	
	@Transient
	long quantidadeDeVendas;
	
	
	//GETS E SETS	
	public String getUrlPrimeiraImagem() {
		return urlPrimeiraImagem;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void setUrlPrimeiraImagem(String urlPrimeiraImagem) {
		this.urlPrimeiraImagem = urlPrimeiraImagem;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
		
	public int getQtdFiles() {
		return qtdFiles;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setQtdFiles(int qtdFiles) {
		this.qtdFiles = qtdFiles;
	}

	public long getQuantidadeDeVendas() {
		return quantidadeDeVendas;
	}

	public void setQuantidadeDeVendas(long quantidadeDeVendas) {
		this.quantidadeDeVendas = quantidadeDeVendas;
	}

	public long getCodProduto() {
		return codProduto;
	}
	public void setCodProduto(long codProduto) {
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
	public void setValor(String valor) {
		Util util = new Util();
		this.valor = util.removeMascaraValor(valor);
	}
	public List<Foto> getListaFotos() {
		return listaFotos;
	}
	public void setListaFotos(List<Foto> listaFotos) {
		this.listaFotos = listaFotos;
	}

}
