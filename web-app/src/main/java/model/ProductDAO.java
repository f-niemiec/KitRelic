package model;

import java.util.Collection;

public interface ProductDAO {
	boolean doSave(ProductBean product);
	ProductBean doRetrieveByKey(int id);
	Collection<ProductBean> doRetrieveAll();
	boolean doDeleteByID(int id);
	boolean doUpdatePrice(int id, int newPrice);
	boolean doUpdateTrend(int id);
	boolean doUpdateQuantity(int id, int update);
	boolean doUpdateNew(int id);
}
