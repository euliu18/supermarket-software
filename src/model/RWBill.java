package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RWBill {
	private File file;
    private int lastIndex;
    private Bill bill;

    public RWBill(Bill bill){
        getFiles();
        this.bill = bill;
        file =new File("./bills/"+(lastIndex+1)+".ser");
        writeBill();
    }

    public RWBill(int index){
        file =new File("./bills/"+(index+1)+".ser");
        bill = readBill();
    }

    private void writeBill() {
        try {
            FileOutputStream fos=new FileOutputStream(file);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(bill);
            oos.close();
            fos.close();
        }catch(FileNotFoundException e) {
            System.err.println("File not found!!!");
        }catch(IOException e) {
            System.err.println("File not writable!!!");
        }

    }

    public Bill readBill() {
        try {
            FileInputStream fis=new FileInputStream(file);
            ObjectInputStream ois= new ObjectInputStream(fis);
            bill=(Bill)ois.readObject();
            ois.close();
        }catch(FileNotFoundException e) {
            System.err.println("File not found!!!");
        }catch(ClassNotFoundException e) {
            System.err.println("Class not found!!!");
        }catch(IOException e) {
            System.err.println("File not acccesable!!!");
        }
        return bill;
    }

    public Bill getBill(){
        return  bill;
    }

    private void getFiles() {
        String dirPath = "./bills";
        File file = new File(dirPath);
        File[] files = file.listFiles();
        if (files != null)
            lastIndex = files.length;
        else
            lastIndex = 0;
    }

    public static int getNumberOfBills(){
        String dirPath = "./bills";
        File file = new File(dirPath);
        File[] files = file.listFiles();
        if (files != null)
            return files.length;
        else
            return 0;
    }

}
