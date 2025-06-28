package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	public Cart() {
		products = new ArrayList<CartItemBean>();
	}
	
	public void addProduct(ProductBean bean) {
		for(CartItemBean item : products) {
			if(item.getId() == bean.getId()) {
				item.increment();
				return;
			}
		}
		CartItemBean prod = new CartItemBean(bean);
		products.add(prod);
	}
	
	public void deleteProduct(ProductBean product) {
		for(CartItemBean item : products) {
			if(item.getId() == product.getId()) {
				products.remove(item);
			}
		}
	}
	
	public List<CartItemBean> getCart(){
		return products;
	}
	
	public CartItemBean getItem(int id) {
		for(CartItemBean item : products) {
			if(item.getId() == id) {
				return item;
			}
		}
		return null;
	}
	
	public double getTotalPrice() {
		double price = 0;
		for(CartItemBean item : products) {
			price += item.getPrice();
		}
		return price;
	}
	
	public int getSize() {
		int size = 0;
		for(CartItemBean item : products) {
			size += item.getQuantity();
		}
		return size;
	}
	
	public boolean isEmpty() {
		return products.isEmpty();
	}
	
	public void clear() {
		products.removeAll(products);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
	    sb.append("Cart [\n");

	    if (products.isEmpty()) {
	        sb.append("  (empty)\n");
	    } else {
	        for (CartItemBean item : products) {
	            sb.append("  ").append(item.toString()).append("\n");
	        }
	    }

	    sb.append("Oggetti totali: ").append(getSize()).append("\n");
	    sb.append("Prezzo finale in euro: ").append(String.format("%.2f", getTotalPrice())).append("\n");
	    sb.append("]");
	    return sb.toString();
	}
	
	
	private List<CartItemBean> products;
}
