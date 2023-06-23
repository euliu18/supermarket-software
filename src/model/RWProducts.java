package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RWProducts {
	private File file;
	private ArrayList<Products> p;
	
	public RWProducts() {
		file =new File("product.ser");
		p=new ArrayList<Products>();
		if(!file.exists()) {
			writeProducts();
		}else {
			p=readProducts();
		}
	}

	private void writeProducts() {
		try {
			FileOutputStream fos=new FileOutputStream(file);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(p);
			oos.close();
			fos.close();
		}catch(FileNotFoundException e) {
			System.err.println("File not found!!!");
		}catch(IOException e) {
			System.err.println("File not writable!!!");
		}
		readProducts();
		
	}

	public ArrayList<Products> readProducts() {
		try {
			FileInputStream fis=new FileInputStream(file);
			ObjectInputStream ois= new ObjectInputStream(fis);
			p=(ArrayList<Products>)ois.readObject();
			ois.close();
		}catch(FileNotFoundException e) {
			System.err.println("File not found!!!");
		}catch(ClassNotFoundException e) {
			System.err.println("Class not found!!!");
		}catch(IOException e) {
			System.err.println("File not acccesable!!!");
		}
		return p;
	}
	
	public boolean existfile() {
		if(file.exists()) {
			return true;
		}
		else {
			return false;
		}
	}
	

	public ArrayList<Products> getProducts(){
		return p;
	}
	
	public void addProduct(Products pr) {
		p.add(pr);
		writeProducts();
	}
	public void deleteProduct(Products pr) {
		p.remove(pr);
		writeProducts();
	}

	public void addQuantity(int value, String barcode){
		ArrayList<Products> tempP = new ArrayList<>();
		for(Products product: p){
			if(product.getBarcode().equals(barcode)){
				product.setStock(product.getStock()+value);
			}
			tempP.add(product);
		}
		p.clear();
		p.addAll(tempP);
		writeProducts();
	}

	public void removeQuantity(int value, String barcode){
		ArrayList<Products> tempP = new ArrayList<>();
		for(Products product: p){
			if(product.getBarcode().equals(barcode)){
				product.setStock(product.getStock()-value);
			}
			tempP.add(product);
		}
		p.clear();
		p.addAll(tempP);
		writeProducts();
	}

	public Products getProduct(String barcode) {
		for(Products x  :p) {
			if(x.getBarcode()==barcode) {
				return x;
			}
		}			
		return null;

	}

}
