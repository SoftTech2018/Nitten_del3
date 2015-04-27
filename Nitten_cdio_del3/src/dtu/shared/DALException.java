package dtu.shared;

/**
 * Data Access Layer Exception is thrown when errors occur in the data layer
 * Exception is serializable and hence DALException is also serializable
 */
public class DALException extends Exception {
	private static final long serialVersionUID = 1L;

	// must have a desfult constructor 
	public DALException() {
		
	}

	public DALException(String s) {
		super(s);
	}
}