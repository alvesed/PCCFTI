package br.com.whitemarket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

public class Comentario {

	@Entity
	@Table(name="comentario")
	public class Foto {
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="cod_comentario")
		private Long id;
		
		@ManyToOne
		@JoinColumn(name="fk_cod_usuario")
		Usuario usuario;
		
		
		@Column(name="comentario")
		private String comentario;

		@ManyToOne
		@JoinColumn(name="fk_cod_produto")
		Produto produto;
		
		
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}

		public String getComentario() {
			return comentario;   
		}

		public void setComentario(String comentario) {
			this.comentario = comentario;
		}
		
		public Produto getProduto() {
			return produto;
		}

		public void setProduto(Produto produto) {
			this.produto = produto;
		}
	
}
}
