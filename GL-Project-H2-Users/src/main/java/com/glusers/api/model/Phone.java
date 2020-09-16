package com.glusers.api.model;

import javax.persistence.*;

@Entity
@Table(name="phone")
public class Phone {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idphone;
	private String number;
	private int citycode;
	private int countrycode;

	
	public int getIdPhone() {
		return idphone;
	}

	public void setId(int id_phone) {
		this.idphone = id_phone;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getCitycode() {
		return citycode;
	}

	public void setCitycode(int citycode) {
		this.citycode = citycode;
	}

	public int getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(int countrycode) {
		this.countrycode = countrycode;
	}

	/*public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/

	public Phone() {
		
	}

	/*public Phone(String number, User user) {
		this.number = number;
		this.user = user;
	}*/
	//public Phone(int id, String number, int citycode, int countrycode, User user) {
	public Phone(int id, String number, int citycode, int countrycode) {
		super();
		this.idphone = id;
		this.number = number;
		this.citycode = citycode;
		this.countrycode = countrycode;
		//this.user = user;
	}
	//public Phone(String number, int citycode, int countrycode, User user) {
	public Phone(String number, int citycode, int countrycode) {
		super();
		
		this.number = number;
		this.citycode = citycode;
		this.countrycode = countrycode;
		//this.user = user;
	}
	
	

}
