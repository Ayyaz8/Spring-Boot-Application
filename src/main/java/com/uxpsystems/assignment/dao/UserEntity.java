package com.uxpsystems.assignment.dao;

import javax.persistence.*;

@Entity
@Table(name = "USER_TABLE")
public class UserEntity {

	public UserEntity() {
	}

	@Id
	// @GeneratedValue
	@Column(name = "user_id")
	private Integer user_id;

	@Column(name = "user_name")
	private String user_name;

	@Column(name = "password")
	private String password;

	@Column(name = "status")
	private String status;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserEntity(Integer user_id, String user_name, String password, String status) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserEntity [user_id=" + user_id + ", user_name=" + user_name + ", password=" + password + ", status="
				+ status + "]";
	}

}
