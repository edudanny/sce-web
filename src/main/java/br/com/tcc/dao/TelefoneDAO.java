package br.com.tcc.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.tcc.model.Telefone;
import br.com.tcc.utils.HibernateUtil;

public class TelefoneDAO extends GenericDAO<Telefone> {
	
	public Telefone buscarPorTelefone(String numero) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Telefone.class);
			consulta.addOrder(Order.asc(numero));
			Telefone resultado = (Telefone) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro){
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
