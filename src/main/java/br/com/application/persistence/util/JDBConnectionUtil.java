package br.com.application.persistence.util;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class JDBConnectionUtil implements Serializable {

	private static final String serverTimeZone = "?serverTimezone=UTC";
	private static final String useSSL = "&useSSL=false";
	private static final String DATABASE = "application" + serverTimeZone + useSSL;
	private static final String MYSQLCONNECTION = "jdbc:mysql://127.0.0.1:3306/";
	private static final String PASSWORD = "$Pr!ngf!3ld";
	private static final String ROOT = "root";


	public JDBConnectionUtil() {

	}

	@SuppressWarnings("finally")
	public static Connection getConectionfactory() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(MYSQLCONNECTION + DATABASE, ROOT, PASSWORD);
			connection.setAutoCommit(Boolean.FALSE);
		}catch(SQLException sQLException) {
			sQLException.printStackTrace();
			throw new RuntimeException(sQLException);
		} finally {
			return connection;
		}
	}
	
	@SuppressWarnings("finally")
	public static void setConectionfactory() {

		try(Connection connection = new JDBConnectionUtil().getConectionfactory()) {
			
		}catch(SQLException sQLException) {
			sQLException.printStackTrace();
			throw new RuntimeException(sQLException);
		} 
	    
	}

	public static void commit(Connection connection) throws SQLException {
		if (connection != null) {
			connection.commit();
		}
	}

	public static void rollback(Connection connection) throws SQLException {
		if (connection != null) {
			connection.rollback();
		}
	}

	public static void close(Connection connection) throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

	public static void close(Connection connection, PreparedStatement preparedStatement) throws SQLException {
		if (connection != null) {
			connection.close();
			preparedStatement.close();
		}
	}

	public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet)
			throws SQLException {
		if (connection != null) {
			connection.close();
			preparedStatement.close();
			resultSet.close();
		}
	}
}
