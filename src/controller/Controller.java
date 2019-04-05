package controller;

import persistance.Dao;

public class Controller {

	private Dao dao;
	
	public Controller() {
		dao = new Dao();
	}
	
	public String[] getSheetNames() {
		return dao.getSheetNames();
	}
	
}
