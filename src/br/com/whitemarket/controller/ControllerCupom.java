package br.com.whitemarket.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.whitemarket.dao.CupomDAO;
import br.com.whitemarket.model.Cupom;

@Transactional
@Controller
public class ControllerCupom {

	@Autowired
	CupomDAO cupom;
	
	@RequestMapping(value = "/verificaCupom", method = RequestMethod.POST)
	@ResponseBody String verificaCupom(@RequestParam("codCupom") String strCupom) {
		
		Cupom c = cupom.encontrarCupom(strCupom);
		
		if(c != null) {
			if(c.getCod_cupom() != null) {
				return "true";
			}
		}
		return "false";
	}
	
}
