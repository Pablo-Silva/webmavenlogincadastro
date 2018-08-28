package form.login.cadastro.web.persistence;

import static form.login.cadastro.web.util.ConstantsStatic.DELETE_NOT_SUCCESS;
import static form.login.cadastro.web.util.ConstantsStatic.DELETE_SUCCESS;
import static form.login.cadastro.web.util.ConstantsStatic.FLAG;
import static form.login.cadastro.web.util.ConstantsStatic.QUERY_SUCCESS;
import static form.login.cadastro.web.util.ConstantsStatic.RECOVERY_NOT_SUCCESS;
import static form.login.cadastro.web.util.ConstantsStatic.RECOVERY_SUCCESS;
import static form.login.cadastro.web.util.ConstantsStatic.SAVE_ERROR;
import static form.login.cadastro.web.util.ConstantsStatic.SAVE_SUCCESS;
import static form.login.cadastro.web.util.ConstantsStatic.UPDATE_ERROR;
import static form.login.cadastro.web.util.ConstantsStatic.UPDATE_SUCCESS;
import static form.login.cadastro.web.util.ConstantsStatic.V_USUARIO_SEQUENCE;
import static form.login.cadastro.web.util.ConstantsStatic.V_USUARIO_TABLE;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import form.login.cadastro.web.vo.Usuario;

/**
 * Class to implement the operations against to the database to the Usuario
 * entity.
 * 
 * @author Baracho
 * 
 * @since June 09, 2018
 * 
 * @version 3.0
 * 
 */

/**
 * @author Baracho
 * 
 * @since junho 13, 2018
 *
 * @version 1.0
 *
 */
class UsuarioDAO implements IUsuarioDAO {

	/**
	 * Instance variables
	 */

	private Connection connection;

	private int i;

	/**
	 * Default constructor
	 */

	public UsuarioDAO() {

	}

	/**
	 * Access methods
	 */

	/**
	 * @param connection
	 *            the connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Class operations
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * form.login.cadastro.web.persistence.IUsuarioDAO#saveOrUpdate(form.
	 * login.cadastro.web.vo.usuario.Usuario)
	 */
	public void saveOrUpdate(Usuario usuario) throws SQLException {
		// TODO Auto-generated method stub

		// Variables declaration

		PreparedStatement preparedStatement = null;

		String query = null;

		String msg = null;

		Integer id = null;

		Integer count = null;

		// Data processing

		try {

			if (usuario.getId() == null) {

				query = "INSERT INTO ";

				query += V_USUARIO_TABLE;

				query += " U (";

				query += "U.ID, ";

				query += "U.NOME, ";

				query += "U.DOB, ";

				query += "U.SEXO, ";

				query += "U.EMAIL, ";

				query += "U.STATUS, ";

				query += "U.LOGIN, ";

				query += "U.SENHA";

				query += ") VALUES (";

				query += V_USUARIO_SEQUENCE;

				query += ".NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

			} else {

				query = "UPDATE ";

				query += V_USUARIO_TABLE;

				query += " U ";

				query += "SET ";

				query += "U.NOME = ?, ";

				query += "U.DOB = ?, ";

				query += "U.SEXO = ?, ";

				query += "U.EMAIL = ?, ";

				query += "U.STATUS = ?, ";

				query += "U.LOGIN = ?, ";

				query += "U.SENHA = ? ";

				query += "WHERE U.ID = ?";
			}

			preparedStatement = connection.prepareStatement(query);

			this.setPreparedStatement(usuario, preparedStatement);

			if (usuario.getId() != null) {

				preparedStatement.setInt(i++, usuario.getId().intValue());
			}

			count = new Integer(preparedStatement.executeUpdate());

			if (count != null && count.equals(1)) {

				this.connection.commit();

				if (usuario.getId() == null) {

					msg = SAVE_SUCCESS;

					id = this.search();

					usuario.setId(id);

				} else {

					msg = UPDATE_SUCCESS;
				}

				this.search(usuario);

				if (usuario != null && usuario.getId() != null) {

					msg += RECOVERY_SUCCESS;

				} else {

					msg += RECOVERY_NOT_SUCCESS;
				}

			} else {

				if (usuario.getId() == null) {

					msg = SAVE_ERROR;

				} else {

					msg = UPDATE_ERROR;
				}

				this.connection.rollback();
			}

		} finally {

			if (preparedStatement != null) {

				preparedStatement.close();

			}

			if (FLAG.equals(true) && msg != null) {

				System.err.println(String.format("\n %s : %s", 
						this.getClass().getSimpleName(), msg));

				if (usuario != null) {

					System.out.println(usuario.toString());
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see form.login.cadastro.web.persistence.IUsuarioDAO#list()
	 */
	public List<Usuario> list() throws SQLException {
		// TODO Auto-generated method stub

		// Variables declaration

		Statement statement = null;

		ResultSet resultSet = null;

		String query = null;

		String msg = null;

		List<Usuario> usuarios = null;

		Usuario usuario = null;

		// Data processing

		try {

			query = "SELECT * FROM ";

			query += V_USUARIO_TABLE;

			query += " U ";

			query += "ORDER BY U.ID ASC";

			statement = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			resultSet = statement.executeQuery(query);

			if (resultSet.last()) {

				resultSet.beforeFirst();

				usuarios = new ArrayList<Usuario>();

				while (resultSet.next()) {

					usuario = new Usuario();

					this.createUsuario(usuario, resultSet);

					usuarios.add(usuario);
				}

				msg = QUERY_SUCCESS;
			}

		} finally {

			if (statement != null) {

				statement.close();
			}

			if (resultSet != null) {

				resultSet.close();
			}

			if (FLAG.equals(true) && msg != null) {

				System.err.println(String.format("\n %s : %s", 
						this.getClass().getSimpleName(), msg));
			}
		}

		// Information output

		return usuarios;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * form.login.cadastro.web.persistence.IUsuarioDAO#delete(form.login.
	 * cadastro.web.vo.usuario.Usuario)
	 */
	public Boolean delete(Usuario usuario) throws SQLException {
		// TODO Auto-generated method stub

		// Variables declaration

		PreparedStatement preparedStatement = null;

		String query = null;

		String msg = null;

		Boolean flag = null;

		Integer count = null;

		// Data processing

		try {

			query = "DELETE FROM ";

			query += V_USUARIO_TABLE;

			query += " U ";

			query += "WHERE U.ID = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, usuario.getId());

			count = new Integer(preparedStatement.executeUpdate());

			if (count != null && count.equals(1)) {

				this.connection.commit();

				flag = new Boolean(true);

				msg = DELETE_SUCCESS;

			} else {

				this.connection.rollback();

				flag = new Boolean(false);

				msg = DELETE_NOT_SUCCESS;
			}

		} finally {

			if (preparedStatement != null) {

				preparedStatement.close();
			}

			if (FLAG.equals(true) && msg != null) {

				System.err.println(String.format("\n %s : %s", 
						this.getClass().getSimpleName(), msg));
			}
		}

		// Information output

		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see form.login.cadastro.web.persistence.IUsuarioDAO#search()
	 */
	public Integer search() throws SQLException {
		// TODO Auto-generated method stub

		// Variables declaration

		Statement statement = null;

		ResultSet resultSet = null;

		String query = null;

		Integer id = null;

		// Data processing

		try {

			query = "SELECT MAX(U.ID) FROM ";

			query += V_USUARIO_TABLE;

			query += " U";

			statement = connection.createStatement();

			resultSet = statement.executeQuery(query);

			if (resultSet.next()) {

				id = new Integer(resultSet.getInt(1));
			}

		} finally {

			if (statement != null) {

				statement.close();
			}

			if (resultSet != null) {

				resultSet.close();
			}
		}

		// Information output

		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * form.login.cadastro.web.persistence.IUsuarioDAO#search(form.login.
	 * cadastro.web.vo.usuario.Usuario)
	 */
	public void search(Usuario usuario) throws SQLException {
		// TODO Auto-generated method stub

		// Variables declaration

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		String query = null;

		// Data processing

		try {

			query = "SELECT * FROM ";

			query += V_USUARIO_TABLE;

			query += " U ";

			query += "WHERE U.ID = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, usuario.getId().intValue());

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				this.createUsuario(usuario, resultSet);

			} else {

				usuario.setId(null);
			}

		} finally {

			if (preparedStatement != null) {

				preparedStatement.close();
			}

			if (resultSet != null) {

				resultSet.close();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * form.login.cadastro.web.persistence.IUsuarioDAO#search(java.lang.
	 * String)
	 */
	public List<Usuario> search(String nome) throws SQLException {
		// TODO Auto-generated method stub

		// Variables declaration

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		String query = null;

		List<Usuario> usuarios = null;

		Usuario usuario = null;

		// Data processing

		try {

			nome = "%" + nome.toUpperCase() + "%";

			query = "SELECT * FROM ";

			query += V_USUARIO_TABLE;

			query += " U ";

			query += "WHERE U.NOME LIKE ?";

			preparedStatement = connection.prepareStatement(
					query, 
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			preparedStatement.setString(1, nome);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.last()) {

				resultSet.beforeFirst();

				usuarios = new ArrayList<Usuario>();

				while (resultSet.next()) {

					usuario = new Usuario();

					this.createUsuario(usuario, resultSet);

					if (usuario != null) {

						usuarios.add(usuario);
					}
				}
			}

		} finally {

			if (preparedStatement != null) {

				preparedStatement.close();
			}

			if (resultSet != null) {

				resultSet.close();
			}
		}

		// Information output

		return usuarios;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * form.login.cadastro.web.persistence.IUsuarioDAO#authenticateUsuario(
	 * form.login.cadastro.web.vo.Usuario)
	 */
	public void authenticateUsuario(Usuario usuario) throws SQLException {
		// TODO Auto-generated method stub

		// Variables declaration

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		String query = null;

		// Data processing

		try {

			query = "SELECT * FROM ";

			query += V_USUARIO_TABLE;

			query += " U ";

			query += "WHERE U.LOGIN = ? AND U.SENHA = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, usuario.getLogin());

			preparedStatement.setString(2, usuario.getSenha());

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				this.createUsuario(usuario, resultSet);

			} else {

				usuario.setId(null);
			}

		} finally {

			if (preparedStatement != null) {

				preparedStatement.close();
			}

			if (resultSet != null) {

				resultSet.close();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * form.login.cadastro.web.persistence.IUsuarioDAO#validateLogin(java.
	 * lang.String)
	 */
	public Usuario validateLogin(String login) throws SQLException {
		// TODO Auto-generated method stub

		// Variables declaration

		Usuario usuario = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		String query = null;

		// Data processing

		try {

			query = "SELECT * FROM ";

			query += V_USUARIO_TABLE;

			query += " U ";

			query += "WHERE U.LOGIN = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, login);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				usuario = new Usuario();

				this.createUsuario(usuario, resultSet);

			} else {

				usuario = null;
			}

		} finally {

			if (preparedStatement != null) {

				preparedStatement.close();
			}

			if (resultSet != null) {

				resultSet.close();
			}
		}

		// Information output

		return usuario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * form.login.cadastro.web.persistence.IUsuarioDAO#validateLogin(java.
	 * lang.String, java.lang.Integer)
	 */
	public Usuario validateLogin(String login, Integer id) throws SQLException {
		// TODO Auto-generated method stub

		// Variables declaration

		Usuario usuario = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		String query = null;

		// Data processing

		try {

			query = "SELECT * FROM ";

			query += V_USUARIO_TABLE;

			query += " U ";

			query += "WHERE U.LOGIN = ? AND U.ID <> ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, login);

			preparedStatement.setInt(2, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				usuario = new Usuario();

				this.createUsuario(usuario, resultSet);

			} else {

				usuario = null;
			}

		} finally {

			if (preparedStatement != null) {

				preparedStatement.close();
			}

			if (resultSet != null) {

				resultSet.close();
			}
		}

		// Information output

		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 * @param preparedStatement
	 *            the prepared Statement to set
	 * @throws SQLException
	 */
	private void setPreparedStatement(
			Usuario usuario, PreparedStatement preparedStatement) 
					throws SQLException {

		i = 1;

		preparedStatement.setString(i++, usuario.getNome());

		if (usuario.getDataNascimento() != null) {

			preparedStatement.setDate(i++, 
					new Date(usuario.getDataNascimento().getTime()));

		} else {

			preparedStatement.setNull(i++, Types.NULL);
		}

		if (usuario.getSexo() != null) {

			preparedStatement.setString(i++, usuario.getSexo().toString());

		} else {

			preparedStatement.setNull(i++, Types.NULL);
		}

		if (usuario.getEmail() != null) {

			preparedStatement.setString(i++, usuario.getEmail());

		} else {

			preparedStatement.setNull(i++, Types.NULL);
		}

		if (usuario.getStatus() != null) {

			if (usuario.getStatus().equals(true)) {

				preparedStatement.setInt(i++, 1);

			} else {

				preparedStatement.setInt(i++, 2);
			}

		} else {

			preparedStatement.setNull(i++, Types.NULL);
		}

		preparedStatement.setString(i++, usuario.getLogin());

		preparedStatement.setString(i++, usuario.getSenha());
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 * @param resultSet
	 *            the resultSet to set
	 * @throws SQLException
	 */
	private void createUsuario(Usuario usuario, ResultSet resultSet) throws SQLException {

		usuario.setId(resultSet.getInt("ID"));

		usuario.setNome(resultSet.getString("NOME"));

		if (resultSet.getDate("DOB") != null) {

			usuario.setDataNascimento(
					new java.util.Date(resultSet.getDate("DOB").getTime()));

		} else {

			usuario.setDataNascimento(null);
		}

		if (resultSet.getString("SEXO") != null) {

			usuario.setSexo(new Character(resultSet.getString("SEXO").charAt(0)));

		} else {

			usuario.setSexo(null);
		}

		if (resultSet.getString("EMAIL") != null) {

			usuario.setEmail(resultSet.getString("EMAIL"));

		} else {

			usuario.setEmail(null);
		}

		if (resultSet.getInt("STATUS") != Types.NULL) {

			if (resultSet.getInt("STATUS") == 1) {

				usuario.setStatus(new Boolean(true));

			} else {

				if (resultSet.getInt("STATUS") == 2) {

					usuario.setStatus(new Boolean(false));
				}
			}

		} else {

			usuario.setStatus(null);
		}

		usuario.setLogin(resultSet.getString("LOGIN"));

		usuario.setSenha(resultSet.getString("SENHA"));
	}
}