package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDS implements ProductDAO{
	public int doSave(ProductBean bean) {
		String query;
		query = "INSERT INTO " + TABLE_NAME + " (Nome, Prezzo, Quantita, Tipo, Taglia, Tendenza, Nuovo, Descrizione)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		int upp = 0;
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
				ResultSet rs = preparedStatement.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					bean.setId(id);
					return id;
				}
			}	
			} catch (SQLException s) {
			System.out.println("Si è verificato il seguente errore: " + s.getMessage());
		}
		return 0;
	}
	
	public ProductBean doRetrieveByKey(int id) {
		String query;
		query = "SELECT * FROM " + TABLE_NAME + " WHERE ID = ?";
		ProductBean bean = new ProductBean();
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();{
				while(rs.next()) {
					bean.setId(rs.getInt("ID"));
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
	
	public Collection<ProductBean> doRetrieveAll(String order){
		String query;
		query = "SELECT * FROM " + TABLE_NAME;
		Collection<ProductBean> products = new LinkedList<ProductBean>();
		if(order!=null && !order.equals("")) {
			query += "ORDER BY " + order;
		}
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();{
				while(rs.next()) {
					ProductBean bean = new ProductBean();
					bean.setId(rs.getInt("ID"));
					bean.setName(rs.getString("Nome"));
					bean.setPrice(rs.getDouble("Prezzo"));
					bean.setQuantity(rs.getInt("Quantita"));
					bean.setType(rs.getString("Tipo"));
					bean.setSize(rs.getString("Taglia"));
					bean.setRecent(rs.getBoolean("Nuovo"));
					bean.setTrend(rs.getBoolean("Tendenza"));
					bean.setDescription(rs.getString("Descrizione"));
					products.add(bean);
				}
			}
			} catch (SQLException s) {
			System.out.println("Si è verificato il seguente errore: " + s.getMessage());
		}
		return products;
	}
	
	public boolean doDelete(int id) {
		String query;
		query = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";
		int result = 0;
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeUpdate();
			} catch (SQLException s) {
			System.out.println("Si è verificato il seguente errore: " + s.getMessage());
		}
		if(result != 0)
			return true;
		else 
			return false;
	}
	
	public boolean doUpdatePrice(int id, double newPrice) {
	    String query = "UPDATE " + TABLE_NAME + " SET Prezzo = ? WHERE ID = ?";
	    int result = 0;
	    try (Connection connection = ds.getConnection()) {
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setDouble(1, newPrice);
	        preparedStatement.setInt(2, id);
	        result = preparedStatement.executeUpdate();
	    } catch (SQLException s) {
	        System.out.println("Si è verificato il seguente errore nell'aggiornamento del prezzo: " + s.getMessage());
	    }
	    
	    return result > 0;
	}
	
	public boolean doUpdateQuantity(int id, int update) {
	    String query = "UPDATE " + TABLE_NAME + " SET Quantita = Quantita + ? WHERE ID = ?";
	    int result = 0;
	    try (Connection connection = ds.getConnection()) {
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setInt(1, update);
	        preparedStatement.setInt(2, id);
	        result = preparedStatement.executeUpdate();
	    } catch (SQLException s) {
	        System.out.println("Errore durante l'aggiornamento della quantità: " + s.getMessage());
	    }

	    return result > 0;
	}
	
	public boolean doUpdateTrend(int id) {
	    String query = "UPDATE " + TABLE_NAME + " SET Tendenza = NOT Tendenza WHERE ID = ?";
	    int result = 0;
	    try (Connection connection = ds.getConnection()) {
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setInt(1, id);
	        result = preparedStatement.executeUpdate();
	    } catch (SQLException s) {
	        System.out.println("Errore durante l'aggiornamento della tendenza: " + s.getMessage());
	    }

	    return result > 0;
	}
	
	public boolean doUpdateNew(int id) {
	    String query = "UPDATE " + TABLE_NAME + " SET Nuovo = NOT Nuovo WHERE ID = ?";
	    int result = 0;
	    try (Connection connection = ds.getConnection()) {
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setInt(1, id);
	        result = preparedStatement.executeUpdate();
	    } catch (SQLException s) {
	        System.out.println("Errore durante l'aggiornamento della tendenza: " + s.getMessage());
	    }

	    return result > 0;
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
