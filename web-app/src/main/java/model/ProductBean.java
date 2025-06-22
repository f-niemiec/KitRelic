package model;

import java.io.Serializable;

public class ProductBean implements Serializable{
	
	public ProductBean(){};
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getQuantity() {
		return quantity;
	}
	
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getSize() {
		return size;
	}
	
	public void setSize(String size) {
		this.size = size;
	}
	
	public boolean isTrend() {
		return trend;
	}
	
	public void setTrend(boolean trend) {
		this.trend = trend;
	}
	
	public boolean isRecent() {
		return recent;
	}
	
	public void setRecent(boolean recent) {
		this.recent = recent;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "[Nome: " + name + " Prezzo: " + price + " Quantità: " + quantity + " Tipo: " + type +
				" Taglia: " + size + " Di tendanza: " + trend + " Nuovo: " + recent + " Descrizione: " 
				+ description + "]";
	}
	
	private static final long serialVersionUID = 1L;
	private String name;
	private double price;
	private double quantity;
	private String type;
	private String size;
	private boolean trend;
	private boolean recent;
	private String description;
}
