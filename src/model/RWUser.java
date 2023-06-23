package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RWUser {
	private ArrayList<User> users;
	private File file;
	public RWUser() {
		users=new ArrayList<User>();
		file=new File("users.ser");
		if(!file.exists()) {
			writeUsers();
		}else {
			users=readUsers();
		}
		
	}
	
	public void writeUsers() {
		try {
			FileOutputStream fos=new FileOutputStream(file);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			if(users.isEmpty()){
				users.add(new Administrator("admin","admin","User","ADMIN",1,1000.0,new MyDate("1/1/2000")));
			}
			oos.writeObject(users);
			oos.close();fos.close();
		}catch(FileNotFoundException e) {
			System.out.println("File is not accesible...");
		}catch(IOException e) {
			System.out.println("File cannot be written");
		}
		readUsers();
	}
	
	public ArrayList<User> readUsers() {
		try {
			FileInputStream fis=new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			users=(ArrayList<User>)ois.readObject();
			ois.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("File not found");
		}catch(IOException e) {
			System.err.println("File corrupted");
		}catch(ClassNotFoundException e) {
			System.err.println("Class not found...");
		}
		return users;
	}
	
	public User checkLogIn(String username,String password) {
		for(User i:users)
			if(((Checker)i).checkUser(username, password))
				return i;
		return null;
	}
	public void addUser(User x) {
		users.add(x);
		writeUsers();
	}
	
	public void deleteUser(User x) {
		users.remove(x);
		writeUsers();
	}
	
	public int getPosition(User ed) {
		
		for(int i=0; i<users.size(); i++)
		{
			if(users.get(i).getID()==ed.getID())
				return i;
		}
		
		return -1;
	}
	
	public User getUser(int id) {
		for(User x:users) {
			if(x.getID()==id)
				return x;
			
				
		}return null;
	}
	
	public void edit(User x,String pass) {
		int i =getPosition(x);
		users.get(i).setPassword(pass);
		writeUsers();
	}

	public void edit(int i, String name, String surname, MyDate birthday, String phone, double salary) {
		users.get(i).setName(name);
		users.get(i).setSurname(surname);
		if(birthday!=null)
			((Economist)users.get(i)).setBirthday(birthday);
		if(phone!=null)
			((Economist)users.get(i)).setPhone(phone);
		if(salary!=0)
			((Economist)users.get(i)).setSalary(salary);
		writeUsers();
		
	}

	public void edit(int i, String name, String surname, MyDate birthday, double salary) {
		users.get(i).setName(name);
		users.get(i).setSurname(surname);
		((Cashier)users.get(i)).setBirthday(birthday);
		((Cashier)users.get(i)).setSalary(salary);
		writeUsers();
	}

	public void edit(int i, String name, String surname) {
		users.get(i).setName(name);
		users.get(i).setSurname(surname);
		writeUsers();
	}
}
