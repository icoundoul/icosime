package eu.els.sie.firestorm.backend.repository.exception;

public class BussinesException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errorCode;
	public BussinesException(String code,String errorMessage) {
        super(errorMessage);
		this.setErrorCode(code);
    }
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
