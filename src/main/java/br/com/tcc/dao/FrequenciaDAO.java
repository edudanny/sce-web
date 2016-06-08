package br.com.tcc.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.tcc.model.Aluno;
import br.com.tcc.model.Frequencia;
import br.com.tcc.model.Turma;
import br.com.tcc.utils.HibernateUtil;

public class FrequenciaDAO extends GenericDAO<Frequencia> {
	
	public Frequencia buscarNomeFrequencia(String nome) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Frequencia.class);
			consulta.addOrder(Order.asc(nome));
			Frequencia resultado = (Frequencia) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro){
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
