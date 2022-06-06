package br.com.application.persistence.service;

import org.hibernate.Session;

import br.com.application.domain.Usuario;
import br.com.application.persistence.repository.LoginRepository;
import br.com.application.persistence.util.HibernateUtil;

public class LoginService implements LoginRepository {

	private Usuario usuario;
	private Session session;
	
	@SuppressWarnings("finally")
	public Usuario getUsuario(String logon, String password) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			usuario = (Usuario) session.createQuery("from Usuario where logon = :logon and password = :password")
					.setParameter("password", password)
					.setParameter("logon", logon);
		} catch (Exception exception) {
			exception.printStackTrace();
		}finally {
			return usuario;
		}

	}

	
	@SuppressWarnings("finally")
	public Boolean getUsuario(String logon) {
		boolean us = Boolean.FALSE;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			usuario = (Usuario) session.createQuery("from Usuario where logon = :name").setParameter("logon", logon);
			if(usuario != null) {
				us = Boolean.TRUE;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}finally {
			return us;
		}
	}

}
