package form.login.cadastro.web.util;

/**
 * Class to implement the standard exception to model layer to the Usuario
 * entity.
 * 
 * @author Baracho
 * 
 * @since June 10, 2018
 *
 * @version 1.0
 *
 */
public class UsuarioModelException extends Exception {

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = -5498463819947915509L;

	/**
	 * Default constructor
	 */
	public UsuarioModelException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public UsuarioModelException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 *            the cause to set
	 */
	public UsuarioModelException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 *            the message to set
	 * @param cause
	 *            the cause to set
	 */
	public UsuarioModelException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 *            the message to set
	 * @param cause
	 *            the cause to set
	 * @param enableSuppression
	 *            the enableSuppression to set
	 * @param writableStackTrace
	 *            the writableStackTrace to set
	 */
	public UsuarioModelException(
			String message, 
			Throwable cause, 
			boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
}
