package br.com.tcc.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.tcc.model.Habilitacao;
import br.com.tcc.utils.HibernateUtil;

public class HabilitacaoDAO extends GenericDAO<Habilitacao> {
	
	public Habilitacao buscarNomeHabilitacao(String nome) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Habilitacao.class);
			consulta.addOrder(Order.asc(nome));
			Habilitacao resultado = (Habilitacao) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro){
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
