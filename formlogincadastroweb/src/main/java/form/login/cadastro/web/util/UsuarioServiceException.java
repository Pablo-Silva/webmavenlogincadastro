package form.login.cadastro.web.util;

/**
 * Class to implement the standard exception to the data access layer to the
 * Usuario entity.
 * 
 * @author Baracho
 * 
 * @since June 09, 2018
 *
 * @version 1.0
 *
 */
public class UsuarioServiceException extends Exception {

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = -6990739940226891935L;

	/**
	 * Default constructor
	 */
	public UsuarioServiceException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public UsuarioServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 *            the cause to set
	 */
	public UsuarioServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 *            the message to set
	 * @param cause
	 *            the cause to set
	 */
	public UsuarioServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 *            the message to set
	 * @param cause
	 *            the cause to set
	 * @param enableSuppression
	 *            the enableSupression to set
	 * @param writableStackTrace
	 *            the writeStackTracet o set
	 */
	public UsuarioServiceException(
			String message, 
			Throwable cause, 
			boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
}
