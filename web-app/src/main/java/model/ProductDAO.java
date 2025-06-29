package model;

import java.util.Collection;

public interface ProductDAO {
	int doSave(ProductBean product);
	ProductBean doRetrieveByKey(int id);
	Collection<ProductBean> doRetrieveAll(String order);
	Collection<ProductBean> doRetrieveNew();
	Collection<ProductBean> doRetrieveTrend();
	boolean doDelete(int id);
	boolean doUpdateName(int id, String name);
	boolean doUpdateDescription(int id, String desc);
	boolean doUpdatePrice(int id, double newPrice);
	boolean doUpdateTrend(int id);
	boolean doUpdateQuantity(int id, int update);
	boolean doUpdateNew(int id);
	public Collection<ProductBean> doRetrieveBySearch(String search);
}
