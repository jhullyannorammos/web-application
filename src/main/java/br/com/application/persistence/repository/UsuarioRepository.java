package br.com.application.persistence.repository;

import br.com.application.domain.Usuario;

public interface UsuarioRepository {
	
	Usuario recoveryPassword(String logon, String password);
	Usuario resetPassword(Usuario usuario);

}
