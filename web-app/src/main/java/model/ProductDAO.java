package model;

public interface ProductDAO {
	boolean insertProduct(ProductBean product);
	ProductBean getProductByID(int id);
	boolean doDeleteByID(int id);
	boolean doUpdatePrice(int id, int newPrice);
	boolean doUpdateTrend(int id);
	boolean doUpdateQuantity(int id, int update);
	boolean doUpdateNew(int id);
}
