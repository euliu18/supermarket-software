package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RWIncomeOutcome {
	 private File file;
	    private IncomeOutcome incomeOutcome;

	    public RWIncomeOutcome(){
	        file =new File("incomeOutcome.ser");
	        incomeOutcome=read();
	    }

	    public RWIncomeOutcome(IncomeOutcome incomeOutcome){
	        file =new File("incomeOutcome.ser");
	        this.incomeOutcome = incomeOutcome;
	        write();
	    }

	    private void write() {
	        try {
	            FileOutputStream fos=new FileOutputStream(file);
	            ObjectOutputStream oos=new ObjectOutputStream(fos);
	            oos.writeObject(incomeOutcome);
	            oos.close();
	            fos.close();
	        }catch(FileNotFoundException e) {
	            System.err.println("File not found!!!");
	        }catch(IOException e) {
	            System.err.println("File not writable!!!");
	        }
	        read();

	    }

	    public IncomeOutcome read() {
	        try {
	            FileInputStream fis=new FileInputStream(file);
	            ObjectInputStream ois= new ObjectInputStream(fis);
	            incomeOutcome=(IncomeOutcome)ois.readObject();
	            ois.close();
	        }catch(FileNotFoundException e) {
	            System.err.println("File not found!!!");
	        }catch(ClassNotFoundException e) {
	            System.err.println("Class not found!!!");
	        }catch(IOException e) {
	            System.err.println("File not acccesable!!!");
	        }
	        return incomeOutcome;
	    }

	    public void refresh(IncomeOutcome incomeOutcome){
	        this.incomeOutcome = incomeOutcome;
	        write();
	    }

}
