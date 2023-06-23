package model;

import java.io.Serializable;

public class ProductsSold implements Serializable {
	
	private String barcode;
    private String name;
    private String supplier;
    private double price;
    private int sold;

    public ProductsSold(Products product, int sold){
        this.barcode = product.getBarcode();
        this.name = product.getName();
        this.supplier = product.getSupplier();
        this.price = product.getCost();
        this.sold = sold;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public String getSupplier() {
        return supplier;
    }

    public double getPrice() {
        return price;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

}
