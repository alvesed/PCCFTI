package br.com.whitemarket.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerPedidos {
	@RequestMapping("/verPedidos")
	public String verPedidos() {
    	return "verPedidos";
	}
	

}
