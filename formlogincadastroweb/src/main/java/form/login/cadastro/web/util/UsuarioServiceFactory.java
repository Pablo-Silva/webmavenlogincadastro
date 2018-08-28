package form.login.cadastro.web.util;

import java.sql.Connection;

import form.login.cadastro.web.persistence.UsuarioService;

/**
 * Class to instantiate the UsuarioService class.
 * 
 * @author Baracho
 * 
 * @since June 09, 2018
 *
 * @version 1.0
 *
 */
public class UsuarioServiceFactory {

	/**
	 * 
	 */
	public UsuarioServiceFactory() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the usuarioService
	 */
	public static UsuarioService getInstanceUsuarioService() {

		Connection connection = OracleConnectionFactory.getConnection();

		// Connection connection = MySQLConnectionFactory.getConnection();

		UsuarioService usuarioService = new UsuarioService();

		usuarioService.setConnection(connection);

		return usuarioService;
	}
}
