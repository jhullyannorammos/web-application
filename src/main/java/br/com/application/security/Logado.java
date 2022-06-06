package br.com.application.security;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Logado implements Serializable {

	private String perfil;
	private String status;
	private String nome;
	private Date dataLogin;
    private boolean logado;
	
	public boolean isLogado() {
		return this.logado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataLogin() {
		return dataLogin;
	}

	public void setDataLogin(Date dataLogin) {
		this.dataLogin = dataLogin;
	}
	
	public String getPerfil() {
		return perfil;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

}
