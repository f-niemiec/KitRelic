package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

public class OrderBean implements Serializable{
	public OrderBean() {}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public PaymentCardBean getCard() {
		return card;
	}
	
	public void setCard(PaymentCardBean card) {
		this.card = card;
	}
	
	public AddressBean getShipping() {
		return shipping;
	}
	
	public void setShipping(AddressBean shipping) {
		this.shipping = shipping;
	}
	
	public AddressBean getBilling() {
		return billing;
	}
	
	public void setBilling(AddressBean billing) {
		this.billing = billing;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserSurname() {
		return userSurname;
	}
	
	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Collection<OrderItemBean> getItems() {
		return items;
	}

	public void setItems(Collection<OrderItemBean> items) {
		this.items = items;
	}

	private static final long serialVersionUID = 1L;
    private int id;
	private LocalDate date;
	private double total;
	private PaymentCardBean card;
	private AddressBean shipping;
	private AddressBean billing;
	private int userId;
	private String userName;
	private String userSurname;
	private String userEmail;
	private Collection<OrderItemBean> items;
}
