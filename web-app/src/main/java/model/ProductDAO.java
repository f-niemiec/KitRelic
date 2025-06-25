package model;

import java.util.Collection;

public interface ProductDAO {
	int doSave(ProductBean product);
	ProductBean doRetrieveByKey(int id);
	Collection<ProductBean> doRetrieveAll(String order);
	boolean doDelete(int id);
	boolean doUpdatePrice(int id, double newPrice);
	boolean doUpdateTrend(int id);
	boolean doUpdateQuantity(int id, int update);
	boolean doUpdateNew(int id);
}
