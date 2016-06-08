package br.com.tcc.mbean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.omnifaces.util.Messages;

import br.com.tcc.dao.AlunoDAO;
import br.com.tcc.dao.AnoLetivoDAO;
import br.com.tcc.dao.FrequenciaDAO;
import br.com.tcc.dao.MatriculaDAO;
import br.com.tcc.model.Aluno;
import br.com.tcc.model.AnoLetivo;
import br.com.tcc.model.Frequencia;
import br.com.tcc.model.Matricula;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class RelatorioBean implements Serializable {
	
	private Aluno aluno;
	private List<Aluno> listAluno;
	
	public String listar() {
		novo();
		try {
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as relatórios");
			erro.printStackTrace();
		}
		return "/pages/relatorio/opcaoRelatorio.xhtml?faces-redirect=true";
		
	}
	
	public String prepararFrequencia() {
		return "/pages/relatorio/listFrequencia.xhtml?faces-redirect=true";
		
	}
	
	public void pesquisarAluno() {
		AlunoDAO alunoDAO = new AlunoDAO();
		String parametro = "";
		if (!aluno.getAlunome().equals(null) && !aluno.getAlunome().trim().equals("")) {
			parametro = "%"+aluno.getAlunome()+"%";
			listAluno = alunoDAO.buscar(Aluno.QUERY_SEARCH_ALU_NOME, parametro);
		} else if (!aluno.getAlumatricula().equals(null) && !aluno.getAlumatricula().trim().equals(""))  {
			parametro = "%"+aluno.getAlumatricula()+"%";
			listAluno = alunoDAO.buscar(Aluno.QUERY_SEARCH_ALU_MAT, parametro);
		} else {
			listAluno = alunoDAO.listar();
		}
		
	}
	
	public void gerarRelatorioFrequencia(Aluno alu) {
		FacesContext context = FacesContext.getCurrentInstance(); 
	    HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();  
	    response.setContentType("application/pdf");  
	    response.setHeader("Content-disposition",  "inline=filename=file.pdf");
	    
	    try {
	        Document document = new Document();
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        PdfWriter.getInstance(document, baos);

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
	        
	        document.add(new Paragraph("Aluno(a): " + alu.getAlunome()));
	        document.add(new Paragraph(" "));
	        
	        for (int i = 0; i < 2; i++) {
	            table.addCell("Data");
	            table.addCell("Frequencia");
	        }
	        
	        table.setHeaderRows(3);
	        table.setFooterRows(1);
	        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
	        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        
			FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
			
			alu.setListFrequencia(frequenciaDAO.buscar(Frequencia.QUERY_SEARCH_FREQ_ALU, alu.getAlucodigo()));
			
			SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
			
			for (int i = 0; i < alu.getListFrequencia().size(); i++) {
				table.addCell(dt.format(alu.getListFrequencia().get(i).getFredata()));
				if (alu.getListFrequencia().get(i).getFrefrequencia()) {
					table.addCell("Presente");
				} else {
					table.addCell("Ausente");
				}
			}
	        
	        document.add(table);
	        document.close();

	        response.setHeader("Expires", "0");
	        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
	        response.setHeader("Pragma", "public");
	        response.setContentType("application/pdf");
	        response.setContentLength(baos.size());
	        
	        OutputStream os = response.getOutputStream();
	        
	        baos.writeTo(os);
	        
	        os.flush();
	        os.close();
	    }
	    catch(DocumentException e) {
	    	e.printStackTrace();
	    }
	    catch (IOException e) {
	    	e.printStackTrace();
	    }
	    catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    context.responseComplete();
	}
	
	public void gerarRelatorioAlunosAnos() {
		FacesContext context = FacesContext.getCurrentInstance(); 
	    HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();  
	    response.setContentType("application/pdf");  
	    response.setHeader("Content-disposition",  "inline=filename=file.pdf");

	    try {
	        Document document = new Document();
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        PdfWriter.getInstance(document, baos);

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
	        document.add(new Paragraph("Total de alunos matriculados: " + listaMatricula));
	        document.close();

	        response.setHeader("Expires", "0");
	        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
	        response.setHeader("Pragma", "public");
	        response.setContentType("application/pdf");
	        response.setContentLength(baos.size());
	        
	        OutputStream os = response.getOutputStream();
	        
	        baos.writeTo(os);
	        
	        os.flush();
	        os.close();
	    }
	    catch(DocumentException e) {
	    	e.printStackTrace();
	    }
	    catch (IOException e) {
	    	e.printStackTrace();
	    }
	    catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    context.responseComplete();
	}

	public void novo() {
		aluno = new Aluno();
		listAluno = new ArrayList<Aluno>();
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getListAluno() {
		return listAluno;
	}

	public void setListAluno(List<Aluno> listAluno) {
		this.listAluno = listAluno;
	}

}
