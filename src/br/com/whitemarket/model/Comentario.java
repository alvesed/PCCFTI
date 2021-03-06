package br.com.whitemarket.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


	@Entity
	@Table(name="comentario")
	public class Comentario {
		
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
		
		private Date data_comentario;
		
		@Transient
		String dataAposComentario;
		
		
		public String getDataAposComentario() {
			return dataAposComentario;
		}

		public void setDataAposComentario(String dataAposComentario) {
			this.dataAposComentario = dataAposComentario;
		}

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

		public Date getData_comentario() {
			return data_comentario;
		}

		public void setData_comentario(Date data_comentario) {
			this.data_comentario = data_comentario;
		}
		
		
	
}
