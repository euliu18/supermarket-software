package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RWProductsSold {
	
	ArrayList<ProductsSold> productsSolds;
	File file;
	public RWProductsSold() {
		productsSolds =new ArrayList<ProductsSold>();
		file=new File("productsSold.ser");
		if(!file.exists()) {
			writeProductsSold();
		}else {
			productsSolds = readProductsSold();
		}
		
	}
	
	public void writeProductsSold() {
		try {
			FileOutputStream fos=new FileOutputStream(file);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(productsSolds);
			oos.close();fos.close();
		}catch(FileNotFoundException e) {
			System.out.println("File is not accesible...");
		}catch(IOException e) {
			System.out.println("File cannot be written");
		}
		readProductsSold();
	}

    public void writeProductsSold(ArrayList<ProductsSold> productsSolds) {
        try {
            FileOutputStream fos=new FileOutputStream(file);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(productsSolds);
            oos.close();fos.close();
        }catch(FileNotFoundException e) {
            System.out.println("File is not accesible...");
        }catch(IOException e) {
            System.out.println("File cannot be written");
        }
        readProductsSold();
    }
	
	public ArrayList<ProductsSold> readProductsSold() {
		try {
			FileInputStream fis=new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			productsSolds =(ArrayList<ProductsSold>)ois.readObject();
			ois.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("File not found");
		}catch(IOException e) {
			System.err.println("File corrupted");
		}catch(ClassNotFoundException e) {
			System.err.println("Class not found...");
		}
		return productsSolds;
	}

	public void addProductSold(ProductsSold x) {
		productsSolds.add(x);
		writeProductsSold();
	}

	public void addProductSoldInThread(ProductsSold x) {
		ArrayList<ProductsSold> tempProductsSold = productsSolds;
		tempProductsSold.add(x);
		writeProductsSold(tempProductsSold);
	}

	public void addQuantity(Products product, int quantity) {
		ArrayList<ProductsSold> tempProductsSold = productsSolds;
		int i=0;
		for (ProductsSold tempProduct: productsSolds){
			if(product.getBarcode().equals(tempProduct.getBarcode())){
				tempProductsSold.get(i).setSold(productsSolds.get(i).getSold()+quantity);
			}
			i++;
		}
		writeProductsSold(tempProductsSold);
	}
	
	public ArrayList<ProductsSold> getSoldList(){
		return productsSolds;
	}

	
}
