package model;

public class Weight {
	
	private double value;
	private String name;
	private String shorthand;
	
	public Weight(String name, String shorthand, double value) {
		this.value = value;
		this.name = name;
		this.shorthand = shorthand;
	}
	
	public String getName() {
		return name;
	}
	
	public double getValue() {
		return value;
	}
	
	public String getShorthand() {
		return shorthand;
	}	

}
