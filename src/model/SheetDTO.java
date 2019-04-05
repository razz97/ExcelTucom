package model;

public class SheetDTO {

	private String title;
	private int index;
	
	public SheetDTO(String title, int index) {
		this.title = title;
		this.index = index;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getIndex() {
		return index;
	}
	
	
}
