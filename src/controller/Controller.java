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
		if (instance == null) instance = new Controller();
		return instance;
	}
	
	public Controller() {
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
		Weight[] weights = new Weight[values.getLastCellNum()-1];
		for (int i = 2; i < values.getLastCellNum()-1; i++) {
			Cell c = values.getCell(i); 
			if (c != null && c.getCellType() == CellType.NUMERIC) 
				weights[i-2] = new Weight(names.getCell(i).getStringCellValue(), c.getNumericCellValue());
		}
		return weights;
	}
	
	
	
}
