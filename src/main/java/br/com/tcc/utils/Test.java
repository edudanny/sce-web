package br.com.tcc.utils;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Test {
	
	public static void main(String[] args) {
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
	}

}
