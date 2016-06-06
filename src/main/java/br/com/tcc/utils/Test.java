package br.com.tcc.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.tcc.dao.AnoLetivoDAO;
import br.com.tcc.dao.MatriculaDAO;
import br.com.tcc.model.AnoLetivo;
import br.com.tcc.model.Matricula;
import br.com.tcc.model.Telefone;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Test {
	
	/*public static void main(String[] args) {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("/home/eduardo/test.pdf"));
			document.open();
			
			document.add(new Paragraph("Gerando PDF - Java 1"));
			document.add(new Paragraph("Gerando PDF - Java 2"));
			document.add(new Paragraph("Gerando PDF - Java 3"));
			document.add(new Paragraph("Gerando PDF - Java 4"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		document.close();
	}*/
	
	public static void main(String[] args) throws IOException, DocumentException {
		String dest = "/home/eduardo/teste.pdf";
		Document document = new Document(PageSize.A4.rotate());
		PdfWriter.getInstance(document, new FileOutputStream(dest));
		document.open();
		float[] columnWidths = {5, 5};
		PdfPTable table = new PdfPTable(columnWidths);
		table.setWidthPercentage(100);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        Font f = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);
        PdfPCell cell = new PdfPCell(new Phrase("Porcentagem de Alunos por Ano", f));
        cell.setBackgroundColor(GrayColor.GRAYBLACK);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(3);
        table.addCell(cell);
        table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
        for (int i = 0; i < 2; i++) {
            table.addCell("Ano");
            table.addCell("Porcentagem");
        }
        table.setHeaderRows(3);
        table.setFooterRows(1);
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        
        AnoLetivoDAO anoLetivoDAO = new AnoLetivoDAO();
        List<AnoLetivo> listAnoLetivo = new ArrayList<AnoLetivo>();
        listAnoLetivo = anoLetivoDAO.listar();
        
        MatriculaDAO matriculaDAO = new MatriculaDAO();
        List<Matricula> listMatricula = new ArrayList<Matricula>();
        listMatricula = matriculaDAO.listar();
        Integer listaMatricula = listMatricula.size();
        
        for (int counter = 0; counter < 9; counter++) {
            table.addCell(listAnoLetivo.get(counter).getAnonome());
            listMatricula = matriculaDAO.buscar(Matricula.QUERY_QTD_ALUNO_ANO, listAnoLetivo.get(counter).getAnonome());
            
            double totalAlunoAno = listMatricula.size();
            double totalAlunos = listaMatricula;
            
            Double total = new Double((totalAlunoAno/totalAlunos)*100);
            DecimalFormat formato = new DecimalFormat("#.##");
            
            table.addCell(formato.format(total) + "%");
        }
        document.add(table);
        document.close();
	}
	
	/*
	 * Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        float[] columnWidths = {1, 5, 5};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        Font f = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);
        PdfPCell cell = new PdfPCell(new Phrase("This is a header", f));
        cell.setBackgroundColor(GrayColor.GRAYBLACK);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(3);
        table.addCell(cell);
        table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
        for (int i = 0; i < 2; i++) {
            table.addCell("#");
            table.addCell("Key");
            table.addCell("Value");
        }
        table.setHeaderRows(3);
        table.setFooterRows(1);
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        for (int counter = 1; counter < 101; counter++) {
            table.addCell(String.valueOf(counter));
            table.addCell("key " + counter);
            table.addCell("value " + counter);
        }
        document.add(table);
        document.close();
	 */

}
