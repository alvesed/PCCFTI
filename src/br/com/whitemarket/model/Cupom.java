package br.com.whitemarket.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="cupom")
public class Cupom {
	
	
	@Id
	@GeneratedValue
	private Long cupom_id;
	
	private String cod_cupom;
	private String descricao;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fk_cod_vendedor")
	private Usuario usuario;
	
	private Integer valor_percent;
	
	private double valor_desconto;
	
	private double valor_minimo;

	private boolean tipo_desconto; //true = reais false = percent
	
	@Temporal(TemporalType.DATE)
	private Date data_expiracao;
	
	//Integer qnt_cupons;
	Integer qnt_cupons; //número máximo de vezes que cupom pode ser usado
	
//	@ManyToMany
//	 @JoinTable(name="ProdutoCupom", joinColumns=
//		    {@JoinColumn(name="cupom_id")}, inverseJoinColumns=
//		      {@JoinColumn(name="usuario_cod")})
//		    private List<Produto> listProdutos;
	
	@OneToMany(mappedBy = "cupom", targetEntity = Produto.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Produto> listaProdutos;

	public String getCod_cupom() {
		return cod_cupom;
	}

	public void setCod_cupom(String cod_cupom) {
		this.cod_cupom = cod_cupom;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isTipo_desconto() {
		return tipo_desconto;
	}

	public void setTipo_desconto(boolean tipo_desconto) {
		this.tipo_desconto = tipo_desconto;
	}

	public Integer getValor_percent() {
		return valor_percent;
	}

	public void setValor_percent(Integer valor_percent) {
		this.valor_percent = valor_percent;
	}

	public double getValor_desconto() {
		return valor_desconto;
	}

	public void setValor_desconto(double valor_desconto) {
		this.valor_desconto = valor_desconto;
	}

	public double getValor_minimo() {
		return valor_minimo;
	}

	public void setValor_minimo(double valor_minimo) {
		this.valor_minimo = valor_minimo;
	}

	//@Transient
	//private long quantidadeProdutos;

}
