package br.com.tcc.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.tcc.model.Ministra;
import br.com.tcc.model.Responsavel;
import br.com.tcc.utils.HibernateUtil;

public class MinistraDAO extends GenericDAO<Ministra> {
	
	public Ministra buscarNomeResponsavel(String nomeProfessor) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Responsavel.class);
			consulta.addOrder(Order.asc(nomeProfessor));
			Ministra resultado = (Ministra) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro){
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
