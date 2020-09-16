package com.glusers.api.model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private Date created;
	private Date modified;
	private Date last_login;
	private String password;
	private String token;
	boolean isactive;
	@OneToMany(
			cascade= CascadeType.ALL,
			orphanRemoval = true
			)
	@JoinColumn(name = "user_id")
	private List<Phone> phones = new ArrayList<>();
	
	public User() {
		this.created = getDateandTimeNow();
		this.modified = getDateandTimeNow();
		this.last_login = getDateandTimeNow();
		this.isactive = true;
	}
	public User(String name) {
		this.name = name;
	}
	
	
	
	public User(int id, String name, String email, String password,
			String token, boolean isactive, List<Phone> phones) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.created = getDateandTimeNow();
		this.modified = getDateandTimeNow();
		this.last_login = getDateandTimeNow();
		this.password = password;
		this.token = token;
		this.isactive = isactive;
		this.phones = phones;
	}
	
	
	public Date getDateandTimeNow() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy:HH:mm:SS");
		Date now = new Date();
		try {
            String fecha = sdf.format(now);
			now = sdf.parse(fecha);
			
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		
		return now;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public Date getLast_login() {
		return last_login;
	}
	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public List<Phone> getPhones() {
		return phones;
	}
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	

}
