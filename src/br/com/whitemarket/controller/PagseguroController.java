package br.com.whitemarket.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.uol.pagseguro.domain.AccountCredentials;
import br.com.uol.pagseguro.domain.Address;
import br.com.uol.pagseguro.domain.Credentials;
import br.com.uol.pagseguro.domain.Item;
import br.com.uol.pagseguro.domain.PaymentRequest;
import br.com.uol.pagseguro.domain.Sender;
import br.com.uol.pagseguro.domain.Shipping;
import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.enums.Currency;
import br.com.uol.pagseguro.enums.ShippingType;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;
import br.com.uol.pagseguro.service.NotificationService;
import br.com.whitemarket.model.ItemPedido;
import br.com.whitemarket.model.Pedido;
import br.com.whitemarket.model.Usuario;


@Controller
@Scope(value= "request")
public class PagseguroController {
	
	private final String EMAIL = "rafaelnonino@hotmail.com";
	private final String TOKEN = "AD41533E2198496892041669ADECB0C3";
	private final String TOKENSANDBOX = "1806ACC9B43D4792AF76AF1A0B12D16D";
	
	@RequestMapping("/pagseguro-criarpagamento")
	public @ResponseBody
	String criarPagamento(HttpSession session, HttpServletResponse response){
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		Address address = (Address) session.getAttribute("address");
		Pedido pedido = (Pedido) session.getAttribute("carrinho");
		
		List<Item> listItemPedido = (List<Item>) session.getAttribute("listItemPedido");
		try {
			PaymentRequest request = new PaymentRequest();
			request.setReference("VND01");
			
			request.setCurrency(Currency.BRL);
			request.setSender(getSender(usuario));
			request.setShipping(getShipping(address)); //DEPENDE
			for(Item i: listItemPedido) {
				request.addItem(i);
			 }
			
			
			try {
				if (!usuario.getEmail().equals("") && usuario != null) {
					pedido.setFinalizado(true);
					
					EntityManagerFactory factory = Persistence.createEntityManagerFactory("market");
					EntityManager manager = factory.createEntityManager();
					
					manager.getTransaction().begin();
					manager.merge(pedido);
					manager.getTransaction().commit();
					
					manager.close();
					factory.close();
				}
				
				pedido = new Pedido();
				pedido.setUsuario(usuario);
				pedido.setListaPedidos(new ArrayList<ItemPedido>());
				
				session.setAttribute("carrinho", pedido);
			
			} catch (Exception e) {
				Logger.getLogger(e.toString());
			}
			
			
			
			try {
				response.sendRedirect(request.register(getCredentials()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return request.register(getCredentials());  
		}catch (PagSeguroServiceException e) {
			// TODO: handle exception
			return e.getMessage();
		}
	}
	
	
	
	public Sender getSender(Usuario usuario){
		Sender sender = new Sender();
		sender.setName(usuario.getNome());
		sender.setEmail(usuario.getEmail());
		return sender;
		
	}
	
	
	public Shipping getShipping(Address address){
		Shipping shipping = new Shipping();
		shipping.setAddress(address);
		shipping.setCost(new BigDecimal("9.00"));
		shipping.setType(ShippingType.PAC);
		return shipping;
		
	}
	
	
	private Credentials getCredentials() throws PagSeguroServiceException
	{
		return new AccountCredentials(EMAIL, TOKEN, TOKENSANDBOX);
	}
	
	
	@RequestMapping(value = "/pagseguro-notificacao", method = RequestMethod.POST)
	public @ResponseBody
	String registrarNotificacao(
			@RequestParam("notificationCode") String nCode,
			@RequestParam("notificationType") String nType
			) {
		
		
		try {
			
			Transaction transaction = NotificationService.checkTransaction(getCredentials(), nCode);
			
			switch (transaction.getStatus()) {
			case PAID:
				System.out.println("PAID");
				//É aqui que deveria chamar um método que deveria finalizar o pedido.
				break;
				
			case CANCELLED:
				System.out.println("CANCELLED");
				break;
			case WAITING_PAYMENT:
				System.out.println("WAITING_PAYMENT");
				break;
				
			case IN_ANALYSIS:
				System.out.println("IN_ANALYSIS");
				break;

			default:
				System.out.println("default");
				break;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "verPedidos";
		
		
	}
	
	
	
	

}
