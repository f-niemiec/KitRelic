package model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationDS {
	public boolean insertUser(RegistrationBean bean) {
		try(Connection connection = ds.getConnection()){
			String query;
			query = "INSERT INTO" + TABLE_NAME + "(Password, Email, Nome, Cognome, Tipo, DataNascita) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
				preparedStatement.setString(1, bean.getPassword());
				preparedStatement.setString(2, bean.getEmail());
				preparedStatement.setString(3, bean.getNome());
				preparedStatement.setString(4, bean.getCognome());
				preparedStatement.setString(5, TipoUtente.Utente.name());
				preparedStatement.setDate(6, bean.getData());
				
				int rows = preparedStatement.executeUpdate();
				if(rows > 0) {
					System.out.println("Inserimento completato!");
					return true;
				} else {
					System.out.println("Inserimento fallito! :(");
					return false;
				}
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
