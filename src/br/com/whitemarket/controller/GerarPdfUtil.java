package br.com.whitemarket.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.whitemarket.model.ItemPedido;
import br.com.whitemarket.model.Pedido;
import br.com.whitemarket.model.Produto;

public class GerarPdfUtil {
	public static void main(String[] args) {
		gerarPdf(null);
	}
	
	public static void gerarPdf(Pedido pedido) {
		 // criação do documento
	       Rectangle pageSize = new Rectangle(PageSize.A3);
	       pageSize.setBackgroundColor(new BaseColor(215, 206, 199));
	       Document document = new Document(pageSize);
	       try {
	           PdfWriter.getInstance(document, new FileOutputStream("D:\\PDF_Teste.pdf"));
	           document.open();
	           //Paragraph p = new Paragraph("Dados do Pedido");
	           //p.setIndentationLeft(200);
	           //document.add(p);
	           //document.add(new Paragraph(" "));
	           document.add(createTableUsuario(pedido));
	           document.add(new Paragraph(" "));
	           document.add(createTableProdutos(pedido));
	       }
	       catch(DocumentException de) {
	           System.err.println(de.getMessage());
	       }
	       catch(IOException ioe) {
	           System.err.println(ioe.getMessage());
	       }
	       document.close();
	}
	
	public static PdfPTable createTableUsuario(Pedido pedido) {
		// CRIA UMA TABELA COM 4 COLUNAS
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        // CRIA UM OBJETO CELL
        PdfPCell cell;
        // ADICIONA AS COLUNAS
        if (pedido != null) {
	        cell = new PdfPCell(new Phrase(pedido.getUsuario().getNome()));
	        cell.setColspan(3);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("CPF: " + pedido.getUsuario().getCpf()));
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Pedido: N° " + pedido.getCod_pedido()));
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("E-Mail: " + pedido.getUsuario().getEmail()));
	        cell.setColspan(3);
	        table.addCell(cell);
        }
        return table;
	}
	
	public static PdfPTable createTableProdutos(Pedido pedido) {
		// CRIA UMA TABELA COM 8 COLUNAS
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        // CRIA UM OBJETO CELL
        PdfPCell cell;
        // ADICIONA AS COLUNAS
        cell = new PdfPCell(new Phrase("Fotos"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Código"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Produto"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Descri."));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Condi."));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Estado"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Qtd."));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Valor"));
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
	        for (Produto prod : listaProdutos) {
	            try {
					table.addCell(criarCellImagem(prod.getListaFotos().get(0).getUrlFoto()));
				} catch (DocumentException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	            
	            //CÓDIGO DO PRODUTO
	            String codP = String.valueOf(prod.getCodProduto());
	            table.addCell(codP);
	            table.addCell(prod.getNome());
	            table.addCell(prod.getDescricao());
	            table.addCell(prod.getCondicao());
	            if (prod.getCondicao().equals("Usado")) {
		            table.addCell(prod.getEstadoProduto());
	            } else {
	            	table.addCell("N/A");
	            }
	            
	            //VALOR DO PRODUTO
	            String valor = String.valueOf(prod.getValor());
	            table.addCell(valor);
	            
	            //QUANTIDADE DE ITENS
	            String qtd = String.valueOf(pedido.getQuantidadeItensPedido());
	            table.addCell(qtd);
	        }
        }
        return table;
	}
	
	public static PdfPCell criarCellImagem(String path) throws DocumentException, IOException {
        Image img = Image.getInstance(path);
        PdfPCell cell = new PdfPCell(img, true);
        return cell;
    }
}