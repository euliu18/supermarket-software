package model;

public class Economist extends User implements Checker{
	
	MyDate birthday;
	String phone;
	
	public Economist(String username, String password, String name, String surname, int iD, double salary,
			MyDate birthday, String phone) {
		super(username, password, name, surname, iD, salary, Type.Economist);
		this.birthday = birthday;
		this.phone = phone;
		//TODO: Delete this
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Name: "+getName()+" Surname: "+getSurname()+" Type: "+getType()+" Birthday: "+birthday+" Phone: "+phone;
	}
	

}
