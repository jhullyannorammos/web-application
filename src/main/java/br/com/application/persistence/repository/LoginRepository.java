package br.com.application.persistence.repository;

import br.com.application.domain.Usuario;

public interface LoginRepository {
	
	Usuario getUsuario(String logon, String password);
	Boolean getUsuario(String logon);
}
