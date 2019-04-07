package persistance;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import exception.InvalidActionException;
import exception.InvalidActionException.Tipo;
import model.SheetDTO;

public class Dao {
	
	private Workbook workbook;
	// Home
	private String path = "C:\\Users\\alex\\Documents\\Eclipse\\datos\\ExcelTucom\\resources\\ShowExcel.xlsx";
	// Laptop
	//private String path = "/home/alex/Eclipse/ExcelTucom/resources/ShowExcel.xlsx";

	public Dao() {
			try {
				workbook = WorkbookFactory.create(new File(path));
			} catch (EncryptedDocumentException |IOException e) {
				e.printStackTrace();
			}
	}
	
	public SheetDTO[] getDTOSheets() {
		SheetDTO[] names = new SheetDTO[workbook.getNumberOfSheets()];
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			names[i] = new SheetDTO(workbook.getSheetName(i), i);
		}
		return names;
	}
	
	public Sheet getSheet(int index) throws InvalidActionException {
		try {
			return workbook.getSheetAt(index);
		} catch(IllegalArgumentException e) {
			throw new InvalidActionException(Tipo.SHEET_NOT_FOUND);
		}
	}
	

	
}
