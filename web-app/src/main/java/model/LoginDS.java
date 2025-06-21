package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import control.Encrypt;

public class LoginDS {
	public boolean userExists(LoginBean bean) {
		String query;
		query = "SELECT * FROM " + TABLE_NAME + "WHERE Email= ?"; 
		String encrypted = "";
		String fromDB = "";
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bean.getEmail());
			try(ResultSet rs = preparedStatement.executeQuery()){
				if(rs.next()) {
					encrypted = rs.getString("Password");
					fromDB = Encrypt.toHash(bean.getPassword());
				}
				return encrypted.equals(fromDB);
			}
		} catch (SQLException s) {
			System.out.println("Si è verificato il seguente errore: " + s.getMessage());
			return false;
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
