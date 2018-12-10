package br.com.whitemarket.dao;

import java.util.List;

import br.com.whitemarket.model.Cupom;

public interface CupomDAO {

	void adicionaCupom(Cupom cupom);
	Cupom encontrarCupom(String strCupom);
	List<Cupom> listaCupom();
	void editaCupom(Cupom cupom);
	
}
