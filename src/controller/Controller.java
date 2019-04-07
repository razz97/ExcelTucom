package controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import exception.InvalidActionException;
import model.SheetDTO;
import model.Weight;
import persistance.Dao;

public class Controller {

	private Dao dao;
	private static Controller instance;
	
	public static Controller getInstance() {
		if (instance == null) 
			instance = new Controller();
		return instance;
	}
	
	private Controller() {
		dao = new Dao();
	}
	
	public SheetDTO[] getDTOSheets() {
		return dao.getDTOSheets();
	}

	public Sheet getSheet(int sheetNumber) throws InvalidActionException {
		return dao.getSheet(sheetNumber);
	}
	
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

	public void updateCell(int sheetNum, int rowNum, int cellNum, String value) {
		dao.updateCell(sheetNum, rowNum, cellNum, value);
	}

	public void commit() throws InvalidActionException {
		dao.commit();
	}
	
	
	
}
