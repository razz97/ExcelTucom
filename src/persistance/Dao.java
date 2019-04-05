package persistance;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Dao {
	
	private Workbook workbook;

	public Dao() {
        try {
			workbook = WorkbookFactory.create(new File("/home/alex/Eclipse/ExcelTucom/resources/ShowExcel.xlsx"));
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public String[] getSheetNames() {
		String[] names = new String[workbook.getNumberOfSheets()];
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			names[i] = workbook.getSheetName(i);
		}
		return names;
	}
	
	public Sheet getSheet(int index) {
		return workbook.getSheetAt(index);
	}	
	
}
