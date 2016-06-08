package br.com.tcc.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.tcc.model.Aluno;
import br.com.tcc.model.Turma;
import br.com.tcc.utils.HibernateUtil;

public class AlunoDAO extends GenericDAO<Aluno> {
	
	public Turma buscarNomeAluno(String nome) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Aluno.class);
			consulta.addOrder(Order.asc(nome));
			Turma resultado = (Turma) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro){
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
