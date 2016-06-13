package br.com.tcc.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.tcc.model.Endereco;
import br.com.tcc.utils.HibernateUtil;

public class EnderecoDAO extends GenericDAO<Endereco> {
	
	public Endereco buscarNomeEndereco(String nome) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Endereco.class);
			consulta.addOrder(Order.asc(nome));
			Endereco resultado = (Endereco) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro){
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
