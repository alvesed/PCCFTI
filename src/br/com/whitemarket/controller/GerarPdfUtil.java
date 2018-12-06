package br.com.whitemarket.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
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
	           //Paragraph p = new Paragraph("Dados do Pedido");
	           //p.setIndentationLeft(200);
	           //document.add(p);
	           //document.add(new Paragraph(" "));
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
		// CRIA UMA TABELA COM 3 COLUNAS
		float[] columnWidths = {3, 5, 3};
		PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        // CRIA UM OBJETO CELL
        PdfPCell cell;
        // ADICIONA AS COLUNAS
        if (pedido != null) {
	        cell = new PdfPCell(new Phrase(pedido.getUsuario().getNome()));
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
		// CRIA UMA TABELA COM 8 COLUNAS
		float[] columnWidths = {5, 3, 4, 7, 5, 5, 3, 5, 5};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        // CRIA UM OBJETO CELL
        PdfPCell cell;
        // ADICIONA AS COLUNAS
        cell = new PdfPCell(new Phrase("Fotos"));
        cell.setBackgroundColor(GrayColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Cód."));
        cell.setBackgroundColor(GrayColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Nome"));
        cell.setBackgroundColor(GrayColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Descrição"));
        cell.setBackgroundColor(GrayColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Condição"));
        cell.setBackgroundColor(GrayColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Estado de Conserva."));
        cell.setBackgroundColor(GrayColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Qtd."));
        cell.setBackgroundColor(GrayColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Valor Unit."));
        cell.setBackgroundColor(GrayColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Valor Total"));
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
					table.addCell(criarCellImagem("C:\\Users\\FTI\\git\\PCCFTI\\WebContent\\" + item.getProduto().getListaFotos().get(0).getUrlFoto()));
				} catch (DocumentException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	            
	            //CÓDIGO DO PRODUTO
	            String codP = String.valueOf(item.getProduto().getCodProduto());
	            cell = new PdfPCell(new Phrase(codP));
	            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(cell);
	            table.addCell(item.getProduto().getNome());
	            table.addCell(item.getProduto().getDescricao());
	            cell = new PdfPCell(new Phrase(item.getProduto().getCondicao()));
	            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(cell);
	            if (item.getProduto().getCondicao().equals("usado")) {
		            table.addCell(item.getProduto().getEstadoProduto());
	            } else {
	            	cell = new PdfPCell(new Phrase("-"));
		            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            	table.addCell(cell);
	            }
	            
	            //QUANTIDADE DE ITENS
		        String qtd = String.valueOf(item.getQuantidade());
		        cell = new PdfPCell(new Phrase(qtd));
	            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
	            
	            //VALOR DO PRODUTO
	            String valor = String.valueOf(item.getProduto().getValor());
	            table.addCell("R$ " + valor);
	            
	            //VALOR TOTAL DO PRODUTO
	            BigDecimal valorTotal = item.getProduto().getValor().multiply(BigDecimal.valueOf(item.getQuantidade()));
	            String valorTotalStr = String.valueOf(valorTotal);
	            table.addCell("R$ " + valorTotalStr);
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