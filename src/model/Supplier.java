package model;

import java.io.Serializable;

public class Supplier implements Serializable{
	
	String name;

	public Supplier(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
