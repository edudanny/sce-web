package br.com.tcc.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.tcc.model.Matricula;
import br.com.tcc.utils.HibernateUtil;

public class MatriculaDAO extends GenericDAO<Matricula> {
	
	public Matricula buscarPorAluno(String nomeMatricula) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Matricula.class);
			consulta.addOrder(Order.asc(nomeMatricula));
			Matricula resultado = (Matricula) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro){
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
