package model;

import java.io.Serializable;

public class CartItemBean implements Serializable{
	public CartItemBean() {}
	
	public CartItemBean(ProductBean product) {
		this.product = product;
		quantity = 1;
	}
	
	public ProductBean getProduct() {
		return product;
	}

	public void setProduct(ProductBean product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		if(quantity > 1 && product.getQuantity() >= quantity) {
			this.quantity = quantity;
		}
	}
	
	public void increment() {
		if(quantity < product.getQuantity()) {
			quantity++;
		}
	}
	
	public void decrement() {
		if(quantity >= 1) {
			quantity--;
		}
	}
	
	public double getPrice() {
		return quantity * product.getPrice();
	}
	
	public int getId() {
		return product.getId();
	}
	
	public String getName() {
		return product.getName();
	}
	
	public String getSize() {
		return product.getSize();
	}
	
	
	@Override
	public String toString() {
		return "[CartItemBean: " + product.getName() +" , Quantità: "+ quantity + ", Prezzo totale: " + 
				getPrice() + " euro]";
	}

	private static final long serialVersionUID = 1L;
	private ProductBean product;
	private int quantity;
}
