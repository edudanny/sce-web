package br.com.tcc.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.tcc.model.AnoLetivo;
import br.com.tcc.model.Usuario;
import br.com.tcc.utils.HibernateUtil;

public class AnoLetivoDAO extends GenericDAO<AnoLetivo>{
	
	public Usuario buscarNomeAnoLetivo(String nome) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.addOrder(Order.asc(nome));
			Usuario resultado = (Usuario) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro){
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
