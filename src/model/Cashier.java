package model;

public class Cashier extends User implements Checker {

	MyDate birthday;
	int nrOfBills=0;
	
	public Cashier(String username, String password, String name, String surname, int iD, double salary,
			MyDate birthday) {
		super(username, password, name, surname, iD, salary, Type.Cashier);
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

	public int getNrOfBills() {
		return nrOfBills;
	}

	public void setNrOfBills() {
		nrOfBills++;
	}

}
