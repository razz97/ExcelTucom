package model;

public class Weight {
	
	private double value;
	private String name;
	private String shorthand;
	private int cellIndex;
	
	public Weight(String name, String shorthand, double value, int cellIndex) {
		this.value = value;
		this.name = name;
		this.shorthand = shorthand;
		this.cellIndex = cellIndex;
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
	
	public int getCellIndex() {
		return cellIndex;
	}

}
