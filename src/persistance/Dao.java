package persistance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import exception.InvalidActionException;
import exception.InvalidActionException.Tipo;
import model.SheetDTO;

/**
 * Class for accessing and modifying data of an excel file.
 * @author alex
 */
public class Dao {

	private static Dao instance;
	private Workbook workbook;
	// Home path
	private String path = "C:\\Users\\alex\\Documents\\Eclipse\\datos\\ExcelTucom\\resources\\ShowExcel.xlsx";
	// Laptop path
	// private String path = "/home/alex/Eclipse/ExcelTucom/resources/ShowExcel.xlsx";

	/**
	 * Creates the instance of this class.
	 * @throws InvalidActionException excel file couldn't be opened.
	 */
	private Dao() throws InvalidActionException {
		try {
			workbook = WorkbookFactory.create(new FileInputStream(new File(path)));
		} catch (EncryptedDocumentException | IOException e) {
			throw new InvalidActionException(Tipo.READ_UNSUCCESSFUL);
		}
	}
	
	/**
	 * Gets unique instance of this class.
	 * @throws InvalidActionException excel file couldn't be opened.
	 */
	public static Dao getInstance() throws InvalidActionException {
		if (instance == null)
			instance = new Dao();
		return instance;
	}

	/**
	 * Gets all sheets in the SheetDTO form. See {@link model.SheetDTO SheetDTO} for details.
	 * @return
	 */
	public SheetDTO[] getDTOSheets() {
		SheetDTO[] names = new SheetDTO[workbook.getNumberOfSheets()];
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			names[i] = new SheetDTO(workbook.getSheetName(i), i);
		}
		return names;
	}

	/**
	 * Gets a sheet given its index.
	 * @param index of the desired sheet.
	 * @return sheet if found.
	 * @throws InvalidActionException sheet index is invalid.
	 */
	public Sheet getSheet(int index) throws InvalidActionException {
		try {
			return workbook.getSheetAt(index);
		} catch (IllegalArgumentException e) {
			throw new InvalidActionException(Tipo.SHEET_NOT_FOUND);
		}
	}

	/**
	 * Updates the value of a specific cell.
	 * @param sheetNum Index of the sheet.
	 * @param rowNum Index of the row.
	 * @param cellNum Index of the cell.
	 * @param value New value.
	 */
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
	
	/**
	 * Writes the changes made in memory to the actual file.
	 * @throws InvalidActionException if the writing was not successful.
	 */
	public void commit() throws InvalidActionException {
		try (FileOutputStream fileOut = new FileOutputStream(new File(path));) {
		    workbook.write(fileOut);
		} catch (IOException e) {
			throw new InvalidActionException(Tipo.WRITE_UNSUCCESSFUL);
		}
	}
	
    /**
     * Given a sheet, this method deletes a column from a sheet and moves
     * all the columns to the right of it to the left one cell.
     * Note: this method will not update any formula references.
     */
    public void deleteColumn(int sheetNum, int columnIndex) {
    	Sheet sheet = workbook.getSheetAt(sheetNum);
        int maxColumn = 0;
        for (int r=0; r < sheet.getLastRowNum()+1; r++) {
            Row row = sheet.getRow(r);
            if (row == null) 
            	continue;
            int lastColumn = row.getLastCellNum();
            if (lastColumn > maxColumn) 
            	maxColumn = lastColumn;
            if (lastColumn < columnIndex) 
            	continue;
            for (int x = columnIndex + 1; x < lastColumn + 1; x++) {
                Cell oldCell = row.getCell(x - 1);
                if (oldCell != null)
                    row.removeCell(oldCell);
                Cell nextCell = row.getCell(x);
                if (nextCell != null) {
                    Cell newCell = row.createCell(x - 1, nextCell.getCellType());
                    cloneCell(newCell, nextCell);
                }
            }
        }
        for (int c = 0; c < maxColumn; c++)
            sheet.setColumnWidth(c, sheet.getColumnWidth(c + 1));
    }
    
    /**
     * Copies the value of a cell into a new cell.
     */
    private static void cloneCell(Cell cNew, Cell cOld){
        switch (cOld.getCellType()){
            case FORMULA:
            case NUMERIC:
                cNew.setCellValue(cOld.getNumericCellValue());
                break;
            case STRING:
                cNew.setCellValue(cOld.getStringCellValue());
                break;
            default:
       }
    }

}
