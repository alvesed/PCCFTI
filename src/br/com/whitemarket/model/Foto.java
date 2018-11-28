package br.com.whitemarket.model;

import br.com.whitemarket.model.Produto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="foto")
public class Foto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cod_foto")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="fk_cod_produto")
	Produto produto;
	
	@Column(name="url_foto")
	private String urlFoto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}
	
}
