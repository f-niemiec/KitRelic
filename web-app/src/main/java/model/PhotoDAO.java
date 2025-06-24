package model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PhotoDAO {
	public synchronized static byte[] load(int id) {
		String query;
		query = "SELECT Foto FROM " + TABLE_NAME + " WHERE ID = ?";
		byte[] bt = null;
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				bt = rs.getBytes("Foto");
			}
		} catch (SQLException s) {
			System.out.println("Si è verificato il seguente errore: " + s.getMessage());
		}
		return bt;
	}
	public synchronized static void updatePhoto(int id, InputStream photo) {
		String query;
		query = "UPDATE" + TABLE_NAME + "SET Foto = ? WHERE ID = ?";
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			try {
				preparedStatement.setBinaryStream(1, photo, photo.available());
				preparedStatement.setInt(2, id);
				preparedStatement.executeUpdate();
			} catch(IOException e) {
				System.out.println("Errore nel caricamento della foto: " + e);
			}
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
	private static final String TABLE_NAME = "Prodotti";
}
