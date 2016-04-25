package br.com.tcc.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.tcc.dao.AnoLetivoDAO;
import br.com.tcc.dao.MatriculaDAO;
import br.com.tcc.model.Aluno;
import br.com.tcc.model.AnoLetivo;
import br.com.tcc.model.Matricula;


public class Test {
	
	public static void main(String[] args) {
		
		/*MatriculaDAO dao = new MatriculaDAO();
		List<Matricula> matriculas = new ArrayList<Matricula>();
				
		AnoLetivo anoLetivo = new AnoLetivo();
		AnoLetivoDAO anoLetivoDAO = new AnoLetivoDAO();
		
		anoLetivo = anoLetivoDAO.buscar(1);
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		matriculas = dao.buscar(Matricula.QUERY_SEARCH_ALU, anoLetivo.getAnocodigo());
		
		for (Matricula matricula : matriculas) {
			alunos.add(matricula.getAluno());
		}
		
		for (Aluno aluno : alunos) {
			System.out.println(aluno.getAlunome());
			System.out.println(aluno.getAlurescodigo().getResnome());
		}*/
		char aux = 'C';
		for (char ch = 'A'; ch <= 'Z'; ch++) {  
		    //int i = ch;  // cast desnecessario, mas explicativo
		    if (ch == aux) {
		    	System.out.println(ch);
			}
		}  
		/*for (int i = 65; i <= 90; i++) {  
		    char ch = (char) i;  
		    System.out.println(i + "  " + ch);  
		} */ 
		
		
	}

}
