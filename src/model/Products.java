package model;

import java.io.Serializable;

public class Products implements Serializable {
	
	String name;
	String category;
	String barcode;
	String supplier;
	double cost;
	int onStock;
	int tempQuantity;
	int tempTotal;
	ProductDate dateCreated;
	ProductDate expiry;
	
	
	public Products(String name, String category, String barcode, String supplier, double cost, ProductDate dateCreated, ProductDate expiry, int onStock) {
		super();
		this.name = name;
		this.category = category;
		this.barcode = barcode;
		this.supplier = supplier;
		this.cost = cost;
		this.dateCreated = dateCreated;
		this.expiry = expiry;
		this.onStock = onStock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getStock() {
		return onStock;
	}

	public void setStock(int stock) {
		this.onStock = stock;
	}
	
	public ProductDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(ProductDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public ProductDate getExpiry() {
		return expiry;
	}

	public void setExpiry(ProductDate expiry) {
		this.expiry = expiry;
	}

	public void setTempQuantity(int value) { this.tempQuantity = value; }

	public int getTempQuantity() { return this.tempQuantity; }

	public int getTempTotal() {
		return tempTotal;
	}

	public void setTempTotal(int tempTotal) {
		this.tempTotal = tempTotal;
	}

	public String toString() {
		return "Name: "+name+
				"Barcode: " + barcode +" "+
				"Supplier: " +supplier+" " +
				"Cost: "+cost;
	}

}
