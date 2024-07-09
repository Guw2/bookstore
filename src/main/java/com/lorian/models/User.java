package com.lorian.models;

import java.util.Objects;

import com.lorian.models.enums.Role;

public class User {
	
	private Long id;
	private String name;
	private String email;
	private String register;
	private String address;
	private Float balance;
	private String password;
	private Role role;

	
	public User() {
		
	}
	
	public User(Long id, String name, String email, String register, String address, Float balance, String password,
			Role role) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.register = register;
		this.address = address;
		this.balance = balance;
		this.password = password;
		this.role = role;
	}
	

	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getEmail() {
		return email;
	}


	public String getRegister() {
		return register;
	}


	public String getAddress() {
		return address;
	}


	public Float getBalance() {
		return balance;
	}


	public String getPassword() {
		return password;
	}


	public Role getRole() {
		return role;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", register=" + register + ", address=" + address
				+ ", balance=" + balance + ", password=" + password + ", role=" + role + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, balance, register, email, id, name, password, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(address, other.address) && Objects.equals(balance, other.balance)
				&& Objects.equals(register, other.register) && Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password) && role == other.role;
	}
	
	
}
