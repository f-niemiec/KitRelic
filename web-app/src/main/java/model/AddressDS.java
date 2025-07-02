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

public class AddressDS implements AddressDAO{
	public int doSave(AddressBean bean) {
		String query = "INSERT INTO " + TABLE_NAME + " (Tipo, Attivo, Citta, Via, Provincia, Paese, CodUtente) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try(Connection connection = ds.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, bean.getAddress().name());
            preparedStatement.setBoolean(2, bean.isActive());
            preparedStatement.setString(3, bean.getCity());
            preparedStatement.setString(4, bean.getStreet());
            preparedStatement.setString(5, bean.getProvince());
            preparedStatement.setString(6, bean.getCountry());
            preparedStatement.setInt(7, bean.getUserId());
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        bean.setId(id);
                        return id;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
	
	public Collection<AddressBean> doRetrieveAll(String order){
		String query;
		query = "SELECT * FROM " + TABLE_NAME;
		Collection<AddressBean> addresses = new LinkedList<AddressBean>();
		if(order!=null && !order.equals("")) {
			query += " ORDER BY " + order;
		}
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();{
				while(rs.next()) {
					AddressBean bean = new AddressBean();
					bean.setId(rs.getInt("ID"));
					bean.setUserId(rs.getInt("CodUtente"));
					bean.setActive(rs.getBoolean("Attivo"));
					bean.setAddress(TipoIndirizzo.valueOf(rs.getString("Tipo")));
					bean.setCity(rs.getString("Citta"));
					bean.setCountry(rs.getString("Paese"));
					bean.setProvince(rs.getString("Provincia"));
					bean.setStreet(rs.getString("Via"));
					addresses.add(bean);
				}
			}
		} catch (SQLException s) {
			System.out.println("Si è verificato il seguente errore: " + s.getMessage());
		}
		return addresses;
	}
	
	public Collection<AddressBean> doRetrieveBilling(int userId){
		String query;
		query = "SELECT * FROM " + TABLE_NAME + " WHERE CodUtente = ? AND Tipo = ?";
		Collection<AddressBean> addresses = new LinkedList<AddressBean>();
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, TipoIndirizzo.Billing.name());
			ResultSet rs = preparedStatement.executeQuery();{
				while(rs.next()) {
					AddressBean bean = new AddressBean();
					bean.setId(rs.getInt("ID"));
					bean.setUserId(rs.getInt("CodUtente"));
					bean.setActive(rs.getBoolean("Attivo"));
					bean.setAddress(TipoIndirizzo.valueOf(rs.getString("Tipo")));
					bean.setCity(rs.getString("Citta"));
					bean.setCountry(rs.getString("Paese"));
					bean.setProvince(rs.getString("Provincia"));
					bean.setStreet(rs.getString("Via"));
					addresses.add(bean);
				}
			}
		} catch (SQLException s) {
			System.out.println("Si è verificato il seguente errore: " + s.getMessage());
		}
		return addresses;
	}
	
	public Collection<AddressBean> doRetrieveShipping(int userId){
		String query;
		query = "SELECT * FROM " + TABLE_NAME + " WHERE CodUtente = ? AND Tipo = ?";
		Collection<AddressBean> addresses = new LinkedList<AddressBean>();
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, TipoIndirizzo.Shipping.name());
			ResultSet rs = preparedStatement.executeQuery();{
				while(rs.next()) {
					AddressBean bean = new AddressBean();
					bean.setId(rs.getInt("ID"));
					bean.setUserId(rs.getInt("CodUtente"));
					bean.setActive(rs.getBoolean("Attivo"));
					bean.setAddress(TipoIndirizzo.valueOf(rs.getString("Tipo")));
					bean.setCity(rs.getString("Citta"));
					bean.setCountry(rs.getString("Paese"));
					bean.setProvince(rs.getString("Provincia"));
					bean.setStreet(rs.getString("Via"));
					addresses.add(bean);
				}
			}
		} catch (SQLException s) {
			System.out.println("Si è verificato il seguente errore: " + s.getMessage());
		}
		return addresses;
	}
	
	public AddressBean doRetrieveByKey(int id) {
			String query;
			query = "SELECT * FROM " + TABLE_NAME + " WHERE ID = ?";
			AddressBean bean = new AddressBean();
			try(Connection connection = ds.getConnection()){
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);
				ResultSet rs = preparedStatement.executeQuery();{
					while(rs.next()) {
						bean.setId(rs.getInt("ID"));
						bean.setUserId(rs.getInt("CodUtente"));
						bean.setActive(rs.getBoolean("Attivo"));
						bean.setAddress(TipoIndirizzo.valueOf(rs.getString("Tipo")));
						bean.setCity(rs.getString("Citta"));
						bean.setCountry(rs.getString("Paese"));
						bean.setProvince(rs.getString("Provincia"));
						bean.setStreet(rs.getString("Via"));
					}
				}
				} catch (SQLException s) {
				System.out.println("Si è verificato il seguente errore: " + s.getMessage());
			}
			return bean;
	}
	
	public AddressBean doRetrieveByUserID(int userId) {
		String query;
		query = "SELECT * FROM " + TABLE_NAME + " WHERE CodUtente = ?";
		AddressBean bean = new AddressBean();
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();{
				while(rs.next()) {
					bean.setId(rs.getInt("ID"));
					bean.setUserId(rs.getInt("CodUtente"));
					bean.setActive(rs.getBoolean("Attivo"));
					bean.setAddress(TipoIndirizzo.valueOf(rs.getString("Tipo")));
					bean.setCity(rs.getString("Citta"));
					bean.setCountry(rs.getString("Paese"));
					bean.setProvince(rs.getString("Provincia"));
					bean.setStreet(rs.getString("Via"));
				}
			}
		} catch (SQLException s) {
			System.out.println("Si è verificato il seguente errore: " + s.getMessage());
		}
		return bean;
	}
	
	public AddressBean doRetrieveActiveBilling(int userId) {
		String query;
		query = "SELECT * FROM " + TABLE_NAME + " WHERE CodUtente = ? And Tipo = ? AND Attivo";
		AddressBean bean = new AddressBean();
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, TipoIndirizzo.Billing.name());
			ResultSet rs = preparedStatement.executeQuery();{
				while(rs.next()) {
					bean.setId(rs.getInt("ID"));
					bean.setUserId(rs.getInt("CodUtente"));
					bean.setActive(rs.getBoolean("Attivo"));
					bean.setAddress(TipoIndirizzo.valueOf(rs.getString("Tipo")));
					bean.setCity(rs.getString("Citta"));
					bean.setCountry(rs.getString("Paese"));
					bean.setProvince(rs.getString("Provincia"));
					bean.setStreet(rs.getString("Via"));
				}
			}
		} catch (SQLException s) {
			System.out.println("Si è verificato il seguente errore: " + s.getMessage());
		}
		return bean;
	}
	
	public AddressBean doRetrieveActiveShipping(int userId) {
		String query;
		query = "SELECT * FROM " + TABLE_NAME + " WHERE CodUtente = ? AND Tipo = ? AND Attivo";
		AddressBean bean = new AddressBean();
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, TipoIndirizzo.Shipping.name());
			ResultSet rs = preparedStatement.executeQuery();{
				while(rs.next()) {
					bean.setId(rs.getInt("ID"));
					bean.setUserId(rs.getInt("CodUtente"));
					bean.setActive(rs.getBoolean("Attivo"));
					bean.setAddress(TipoIndirizzo.valueOf(rs.getString("Tipo")));
					bean.setCity(rs.getString("Citta"));
					bean.setCountry(rs.getString("Paese"));
					bean.setProvince(rs.getString("Provincia"));
					bean.setStreet(rs.getString("Via"));
				}
			}
		} catch (SQLException s) {
			System.out.println("Si è verificato il seguente errore: " + s.getMessage());
		}
		return bean;
	}
	
	public void doUpdateActive(int id) {
		String getUserAndTypeQuery = "SELECT CodUtente, Tipo FROM " + TABLE_NAME + " WHERE ID = ?";
	    String disableSameTypeQuery = "UPDATE " + TABLE_NAME + " SET Attivo = FALSE WHERE CodUtente = ? AND Tipo = ? AND Attivo = TRUE";
	    String activateQuery = "UPDATE " + TABLE_NAME + " SET Attivo = TRUE WHERE ID = ?";
	    try (Connection connection = ds.getConnection()) {
	        int userId = -1;
	        String tipo = null;
	        try (PreparedStatement psGet = connection.prepareStatement(getUserAndTypeQuery)) {
	            psGet.setInt(1, id);
	            try (ResultSet rs = psGet.executeQuery()) {
	                if (rs.next()) {
	                    userId = rs.getInt("CodUtente");
	                    tipo = rs.getString("Tipo");
	                } else {
	                    return;
	                }
	            }
	        }
	        try (PreparedStatement psDisable = connection.prepareStatement(disableSameTypeQuery)) {
	            psDisable.setInt(1, userId);
	            psDisable.setString(2, tipo);
	            psDisable.executeUpdate();
	        }
	        try (PreparedStatement psActivate = connection.prepareStatement(activateQuery)) {
	            psActivate.setInt(1, id);
	            psActivate.executeUpdate();
	        }
	    } catch (SQLException e) {
	        System.out.println("Si è verificato un errore: " + e.getMessage());
	    }
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
	private static final String TABLE_NAME = "Indirizzi";
}
