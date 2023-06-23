package model;


public class Administrator extends User implements Checker{
    
	MyDate birthday;
	
	
	public Administrator(String username, String password, String name, String surname, int iD, double salary,
			MyDate birthday) {
		super(username, password, name, surname, iD, salary, Type.Administrator);
		this.birthday = birthday;
	}

	
	@Override
	public boolean checkUser(String username, String password) {
		return username.equals(this.getUsername())&& password.equals(this.getPassword());
	}


	public MyDate getBirthday() {
		return birthday;
	}


	public void setBirthday(MyDate birthday) {
		this.birthday = birthday;
	}


	@Override
	public String toString() {
		return "Name: "+getName()+" Surname: "+getSurname()+" Type: "+getType()+" Birthday: "+birthday;
	}
	

}
