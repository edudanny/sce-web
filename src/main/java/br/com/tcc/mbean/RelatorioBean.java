package br.com.tcc.mbean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.omnifaces.util.Messages;

import br.com.tcc.dao.AlunoDAO;
import br.com.tcc.dao.AnoLetivoDAO;
import br.com.tcc.dao.MatriculaDAO;
import br.com.tcc.model.Aluno;
import br.com.tcc.model.AnoLetivo;
import br.com.tcc.model.Matricula;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.collection.PdfTargetDictionary;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class RelatorioBean implements Serializable {
	
	/*private List<Aluno> listAluno;
	private List<AnoLetivo> listAnoLetivo;
	private List<Matricula> listMatricula;*/
	
	public String listar() {
		novo();
		try {
			/*AlunoDAO alunoDAO = new AlunoDAO();
			listAluno = alunoDAO.listar();
			
			AnoLetivoDAO anoLetivoDAO = new AnoLetivoDAO();
			listAnoLetivo = anoLetivoDAO.listar();
			
			MatriculaDAO matriculaDAO = new MatriculaDAO();
			listMatricula = matriculaDAO.listar();*/
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as relatórios");
			erro.printStackTrace();
		}
		return "/pages/relatorio/opcaoRelatorio.xhtml?faces-redirect=true";
		
	}
	
	public void gerarRelatorioAlunosAnos() {
		FacesContext context = FacesContext.getCurrentInstance(); 
	    HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();  
	    response.setContentType("application/pdf");  
	    response.setHeader("Content-disposition",  "inline=filename=file.pdf");
	    try {

	        // Get the text that will be added to the PDF
	        //String text = "test";
	        // step 1
	        Document document = new Document();
	        // step 2
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        PdfWriter.getInstance(document, baos);
	        // step 3
	        document.open();
	        // step 4
	        //document.add(new Paragraph(text));
	        
	        
	        
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
	        document.add(new Paragraph("Total de alunos matriculados: " + listaMatricula));
	        // step 5
	        document.close();

	        // setting some response headers
	        response.setHeader("Expires", "0");
	        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
	        response.setHeader("Pragma", "public");
	        // setting the content type
	        response.setContentType("application/pdf");
	        // the contentlength
	        response.setContentLength(baos.size());
	        // write ByteArrayOutputStream to the ServletOutputStream
	        OutputStream os = response.getOutputStream();
	        baos.writeTo(os);
	        os.flush();
	        os.close();
	        //log.debug("flushed and closed the outputstream");

	    }
	    catch(DocumentException e) {
	        //log.error("error: "+e);
	    	e.printStackTrace();
	    }
	    catch (IOException e) {
	        //log.error("error: "+e);
	    	e.printStackTrace();
	    }
	    catch (Exception ex) {
	        //log.debug("error: " + ex.getMessage());
	        ex.printStackTrace();
	    }
	    context.responseComplete();
	    //log.debug("context.responseComplete()");
	}

	public void novo() {
		/*listAluno = new ArrayList<Aluno>();
		listAnoLetivo = new ArrayList<AnoLetivo>();*/
	}

}
