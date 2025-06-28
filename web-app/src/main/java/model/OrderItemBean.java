package model;

import java.io.Serializable;

public class OrderItemBean implements Serializable{
	public OrderItemBean() {}
	
	public int getItemId() {
		return itemId;
	}
	
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	private static final long serialVersionUID = 1L;
	private int itemId;
	private int orderId;
	private int productId;
	private int quantity;
	private String productName;
	private String productSize;
	private double productPrice;
}
