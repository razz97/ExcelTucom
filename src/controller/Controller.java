package controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import exception.InvalidActionException;
import model.SheetDTO;
import model.Weight;
import persistance.Dao;

/**
 * Responsible for the logic of the application.
 * @author alex
 */
public class Controller {

	private Dao dao;
	private static Controller instance;
	
	/**
	 * Gets the unique instance of this class.
	 * @throws InvalidActionException there was an error while creating.
	 */
	public static Controller getInstance() throws InvalidActionException {
		if (instance == null) 
			instance = new Controller();
		return instance;
	}
	
	/**
	 * Creates an instance of this class.
	 * @throws InvalidActionException there was an error getting dao instance.
	 */
	private Controller() throws InvalidActionException {
		dao = Dao.getInstance();
	}
	
	/**
	 * Gets all the sheets in the excel file as SheetDTO, see {@link model.SheetDTO SheetDTO} for details.
	 * @return array of SheetDTO
	 */
	public SheetDTO[] getDTOSheets() {
		return dao.getDTOSheets();
	}

	/**
	 * Gets a specific sheet for the excel file.
	 * @param sheetNumber - the index of the sheet.
	 * @return the desired sheet.
	 * @throws InvalidActionException sheet wasn't found.
	 */
	public Sheet getSheet(int sheetNumber) throws InvalidActionException {
		return dao.getSheet(sheetNumber);
	}
	
	/**
	 * Gets all the weights of a specified sheet. See {@link model.Weight Weight} for more details.
	 * @param sheet
	 * @return
	 */
	public Weight[] getWeights(Sheet sheet) {
		Row names = sheet.getRow(0);
		Row values = sheet.getRow(1);
		Row shorthands = sheet.getRow(2);
		Weight[] weights = new Weight[values.getLastCellNum()];
		for (int i = 2; i < values.getLastCellNum(); i++) {
			Cell c = values.getCell(i); 
			if (c != null && c.getCellType() == CellType.NUMERIC) {
				String name = names.getCell(i).getStringCellValue();
				String shorthand = shorthands.getCell(i).getStringCellValue();
				double value = c.getNumericCellValue();
				weights[i-2] = new Weight(name, shorthand, value, i);
			}
		}
		return weights;
	}

	/**
	 * Updates a cell.
	 * @param sheetNum - sheet index.
	 * @param rowNum - row index.
	 * @param cellNum  -column index.
	 * @param value - new value.
	 */
	public void updateCell(int sheetNum, int rowNum, int cellNum, String value) {
		dao.updateCell(sheetNum, rowNum, cellNum, value);
	}

	/**
	 * Commits all changes in memory to the actual file
	 * @throws InvalidActionException error writing to the file.
	 */
	public void commit() throws InvalidActionException {
		dao.commit();
	}

	/**
	 * Deletes the specified column in the specified sheet.
	 * @param sheetNum - sheet index.
	 * @param index - column index.
	 */
	public void deleteColumn(int sheetNum, int index) {
		dao.deleteColumn(sheetNum, index);
	}

	/**
	 * Adds a new column into the specified sheet.
	 * @param sheetNum - sheet index.
	 * @param name - name of column
	 * @param shorthand - shorthand of column
	 * @param value - weight for nota final.
	 */
	public void addColumn(int sheetNum, String name, String shorthand, double value) {
		dao.addColumn(sheetNum,name,shorthand,value);
	}
		
}
