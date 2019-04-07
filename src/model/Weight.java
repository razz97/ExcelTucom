package model;

public class Weight {
	
	private double value;
	private String name;
	
	public Weight(String name, double value) {
		this.value = value;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Weight [value=" + value + ", name=" + name + "]";
	}
	
	

}
