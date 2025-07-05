package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

public interface OrderDAO {
	OrderBean doRetrieveByKey(int id);
	Collection<OrderBean> doRetrieveByUserID(int userId);
	Collection<OrderBean> doRetrieveAll(String order);
	Collection<OrderBean> doRetrieveByDate(LocalDate from, LocalDate to);
	Collection<OrderBean> doRetrieveByDateAndUser(LocalDate from, LocalDate to, int userId);
}
