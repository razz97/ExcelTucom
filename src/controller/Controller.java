package controller;

import org.apache.poi.ss.usermodel.Sheet;

import model.SheetDTO;
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

	public Sheet getSheet(int sheetNumber) {
		return dao.getSheet(sheetNumber);
	}
	
}
