package model;

public class Weight {
	
	private double value;
	private String name;
	private String shorthand;
	private int columnIndex;
	
	public Weight(String name, String shorthand, double value, int columnIndex) {
		this.value = value;
		this.name = name;
		this.shorthand = shorthand;
		this.columnIndex = columnIndex;
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
	
	public int getColumnIndex() {
		return columnIndex;
	}

}
