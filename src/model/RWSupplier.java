package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RWSupplier {

	private final File file;
	private ArrayList<Supplier> supplier;
	
	public RWSupplier() {
		file=new File("supplier.ser");
		supplier=new ArrayList<Supplier>();
		if(!file.exists()) {
			writeSupplier();
		}else {
			supplier=readSupplier();
		}
	}

	public ArrayList<Supplier> readSupplier() {
		try {
			FileInputStream fis=new FileInputStream(file);
			ObjectInputStream ois=new ObjectInputStream(fis);
			supplier=(ArrayList<Supplier>) ois.readObject();
			ois.close();
		}catch(FileNotFoundException e) {
			System.err.println("File not found!");
		}catch(ClassNotFoundException e) {
			System.err.println("Class not found!");
		}catch(IOException e) {
			System.err.println("File not Accessable");
		}
		return supplier;
	}

	public void writeSupplier() {
		try {
			FileOutputStream fos=new FileOutputStream(file);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(supplier);
			oos.close();
			fos.close();
		}catch(FileNotFoundException e) {
			System.err.println("File Not Found!");
		}catch(IOException e) {
			System.err.println("File not writable!");
		}
		
		readSupplier();
		
	}
	
	public void addSupplier(Supplier sup) {
		supplier.add(sup);
		writeSupplier();
	}
	
	public void deleteSupplier(Supplier sup) {
		supplier.remove(sup);
		writeSupplier();
	}

}
