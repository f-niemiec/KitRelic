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

public class PaymentCardDS implements PaymentCardDAO{
	public int doSave(PaymentCardBean bean) {
		String query;
		query = "INSERT INTO " + TABLE_NAME + " (Scadenza, NumeroCarta, CVV, Proprietario, IDUtente)"
				+ "VALUES(?, ?, ?, ?, ?)";
		int upp = 0;
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setDate(1, java.sql.Date.valueOf(bean.getExpires()));
			preparedStatement.setInt(2, bean.getCardNumber());
			preparedStatement.setInt(3, bean.getCvv());
			preparedStatement.setString(4, bean.getOwner());
			preparedStatement.setInt(5, bean.getUserId());
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
	
	public PaymentCardBean doRetrieveByKey(int id) {
		String query;
		query = "SELECT * FROM " + TABLE_NAME + " WHERE ID = ?";
		PaymentCardBean bean = new PaymentCardBean();
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();{
				while(rs.next()) {
					bean.setId(rs.getInt("ID"));
					bean.setUserId(rs.getInt("IDUtente"));
					bean.setExpires(rs.getDate("Scadenza").toLocalDate());
					bean.setCvv(rs.getInt("CVV"));
					bean.setOwner(rs.getString("Proprietario"));
					bean.setCardNumber(rs.getInt("NumeroCarta"));
				}
			}
		} catch (SQLException s) {
			System.out.println("Si è verificato il seguente errore: " + s.getMessage());
		}
		return bean;
	}
	
	public Collection<PaymentCardBean> doRetrieveByUserID(int userId){
		String query;
		query = "SELECT * FROM " + TABLE_NAME + " WHERE IDUtente = ?";
		Collection<PaymentCardBean> cards = new LinkedList<PaymentCardBean>();
		try(Connection connection = ds.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();{
				while(rs.next()) {
					PaymentCardBean bean = new PaymentCardBean();
					bean.setId(rs.getInt("ID"));
					bean.setUserId(rs.getInt("IDUtente"));
					bean.setExpires(rs.getDate("Scadenza").toLocalDate());
					bean.setCvv(rs.getInt("CVV"));
					bean.setOwner(rs.getString("Proprietario"));
					bean.setCardNumber(rs.getInt("NumeroCarta"));
					cards.add(bean);
				}
			}
		} catch (SQLException s) {
			System.out.println("Si è verificato il seguente errore: " + s.getMessage());
		}
		return cards;
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
	private static final String TABLE_NAME = "MetodoDiPagamento";
}
