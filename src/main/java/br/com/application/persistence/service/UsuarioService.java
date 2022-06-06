package br.com.application.persistence.service;

import org.hibernate.Session;

import br.com.application.domain.Usuario;
import br.com.application.persistence.repository.UsuarioRepository;
import br.com.application.persistence.util.HibernateUtil;

public class UsuarioService implements UsuarioRepository {
	
	private Usuario usuario;
	private Session session;

	@SuppressWarnings("finally")
	public Usuario recoveryPassword(String logon, String password) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			usuario = (Usuario) session.createQuery("from Usuario where logon = :name").setParameter("logon", logon);
			usuario.setPassword(password);
		} catch (Exception exception) {
			exception.printStackTrace();
		}finally {
		    return usuario;
		}
	}
	
	public Usuario resetPassword(Usuario usuario) {
		usuario.setPassword(null);
		return usuario;
	}
	
}
