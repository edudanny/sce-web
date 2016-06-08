package br.com.tcc.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.tcc.model.Professor;
import br.com.tcc.utils.HibernateUtil;

public class ProfessorDAO extends GenericDAO<Professor> {
	
	public Professor buscarNomeProfessor(String nome) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Professor.class);
			consulta.addOrder(Order.asc(nome));
			Professor resultado = (Professor) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro){
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
