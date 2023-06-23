package model;

public class ProductDate extends MyDate {
	
	public ProductDate(int d, int m, int y) {
		super(d, m, y);
	}

	public ProductDate(String date) {
		super(date);
	}

	public void setdateFromDp(String a) {
		String[] st=a.split("/");
		setD(Integer.parseInt(st[2]));
		setM(Integer.parseInt(st[1]));
		setY(Integer.parseInt(st[0]));
	}

	@Override
	public String toString() {
		return getD() + "/" + getM() + "/" + getY();
	}
}
