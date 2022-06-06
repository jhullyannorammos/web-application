package br.com.application.domain;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name="contatos")
@Entity
public class Contato {

	private Calendar dataNascimento;
	private String endereco;
	private String email;
	private String nome;
	private Long id;
	
	public Contato() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
