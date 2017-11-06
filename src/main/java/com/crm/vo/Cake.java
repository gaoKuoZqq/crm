package com.crm.vo;

public class Cake {
	String name;
	Object value;
	public Cake(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}
	public Cake() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Cake [name=" + name + ", value=" + value + "]";
	}
	
}
