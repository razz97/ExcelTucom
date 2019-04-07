package exception;


/**
 * Excepcion que denota un error en runtime provocado por el usuario.
 * El programa deberia poder continuar despues de su ejecución.
 * 
 * @author razz97
 */
public class InvalidActionException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Tipo de la excepcion.
	 * 
	 * @author razz97
	 */
	public enum Tipo {
		SHEET_NOT_FOUND("No sheet was found in the specified index."),
		SHEET_PARAM_NOT_INTEGER("Parameter 'sheet' must be an integer."), 
		WRITE_UNSUCCESSFUL("There was an error writing into the excel file."), 
		READ_UNSUCCESSFUL("There was an error reading the excel file."), 
		CELL_INDEX_NOT_INTEGER("Parameter 'cell index' must be an integer.");
		
		private String message;

		Tipo(String message) {
			this.message = message;
		}
	}

	private String message;

	public InvalidActionException(Tipo tipo) {
		this.message = tipo.message;
	}

	@Override
	public String getMessage() {
		return message;
}
}
