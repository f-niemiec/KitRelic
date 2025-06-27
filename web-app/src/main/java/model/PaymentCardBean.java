package model;

import java.io.Serializable;
import java.time.LocalDate;

public class PaymentCardBean implements Serializable{
	public PaymentCardBean() {}
    
    public int getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getExpires() {
		return expires;
	}
	
	public void setExpires(LocalDate expires) {
		this.expires = expires;
	}
	
	public int getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public int getCvv() {
		return cvv;
	}
	
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	private static final long serialVersionUID = 1L;
    private int id;
    private LocalDate expires;
    private int cardNumber;
    private int cvv;
    private boolean active;
    private String owner;
    private int userId;
    
}
