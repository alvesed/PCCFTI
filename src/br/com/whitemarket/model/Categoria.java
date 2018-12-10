package br.com.whitemarket.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="categoria")
public class Categoria {
	
	public Categoria() {}
	
	public Categoria(long id) 
	{
		this.id = id;
		
	}
	
	
	@Id
	@GeneratedValue()
	@Column(name="id")
	private long id;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="fk_categoria")
	private List<Produto> produto;
	
	private String descricao;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
