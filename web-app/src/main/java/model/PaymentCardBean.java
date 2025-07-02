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
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getCvv() {
		return cvv;
	}
	
	public void setCvv(String cvv) {
		this.cvv = cvv;
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
	
	private static final long serialVersionUID = 1L;
    private int id;
    private LocalDate expires;
    private String cardNumber;
    private String cvv;
    private String owner;
    private int userId;
    
}
