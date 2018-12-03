package br.com.whitemarket.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.uol.pagseguro.domain.AccountCredentials;
import br.com.uol.pagseguro.domain.Address;
import br.com.uol.pagseguro.domain.Credentials;
import br.com.uol.pagseguro.domain.Item;
import br.com.uol.pagseguro.domain.PaymentRequest;
import br.com.uol.pagseguro.domain.Phone;
import br.com.uol.pagseguro.domain.Sender;
import br.com.uol.pagseguro.domain.Shipping;
import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.enums.Currency;
import br.com.uol.pagseguro.enums.ShippingType;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.service.NotificationService;
import br.com.whitemarket.model.Usuario;


@Controller
@Scope(value= "request")
public class PagseguroController {
	
	private final String EMAIL = "rafaelnonino@hotmail.com";
	private final String TOKEN = "AD41533E2198496892041669ADECB0C3";
	
	@RequestMapping("/pagseguro-criarpagamento")
	public @ResponseBody
	String criarPagamento(HttpSession session){
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		
		
		try {
			PaymentRequest request = new PaymentRequest();
			request.setReference("VND01");
			request.setCurrency(Currency.BRL);
			request.setSender(getSender(usuario));
			request.setShipping(getShipping()); //DEPENDE
			request.addItem(getItem());
			request.setNotificationURL("localhost:8080/WhiteMarket/pagseguro-notificacao");
			request.setRedirectURL("localhost:8080/WhiteMarket/verPedidos");
			
			
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
		sender.setPhone(new Phone(usuario.getTelefone().substring(0,2), usuario.getTelefone().substring(3, usuario.getTelefone().length())));
		return sender;
		
	}
	
	
	public Shipping getShipping(){
		Shipping shipping = new Shipping();
		shipping.setAddress(getAddress());
		shipping.setCost(new BigDecimal("9.00"));
		shipping.setType(ShippingType.PAC);
		return shipping;
		
	}
	
	
	public Address getAddress(){
		Address adress = new Address();
		adress.setCity("Londrina");
		adress.setComplement("Perto da Pizza Hut");
		adress.setCountry("Brasil");
		adress.setState("PR");
		adress.setPostalCode("86050464");
		adress.setNumber("15");
		adress.setDistrict("Retiro");
		
		return adress;
		
	}
	
	public Item getItem() {
		Item item = new Item();
		item.setId("1");
		item.setDescription("MEIA");
		item.setQuantity(5);
		item.setAmount(new BigDecimal("10.00"));
		item.setShippingCost(new BigDecimal("10.00"));
		
		return item;
	}
	
	
	private Credentials getCredentials() throws PagSeguroServiceException
	{
		return new AccountCredentials(EMAIL, TOKEN);
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
				
				break;
				
			case CANCELLED:
				
				break;
			case WAITING_PAYMENT:
				break;
				
			case IN_ANALYSIS:
				break;

			default:
				break;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "";
		
		
	}
	
	
	
	

}
