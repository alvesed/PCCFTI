package br.com.whitemarket.model;

import java.util.Date;

public class Pedidos {
	
	int cod_compra;
	int cod_vendedor;
	int cod_comprador;
	double valor_pago;
	boolean finalizado;
	Date data_compra;
	
	
	public int getCod_compra() {
		return cod_compra;
	}
	public void setCod_compra(int cod_compra) {
		this.cod_compra = cod_compra;
	}
	public int getCod_vendedor() {
		return cod_vendedor;
	}
	public void setCod_vendedor(int cod_vendedor) {
		this.cod_vendedor = cod_vendedor;
	}
	public int getCod_comprador() {
		return cod_comprador;
	}
	public void setCod_comprador(int cod_comprador) {
		this.cod_comprador = cod_comprador;
	}
	public double getValor_pago() {
		return valor_pago;
	}
	public void setValor_pago(double valor_pago) {
		this.valor_pago = valor_pago;
	}
	public boolean isFinalizado() {
		return finalizado;
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	public Date getData_compra() {
		return data_compra;
	}
	public void setData_compra(Date data_compra) {
		this.data_compra = data_compra;
	}
	
	

}
