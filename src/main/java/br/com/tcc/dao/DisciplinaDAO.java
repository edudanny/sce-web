package br.com.tcc.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.tcc.model.Agenda;
import br.com.tcc.model.Disciplina;
import br.com.tcc.utils.HibernateUtil;

public class DisciplinaDAO extends GenericDAO<Disciplina> {
	
	public Agenda buscarNomeDisciplina(String nome) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Agenda.class);
			consulta.addOrder(Order.asc(nome));
			Agenda resultado = (Agenda) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro){
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
