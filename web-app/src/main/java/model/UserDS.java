package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDS implements UserDAO{
	public String getNameByMail(String email) {
		String query;
		query = "SELECT * FROM " + TABLE_NAME + " WHERE Email = ?";
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			try(ResultSet rs = preparedStatement.executeQuery()){
				if(rs.next()) {
					return rs.getString("Nome");
				}
			}
			
		} catch (SQLException s) {
			System.out.println("Si è verificato il seguente errore: " + s.getMessage());
		}
		return "";
	}
	
	public String getSurnameByMail(String email) {
		String query;
		query = "SELECT * FROM " + TABLE_NAME + " WHERE Email = ?";
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			try(ResultSet rs = preparedStatement.executeQuery()){
				if(rs.next()) {
					return rs.getString("Cognome");
				}
			}
			
		} catch (SQLException s) {
			System.out.println("Si è verificato il seguente errore: " + s.getMessage());
		}
		return "";
	}
	
	public String getTypeByMail(String email) {
		String query;
		query = "SELECT * FROM " + TABLE_NAME + " WHERE Email = ?";
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			try(ResultSet rs = preparedStatement.executeQuery()){
				if(rs.next()) {
					return rs.getString("Tipo");
				}
			}
			
		} catch (SQLException s) {
			System.out.println("Si è verificato il seguente errore: " + s.getMessage());
		}
		return "";
	}
	
	public void removeUserByMail(String email) {
		String query;
		query = "DELETE FROM " + TABLE_NAME + " WHERE Email = ?";
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.executeUpdate();
		} catch (SQLException s) {
			System.out.println("Si è verificato il seguente errore: " + s.getMessage());
		}
	}
	
	
	
	
	private static DataSource ds;
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/KitRelicDB");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
			
		}		
	}
	private static final String TABLE_NAME = "Utenti";
}
