package br.com.tcc.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.tcc.model.Nota;
import br.com.tcc.utils.HibernateUtil;

public class NotaDAO extends GenericDAO<Nota> {
	
	public Nota buscarPorData(String data) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Nota.class);
			consulta.addOrder(Order.asc(data));
			Nota resultado = (Nota) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro){
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
