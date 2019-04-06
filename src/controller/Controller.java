package controller;

import org.apache.poi.ss.usermodel.Sheet;

import model.SheetDTO;
import persistance.Dao;

public class Controller {

	private Dao dao;
	
	public Controller() {
		dao = new Dao();
	}
	
	public SheetDTO[] getDTOSheets() {
		return dao.getDTOSheets();
	}

	public Sheet getSheet(int sheetNumber) {
		return dao.getSheet(sheetNumber);
	}
	
}
