package br.com.whitemarket.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.whitemarket.model.Endereco;
import br.com.whitemarket.model.ItemPedido;
import br.com.whitemarket.model.Pedido;
import br.com.whitemarket.model.Produto;

public class GerarPdfUtil {
	public static void main(String[] args) {
		gerarPdf(null, null);
	}
	
	public static void gerarPdf(Pedido pedido, Endereco endereco) {
		 // criação do documento
	       Rectangle pageSize = new Rectangle(PageSize.A4);
	       Document document = new Document(pageSize);
	       try {
	           PdfWriter.getInstance(document, new FileOutputStream("C:\\PDF_Teste.pdf"));
	           document.open();
	           document.add(createTableUsuario(pedido, endereco));
	           document.add(new Paragraph(" "));
	           document.add(createTableProdutos(pedido));
	           document.add(new Paragraph(" "));
	           document.add(tableValorTotal(pedido));
	       }
	       catch(DocumentException de) {
	           System.err.println(de.getMessage());
	       }
	       catch(IOException ioe) {
	           System.err.println(ioe.getMessage());
	       }
	       document.close();
	}
	
	public static PdfPTable createTableUsuario(Pedido pedido, Endereco endereco) {
		Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
		// CRIA UMA TABELA COM 3 COLUNAS
		float[] columnWidths = {3, 5, 3};
		PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        // CRIA UM OBJETO CELL
        PdfPCell cell;
        // ADICIONA AS COLUNAS
        if (pedido != null) {
	        cell = new PdfPCell(new Phrase(pedido.getUsuario().getNome(), titleFont));
	        cell.setColspan(2);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("CPF: " + pedido.getUsuario().getCpf()));
	        table.addCell(cell);
	        //ROW 2
	        cell = new PdfPCell(new Phrase("Pedido: N° " + pedido.getCod_pedido()));
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("E-Mail: " + pedido.getUsuario().getEmail()));
	        cell.setColspan(2);
	        table.addCell(cell);
	        //ROW 3
	        cell = new PdfPCell(new Phrase("Logradouro: " + endereco.getLogradouro()));
	        cell.setColspan(2);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("CEP: " + endereco.getPostalCode()));
	        table.addCell(cell);
	        //ROW 4
	        cell = new PdfPCell(new Phrase("Cidade: " + endereco.getCity()));
	        cell.setColspan(2);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Estado: " + endereco.getState()));
	        table.addCell(cell);
        }
        return table;
	}
	
	public static PdfPTable createTableProdutos(Pedido pedido) {
		Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
		// CRIA UMA TABELA COM 8 COLUNAS
		float[] columnWidths = {5, 2, 4, 7, 5, 5, 2, 5, 5};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        // CRIA UM OBJETO CELL
        PdfPCell cell;
        // ADICIONA AS COLUNAS
        cell = new PdfPCell(new Phrase("Fotos", titleFont));
        cell.setBackgroundColor(GrayColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Cód.", titleFont));
        cell.setBackgroundColor(GrayColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Nome",titleFont));
        cell.setBackgroundColor(GrayColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Descrição", titleFont));
        cell.setBackgroundColor(GrayColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Condição", titleFont));
        cell.setBackgroundColor(GrayColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Estado de Conserva.", titleFont));
        cell.setBackgroundColor(GrayColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Qtd.", titleFont));
        cell.setBackgroundColor(GrayColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Valor Unit.", titleFont));
        cell.setBackgroundColor(GrayColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Valor Total", titleFont));
        cell.setBackgroundColor(GrayColor.LIGHT_GRAY);
        table.addCell(cell);
        // CRIA UMA LISTA DE PRODUTOS
        List<Produto> listaProdutos = new ArrayList<Produto>();
        if (pedido != null) {	        
	        for (ItemPedido item : pedido.getListaPedidos()) {
	        	listaProdutos.add(item.getProduto());
	        }
        }
        // ADICIONA UMA ROW PARA CADA PRODUTO DA LISTA
        if (listaProdutos != null) {
        	for (ItemPedido item : pedido.getListaPedidos()) {
	            try {
	            	cell = new PdfPCell(criarCellImagem("C:\\Users\\FTI\\git\\PCCFTI\\WebContent\\" + item.getProduto().getListaFotos().get(0).getUrlFoto()));
	            	cell.setPadding(2);
					table.addCell(cell);
				} catch (DocumentException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	            
	            //CÓDIGO DO PRODUTO
	            String codP = String.valueOf(item.getProduto().getCodProduto());
	            cell = new PdfPCell(new Phrase(codP, titleFont));
	            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(cell);
	            cell = new PdfPCell(new Phrase(item.getProduto().getNome(), titleFont));
	            table.addCell(cell);
	            cell = new PdfPCell(new Phrase(item.getProduto().getDescricao(), titleFont));
	            table.addCell(cell);
	            cell = new PdfPCell(new Phrase(item.getProduto().getCondicao(),titleFont));
	            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(cell);
	            if (item.getProduto().getCondicao().equals("usado")) {
	            	cell = new PdfPCell(new Phrase(item.getProduto().getEstadoProduto(),titleFont));
		            table.addCell(cell);
	            } else {
	            	cell = new PdfPCell(new Phrase("-", titleFont));
		            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            	table.addCell(cell);
	            }
	            
	            //QUANTIDADE DE ITENS
		        String qtd = String.valueOf(item.getQuantidade());
		        cell = new PdfPCell(new Phrase(qtd, titleFont));
	            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
	            
	            //VALOR DO PRODUTO
	            String valor = String.valueOf(item.getProduto().getValor());
	            cell = new PdfPCell(new Phrase("R$ " + valor, titleFont));
	            table.addCell(cell);
	            
	            //VALOR TOTAL DO PRODUTO
	            BigDecimal valorTotal = item.getProduto().getValor().multiply(BigDecimal.valueOf(item.getQuantidade()));
	            String valorTotalStr = "R$ " + String.valueOf(valorTotal);
	            cell = new PdfPCell(new Phrase(valorTotalStr, titleFont));
	            table.addCell(cell);
	        }
        }
        return table;
	}
	
	public static PdfPTable tableValorTotal(Pedido pedido) {
		PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cell;
        
        if (pedido != null) {
        	cell = new PdfPCell(new Phrase("Valor Total dos Itens: R$ " + pedido.getValor_pago()));
        	cell.setBackgroundColor(GrayColor.LIGHT_GRAY);
        	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        	table.addCell(cell);
        }
        
        return table;
	}
	
	public static PdfPCell criarCellImagem(String path) throws DocumentException, IOException {
        Image img = Image.getInstance(path);
        PdfPCell cell = new PdfPCell(img, true);
        return cell;
    }
}