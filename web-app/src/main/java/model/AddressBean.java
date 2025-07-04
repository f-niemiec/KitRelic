package model;

import java.io.Serializable;

public class AddressBean implements Serializable{
	public AddressBean() {}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public TipoIndirizzo getAddress() {
		return address;
	}
	
	public void setAddress(TipoIndirizzo address) {
		this.address = address;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getProvince() {
		return province;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
		
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
	    return city + ", " + province + ", " + street + ", " + country;
	}
	
	private static final long serialVersionUID = 1L;
	private int id;
	private TipoIndirizzo address;
	private boolean active;
	private String city;
	private String street;
	private String province;
	private String country;
	private int userId;
}
