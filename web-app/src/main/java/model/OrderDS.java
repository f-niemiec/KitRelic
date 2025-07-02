package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrderDS implements OrderDAO {
    public int doSave(OrderBean ordine) {
        String insertOrder = "INSERT INTO " + TABLE_NAME + " (Data, Costo, IdUtente, IDCarta, IDIndirizzo, IDFatturazione) VALUES (?, ?, ?, ?, ?, ?)";
        String insertItem = "INSERT INTO " + ITEM_TABLE_NAME + " (OrdineID, ProdottoID, Quantita) VALUES (?, ?, ?)";
        int orderId = 0;
        try (Connection connection = ds.getConnection()) {
            PreparedStatement psOrdine = connection.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS);
            psOrdine.setDate(1, Date.valueOf(ordine.getDate()));
            psOrdine.setDouble(2, ordine.getTotal());
            psOrdine.setInt(3, ordine.getUserId());
            psOrdine.setInt(4, ordine.getCard().getId());
            psOrdine.setInt(5, ordine.getShipping().getId());
            psOrdine.setInt(6, ordine.getBilling().getId());
            int update = psOrdine.executeUpdate();
            if (update > 0) {
                ResultSet rs = psOrdine.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                    ordine.setId(orderId);
                }
            }
            if (orderId > 0 && ordine.getItems() != null) {
                PreparedStatement psItem = connection.prepareStatement(insertItem);
                for (OrderItemBean item : ordine.getItems()) {
                    psItem.setInt(1, orderId);
                    psItem.setInt(2, item.getProductId());
                    psItem.setInt(3, item.getQuantity());
                    psItem.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Errore durante il salvataggio dell'ordine: " + e.getMessage());
        }
        return orderId;
    }

    public OrderBean doRetrieveByKey(int id) {
        String queryOrdine = "SELECT * FROM " + TABLE_NAME + " WHERE ID = ?";
        String queryItems = "SELECT o.Quantita, p.* FROM OggettoOrdine o JOIN Prodotti p ON o.ProdottoID = p.ID WHERE o.OrdineID = ?";
        OrderBean ordine = null;
        try (Connection connection = ds.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(queryOrdine);
            preparedStatement.setInt(1, id);
            ResultSet rsOrdine = preparedStatement.executeQuery();
            if (rsOrdine.next()) {
                ordine = new OrderBean();
                ordine.setId(rsOrdine.getInt("ID"));
                ordine.setDate(rsOrdine.getDate("Data").toLocalDate());
                ordine.setTotal(rsOrdine.getDouble("Costo"));
                ordine.setUserId(rsOrdine.getInt("IdUtente"));
                PaymentCardBean card = new PaymentCardBean();
                card.setId(rsOrdine.getInt("IDCarta"));
                ordine.setCard(card);
                AddressBean shipping = new AddressBean();
                shipping.setId(rsOrdine.getInt("IDIndirizzo"));
                ordine.setShipping(shipping);
                AddressBean billing = new AddressBean();
                billing.setId(rsOrdine.getInt("IDFatturazione"));
                ordine.setBilling(billing);
                Collection<OrderItemBean> items = new LinkedList<>();
                PreparedStatement psItems = connection.prepareStatement(queryItems);
                psItems.setInt(1, id);
                ResultSet rsItems = psItems.executeQuery();
                while (rsItems.next()) {
                    OrderItemBean item = new OrderItemBean();
                    item.setProductId(rsItems.getInt("ID"));
                    item.setProductName(rsItems.getString("Nome"));
                    item.setProductSize(rsItems.getString("Taglia"));
                    item.setProductPrice(rsItems.getDouble("Prezzo"));
                    item.setQuantity(rsItems.getInt("Quantita"));
                    items.add(item);
                }
                ordine.setItems(items);
            }
        } catch (SQLException e) {
            System.out.println("Errore durante il recupero dell'ordine: " + e.getMessage());
        }
        return ordine;
    }

    public Collection<OrderBean> doRetrieveByUserID(int userId) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE IDUtente = ?";
        Collection<OrderBean> orders = new LinkedList<>();
        try (Connection connection = ds.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                OrderBean order = doRetrieveByKey(rs.getInt("ID"));
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Errore durante il recupero ordini utente: " + e.getMessage());
        }
        return orders;
    }

    public Collection<OrderBean> doRetrieveAll(String order) {
        String query = "SELECT * FROM " + TABLE_NAME;
        if (order != null && !order.equals("")) {
            query += " ORDER BY " + order;
        }
        Collection<OrderBean> orders = new LinkedList<>();
        try (Connection connection = ds.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                OrderBean ordine = doRetrieveByKey(rs.getInt("ID"));
                orders.add(ordine);
            }
        } catch (SQLException e) {
            System.out.println("Errore durante il recupero di tutti gli ordini: " + e.getMessage());
        }
        return orders;
    }

    public Collection<OrderBean> doRetrieveByDate(LocalDate from, LocalDate to) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE Data BETWEEN ? AND ?";
        Collection<OrderBean> orders = new LinkedList<>();
        try (Connection connection = ds.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(from));
            preparedStatement.setDate(2, Date.valueOf(to));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                OrderBean ordine = doRetrieveByKey(rs.getInt("ID"));
                orders.add(ordine);
            }
        } catch (SQLException e) {
            System.out.println("Errore durante il recupero per data: " + e.getMessage());
        }
        return orders;
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

    private static final String TABLE_NAME = "Ordini";
    private static final String ITEM_TABLE_NAME = "OggettoOrdine";
}
