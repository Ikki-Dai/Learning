package com.example.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
//import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_USER")
public class User implements Serializable{
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "ID")
	private String id;

	@Column(name = "USERNAME",unique = true,nullable= false)
	private String username;

	@Column(name = "PASSCODE", nullable = false)
	private String passcode;

	@Column(name = "MAIL")
	private String mail;
	
	@Column(name = "BIRTH")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar birthDay;
	
	//@Enumerated
	@Column(name = "GENDER")
	private boolean gender;
	
	@OneToMany(mappedBy = "user") //com.example.entity.Role.user 指定多的一方维护的依赖属性
	private Set<Role> roles;
	
	
	public User() {
		super();
	}

	public User(String username, String passcode) {
		super();
		this.username = username;
		this.passcode = passcode;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPasscode() {
		return passcode;
	}
	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}


	public Calendar getBirthDay() {
		return birthDay;
	}


	public void setBirthDay(Calendar birthDay) {
		this.birthDay = birthDay;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User : {id : " + id + ", username : " + username + ", passcode : " + passcode + ", mail : " + mail
				+ ", birthDay : " + birthDay + ", gender : " + gender + "}";
	}
	
	
	
}
