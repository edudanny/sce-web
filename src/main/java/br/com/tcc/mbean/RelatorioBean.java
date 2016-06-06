package br.com.tcc.mbean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.omnifaces.util.Messages;

import br.com.tcc.dao.AlunoDAO;
import br.com.tcc.model.Aluno;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class RelatorioBean implements Serializable {
	
	private List<Aluno> listAluno;
	
	public String listar() {
		novo();
		try {
			AlunoDAO alunoDAO = new AlunoDAO();
			listAluno = alunoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as relatórios");
			erro.printStackTrace();
		}
		return "/pages/relatorio/opcaoRelatorio.xhtml?faces-redirect=true";
		
	}
	
	public void gerarRelatorio() {
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
	        
	        
	        for (Aluno aluno : listAluno) {
	        	document.add(new Paragraph(aluno.getAlunome()));
			}
	        
	        /*StringBuilder builder = new StringBuilder();
	        builder.append("estou		");
	        builder.append("concatenando ");
	        builder.append("strings");*/
	        
	        /*List<StringBuilder> text = new ArrayList<StringBuilder>();
	        text.add(builder);
	        for (StringBuilder string : text) {
	        	document.add(new Paragraph(string.toString()));
			}*/
	        
	        
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
		listAluno = new ArrayList<Aluno>();
	}

	public List<Aluno> getListAluno() {
		return listAluno;
	}

	public void setListAluno(List<Aluno> listAluno) {
		this.listAluno = listAluno;
	}

}
