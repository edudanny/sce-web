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
		
		MatriculaDAO dao = new MatriculaDAO();
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
		}
		
		
		
	}

}
