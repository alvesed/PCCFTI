package br.com.whitemarket.model;

public class Produto {
	private String nome;
	private String descricao;
	private String estadoProduto;
	private String condicao;
	private double valor;
	private byte[] foto;
	
	
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
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
}
