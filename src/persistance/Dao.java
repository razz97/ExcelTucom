package persistance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
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
	// private String path = "/home/alex/Eclipse/ExcelTucom/resources/ShowExcel.xlsx";

	public Dao() throws InvalidActionException {
		try {
			workbook = WorkbookFactory.create(new FileInputStream(new File(path)));
		} catch (EncryptedDocumentException | IOException e) {
			throw new InvalidActionException(Tipo.READ_UNSUCCESSFUL);
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
		} catch (IllegalArgumentException e) {
			throw new InvalidActionException(Tipo.SHEET_NOT_FOUND);
		}
	}

	public void updateCell(int sheetNum, int rowNum, int cellNum, String value) {
		Cell cell = workbook.getSheetAt(sheetNum).getRow(rowNum).getCell(cellNum);
			switch (cell.getCellType()) {
			case NUMERIC:
			case FORMULA:
				cell.setCellValue(new Double(value));
				break;
			case STRING:
				cell.setCellValue(value);
				break;
			default:
		}
	}
	
	public void commit() throws InvalidActionException {
		try (FileOutputStream fileOut = new FileOutputStream(new File(path));) {
		    workbook.write(fileOut);
		} catch (IOException e) {
			throw new InvalidActionException(Tipo.WRITE_UNSUCCESSFUL);
		}
	}

}
