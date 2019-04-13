package model;
/**
 * An instance of this class represents a sheet of an excel file,
 * it serves as a container to store its index and title.
 * @author alex
 *
 */
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
