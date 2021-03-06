package br.com.tcc.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.tcc.model.Secretaria;
import br.com.tcc.utils.HibernateUtil;

public class SecretariaDAO extends GenericDAO<Secretaria>{
	
	public Secretaria buscarNomeSecretaria(String nome) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Secretaria.class);
			consulta.addOrder(Order.asc(nome));
			Secretaria resultado = (Secretaria) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro){
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
}
