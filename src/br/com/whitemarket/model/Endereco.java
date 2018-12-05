package br.com.whitemarket.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="endereco")
public class Endereco {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long cod_endereco;
	
	@OneToOne
	@JoinColumn(name = "fk_cod_usuario")
	Usuario usuario;
	String city;
	String complement;
	String state;
	String postalCode;
	String number;
	public Long getCod_endereco() {
		return cod_endereco;
	}
	public void setCod_endereco(Long cod_endereco) {
		this.cod_endereco = cod_endereco;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
}
