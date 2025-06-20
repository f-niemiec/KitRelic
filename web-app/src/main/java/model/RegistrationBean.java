package model;

import java.sql.Date;

public class RegistrationBean {
	public RegistrationBean() {}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public TipoUtente getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoUtente tipo) {
		this.tipo = tipo;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private TipoUtente tipo;
	private Date data;
}
