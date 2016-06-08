package br.com.tcc.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.tcc.model.Usuario;
import br.com.tcc.utils.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {
	
	public Usuario buscarNomeProfessor(String login, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.addOrder(Order.asc(login));
			Usuario resultado = (Usuario) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro){
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
