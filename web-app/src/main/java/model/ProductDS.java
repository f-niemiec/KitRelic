package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDS implements ProductDAO{
	public boolean doSave(ProductBean bean) {
		String query;
		query = "INSERT INTO " + TABLE_NAME + " (Nome, Prezzo, Quantita, Tipo, Taglia, Tendenza, Nuovo, Descrizione)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		int upp = 0;
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bean.getName());
			preparedStatement.setDouble(2, bean.getPrice());
			preparedStatement.setInt(3, bean.getQuantity());
			preparedStatement.setString(4, bean.getType());
			preparedStatement.setString(5, bean.getSize());
			preparedStatement.setBoolean(6, bean.isTrend());
			preparedStatement.setBoolean(7, bean.isRecent());
			preparedStatement.setString(8, bean.getDescription());
			upp = preparedStatement.executeUpdate();
			if(upp > 0) {
				return true;
			}	
			} catch (SQLException s) {
			System.out.println("Si è verificato il seguente errore: " + s.getMessage());
		}
		return false;
	}
	
	public ProductBean doRetrieveByKey(int id) {
		String query;
		query = "SELECT * FROM " + TABLE_NAME + "WHERE ID = ?";
		ProductBean bean = new ProductBean();
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();{
				while(rs.next()) {
					bean.setName(rs.getString("Nome"));
					bean.setPrice(rs.getDouble("Prezzo"));
					bean.setQuantity(rs.getInt("Quantita"));
					bean.setType(rs.getString("Tipo"));
					bean.setSize(rs.getString("Taglia"));
					bean.setRecent(rs.getBoolean("Nuovo"));
					bean.setTrend(rs.getBoolean("Tendenza"));
					bean.setDescription(rs.getString("Descrizione"));
				}
			}
			} catch (SQLException s) {
			System.out.println("Si è verificato il seguente errore: " + s.getMessage());
		}
		return bean;
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
