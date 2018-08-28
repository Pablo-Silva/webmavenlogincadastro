package form.login.cadastro.web.util;

import static form.login.cadastro.web.util.ConstantsStatic.CONNECTION_CLOSE_SUCCESS;
import static form.login.cadastro.web.util.ConstantsStatic.CONNECTION_ERROR;
import static form.login.cadastro.web.util.ConstantsStatic.CONNECTION_ERROR_CLASS_NOT_FOUND;
import static form.login.cadastro.web.util.ConstantsStatic.CONNECTION_SUCCESS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class to manage the connection to the Oracle database.
 * 
 * @author Baracho
 * 
 * @since June 15, 2018
 * 
 * @version 1.0
 * 
 */

class OracleConnectionFactory {

	private static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
	private static final String DATABASE_URL = "jdbc:oracle:thin:@localhost:1521: XE";
	private static final String USERNAME = "aluno";
	private static final String PASSWORD = "aluno";

	private static Connection connection;

	private OracleConnectionFactory() {

		openConnection();
	}

	private void openConnection() {

		String msg = null;

		try {

			Class.forName(JDBC_DRIVER);

			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

			connection.setAutoCommit(false);

			msg = CONNECTION_SUCCESS;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

			connection = null;

			msg = CONNECTION_ERROR_CLASS_NOT_FOUND + " " + e.getMessage();

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

			connection = null;

			msg = CONNECTION_ERROR + " " + e.getMessage();

		} finally {

			System.err.println(msg);
		}
	}

	public static Connection getConnection() {

		if (connection == null) {

			new OracleConnectionFactory();
		}

		return connection;
	}

	public static void closeConnection() {

		String msg = null;

		try {

			connection.close();

			msg = CONNECTION_CLOSE_SUCCESS;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			msg = CONNECTION_ERROR + " " + e.getMessage();

		} finally {

			System.err.println(msg);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		OracleConnectionFactory.getConnection();

		OracleConnectionFactory.closeConnection();
	}
}