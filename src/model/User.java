package model;

import java.io.Serializable;

public abstract class User implements Serializable{
	private String username;
	private String password;
	private String name;
	private String surname;
	private int ID;
	private double salary;
	private Type type;
	
	public User(String username, String password, String name, String surname, int iD, double salary, Type type) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		ID = iD;
		this.salary = salary;
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getID() {
		return ID;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
