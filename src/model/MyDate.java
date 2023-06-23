package model;

import java.io.Serializable;

public class MyDate implements Serializable{

	private int d;
	private int m;
	private int y;
	
	public MyDate(int d, int m, int y) {
		this.d = d;
		this.m = m;
		this.y = y;
	}
	
	public MyDate(String date) {
		String[] arr=date.split("[/-]");
		this.d=Integer.parseInt(arr[0]);
		this.m=Integer.parseInt(arr[1]);
		this.y=Integer.parseInt(arr[2]);
	}
	
	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String toString() {
		return d+"/"+m+"/"+y;
 }
}
