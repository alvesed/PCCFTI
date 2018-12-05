package br.com.whitemarket.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
	       Rectangle pageSize = new Rectangle(PageSize.A4);
	       Document document = new Document(pageSize);
	       try {
	           PdfWriter.getInstance(document, new FileOutputStream("C:\\PDF_Teste.pdf"));
	           document.open();
	           //Paragraph p = new Paragraph("Dados do Pedido");
	           //p.setIndentationLeft(200);
	           //document.add(p);
	           //document.add(new Paragraph(" "));
	           document.add(createTableUsuario(pedido));
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
	        cell = new PdfPCell(new Phrase("Endereço: " + pedido.getUsuario().getEndereco()));
	        cell.setColspan(4);
	        table.addCell(cell);
        }
        return table;
	}
	
	public static PdfPTable createTableProdutos(Pedido pedido) {
		// CRIA UMA TABELA COM 8 COLUNAS
        PdfPTable table = new PdfPTable(9);
        table.setTotalWidth(530);
        table.setLockedWidth(true);
        try {
			table.setWidths(new float[] { 2, 1 });
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
        // CRIA UM OBJETO CELL
        PdfPCell cell;
        // ADICIONA AS COLUNAS
        cell = new PdfPCell(new Phrase("Fotos"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Código"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Produto"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Descrição"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Condição"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Estado de Conserva."));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Qtd."));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Valor Unit."));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Valor Total"));
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
	            table.addCell(codP);
	            table.addCell(item.getProduto().getNome());
	            table.addCell(item.getProduto().getDescricao());
	            table.addCell(item.getProduto().getCondicao());
	            if (item.getProduto().getCondicao().equals("usado")) {
		            table.addCell(item.getProduto().getEstadoProduto());
	            } else {
	            	table.addCell("N/A");
	            }
	            
	            //QUANTIDADE DE ITENS
		        String qtd = String.valueOf(item.getQuantidade());
		        table.addCell(qtd);
	            
	            //VALOR DO PRODUTO
	            String valor = String.valueOf(item.getProduto().getValor());
	            table.addCell(valor);
	            
	            //VALOR TOTAL DO PRODUTO
	            BigDecimal valorTotal = item.getProduto().getValor().multiply(BigDecimal.valueOf(item.getQuantidade()));
	            String valorTotalStr = String.valueOf(valorTotal);
	            table.addCell(valorTotalStr);
	        }
        }
        return table;
	}
	
	public static PdfPTable tableValorTotal(Pedido pedido) {
		PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cell;
        
        if (pedido != null) {
        	cell = new PdfPCell(new Phrase("Valor Total dos Itens: " + pedido.getValor_pago()));
        	table.addCell(cell);
        } else {
        	cell = new PdfPCell(new Phrase("Valor Total dos Itens: "));
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