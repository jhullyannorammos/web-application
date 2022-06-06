package br.com.application.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.application.domain.Colaborador;
import br.com.application.domain.Contato;
import br.com.application.persistence.util.JDBConnectionUtil;

public class ContatoPersistence {
	
	String INSERT = "insert into contatos (nome, email, endereco, dataNascimento) values (? , ? ,  ? , ?)";
	String SELECT = "SELECT * FROM CONTATOS";
	
	private List<Contato> contatos;
	private Contato contato;
	
	PreparedStatement preparedStatement;
	Connection connection;
	ResultSet resultSet;
	
	public ContatoPersistence() {
		connection = JDBConnectionUtil.getConectionfactory();
	}
	
	@SuppressWarnings("finally")
	public List<Contato> findAll() throws Exception {
		preparedStatement = connection.prepareStatement(SELECT);
		resultSet = preparedStatement.executeQuery();
		contatos = new ArrayList<Contato>();
		try {
			while (resultSet.next()) {
				contato = new Contato();
				Calendar data = Calendar.getInstance();
				data.setTime(resultSet.getDate("dataNascimento"));
				contato.setEndereco(resultSet.getString("endereco"));
				contato.setNome(resultSet.getString("nome"));
				contato.setId(resultSet.getLong("id"));
				contato.setEmail(resultSet.getString("email"));
				contato.setDataNascimento(data);
				contatos.add(contato);
			}
		} catch (Exception SQLexception) {
			JDBConnectionUtil.rollback(connection);
			throw new RuntimeException(SQLexception);
		}finally {
			JDBConnectionUtil.close(connection, preparedStatement, resultSet);
			return contatos;
		}
	}
	
	public void update(Colaborador colaborador) throws Exception {
		try {
			
		} catch (Exception exception) {
			exception.printStackTrace();
			JDBConnectionUtil.rollback(connection);
		} finally {
			JDBConnectionUtil.close(connection, preparedStatement);
		}
	}
	
	public void save(Contato contato) throws Exception {
		java.sql.Date dataParaGravar = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		try {
			preparedStatement = connection.prepareStatement(INSERT);
			preparedStatement.setString(1, contato.getNome());
			preparedStatement.setString(2, contato.getEmail());
			preparedStatement.setString(3, contato.getEndereco());
			preparedStatement.setDate(4, dataParaGravar);
			preparedStatement.execute();
		} catch (Exception exception) {
			exception.printStackTrace();
			JDBConnectionUtil.rollback(connection);
		} finally {
			JDBConnectionUtil.close(connection, preparedStatement);
		}
	}
	
	public void remove(Contato contato) throws SQLException {
		try {
		   preparedStatement = connection.prepareStatement("delete from contatos where id=?");
		   preparedStatement.setLong(1, contato.getId());
		   preparedStatement.execute();
		} catch (SQLException e) {
		   throw new RuntimeException(e);
		} finally {
			preparedStatement.close();
		} 
	}
}
