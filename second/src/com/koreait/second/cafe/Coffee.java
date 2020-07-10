package com.koreait.second.cafe;

public class Coffee {
	private String name;
	private int price;
	
	public Coffee(MenuItem mi) {
		name = mi.getName();
		price = mi.getPrice();
	}
	
	
	public String getName() {
		return name;
	}


	public int getPrice() {
		return price;
	}


	@Override
	public String toString() {		
		return String.format("%s\t\t%,4d원", name, price);
	}
}
