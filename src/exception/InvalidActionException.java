package exception;


/**
 * Custom exception class, represents that there was an error either caused by the user or by an excel file.
 * @author razz97
 */
public class InvalidActionException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Tye of an InvalidActionException.
	 * Contains the message to show to the user.
	 * @author razz97
	 */
	public enum Tipo {
		SHEET_NOT_FOUND("No sheet was found in the specified index."),
		SHEET_PARAM_NOT_INTEGER("Parameter 'sheet' must be an integer."), 
		WRITE_UNSUCCESSFUL("There was an error writing into the excel file."), 
		READ_UNSUCCESSFUL("There was an error reading the excel file."), 
		WRONG_POST_PARAM("Wrong post param, try using the form instead of sending request manually."), 
		WRONG_DELETE_PARAM("Wrong delete param, try using the form instead of sending request manuallly"), 
		ERROR_UPLOADING_FILE("There was an error uploading the requested file."), 
		FILE_EXISTS("File already exists in the server, please upload with different name or select it in upload tab."), 
		INVALID_SHEET_NAME("Sheet name is invalid.");		
		
		private String message;

		Tipo(String message) {
			this.message = message;
		}
		
		public String getMessage() {
			return message;
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
