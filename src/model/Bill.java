package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Bill implements Serializable {
	int Id;
	int UserId;
	ArrayList<Products> products;
	ArrayList<Integer> amounts;
	int total;
	ProductDate date;
	
	public Bill(int id, int userId, ProductDate date) {
		super();
		Id = id;
		products = new ArrayList<>();
		amounts = new ArrayList<>();
		UserId = userId;
		this.date = date;
	}

	public void addProduct(Products product, int amount){
		products.add(product);
		amounts.add(amount);
	}

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public ProductDate getDate() {
		return date;
	}
	public void setDate(ProductDate date) {
		this.date = date;
	}

	public ArrayList<Products> getProducts() {
		return products;
	}

	public String toString() {
		return "Id: "+ getId() + "\r\n"+
				"UserId: "+getUserId()+"\r\n"+
				"ProductDate" + getDate().toString() + "\r\n"+
				"Products" +products+"\r\n"+
				"Amounts: "+amounts+"\r\n"+
				"Total: "+total;
	}

}
